#include "net_praseodym_activelearner_AFL.h"
#include <asm/unistd_64.h>

static int memfd_create(const char *name, unsigned int flags) {
  return syscall(__NR_memfd_create, name, flags);
}

static void setup_memfd() {
  // Create a memfd (shm) to save stdout of our target
  const int shm_size = 1024 * 1024;
  int ret;
  stdout_fd = memfd_create("stdout_fd", 0);
  if (stdout_fd == -1) PFATAL("Unable to create stdout buffer (%d)", errno);
  ret = ftruncate(stdout_fd, shm_size);
  ret = fcntl(stdout_fd, F_ADD_SEALS, F_SEAL_SHRINK);
  stdout_buffer = mmap(NULL, shm_size, PROT_READ | PROT_WRITE, MAP_SHARED, stdout_fd, 0);
  //SAYF("stdout_fd: %d\n", stdout_fd);
  if (shm_id < 0) PFATAL("shmget() failed");
}

JNIEXPORT void JNICALL Java_net_praseodym_activelearner_AFL_hello(JNIEnv *env, jobject obj) {
  SAYF("hello\n");
}

JNIEXPORT void JNICALL Java_net_praseodym_activelearner_AFL_pre(JNIEnv *env,
                                                                jobject obj,
                                                                jstring jin,
                                                                jstring jout,
                                                                jobjectArray jargv) {
//  SAYF("libafl pre: initialising AFL\n");

  in_dir = (u8 * )(*env)->GetStringUTFChars(env, jin, 0);
  if (in_dir == NULL) PFATAL("libafl pre: Unable to get in_dir");
//  SAYF("libafl pre: in_dir: [%s]\n", in_dir);

  out_dir = (u8 * )(*env)->GetStringUTFChars(env, jout, 0);
  if (out_dir == NULL) PFATAL("libafl pre: Unable to get out_dir");
//  SAYF("libafl pre: out_dir: [%s]\n", out_dir);

//  const char *dict_dir = (*env)->GetStringUTFChars(env, jdict, 0);
//  if (dict_dir == NULL) PFATAL("Pre: Unable to get dict_dir");

  jsize length = (*env)->GetArrayLength(env, jargv);
  //SAYF("argv length: %d\n", length);
  char *argv[length+1];
  int i = 0;
  for(; i < length; i++) {
    jstring arg = (jstring)(*env)->GetObjectArrayElement(env, jargv, i);
    if (arg != NULL) {
      char *carg = (u8 * )(*env)->GetStringUTFChars(env, arg, NULL);
      argv[i] = carg;
      // SAYF("argv[%d] = %s\n", i, argv[i]);
    }
  }
  // argv must be NULL-terminated
  argv[length] = NULL;

  // TODO: initialise other options like qemu and dictionary

  u64 prev_queued = 0;
  u32 sync_interval_cnt = 0, seek_to;
  u8 *extras_dir = 0;
  u8 mem_limit_given = 0;

  struct timeval tv;
  struct timezone tz;

  doc_path = (u8 *) "docs";

  gettimeofday(&tv, &tz);
  srandom(tv.tv_sec ^ tv.tv_usec ^ getpid());

  if (!strcmp(in_dir, "-")) in_place_resume = 1;

  setup_signal_handlers();
  check_asan_opts();

  if (sync_id) fix_up_sync();

  if (!strcmp(in_dir, out_dir))
    FATAL("Input and output directories can't be the same");

  if (dumb_mode) {

    if (crash_mode) FATAL("-C and -n are mutually exclusive");
    if (qemu_mode) FATAL("-Q and -n are mutually exclusive");

  }

  if (getenv("AFL_NO_FORKSRV"))    no_forkserver    = 1;
  if (getenv("AFL_NO_CPU_RED"))    no_cpu_meter_red = 1;
  if (getenv("AFL_SHUFFLE_QUEUE")) shuffle_queue    = 1;

  if (dumb_mode == 2 && no_forkserver)
    FATAL("AFL_DUMB_FORKSRV and AFL_NO_FORKSRV are mutually exclusive");

  if (getenv("AFL_PRELOAD")) {
    setenv("LD_PRELOAD", getenv("AFL_PRELOAD"), 1);
    setenv("DYLD_INSERT_LIBRARIES", getenv("AFL_PRELOAD"), 1);
  }

  orig_cmdline = (u8 *) "<activelearner>";

  fix_up_banner((u8 *) argv[0]);

  not_on_tty = 1;

  get_core_count();

#ifdef HAVE_AFFINITY
  bind_to_free_cpu();
#endif /* HAVE_AFFINITY */

  check_crash_handling();
  check_cpu_governor();

  setup_post();
  setup_shm();
  init_count_class16();

  setup_memfd();

  setup_dirs_fds();
  read_testcases();
  load_auto();

  pivot_inputs();

  if (extras_dir) load_extras(extras_dir);

  if (!timeout_given) find_timeout();

  detect_file_args(argv);

  if (!out_file) setup_stdio_file();

  check_binary(argv[0]);

  start_time = get_cur_time();

//  if (qemu_mode)
//    use_argv = get_qemu_argv(argv[0], argv + optind, argc - optind);
//  else
//    use_argv = argv + optind;

  perform_dry_run(argv);

  cull_queue();

  show_init_stats();

  seek_to = find_start_position();

  write_stats_file(0, 0, 0);
  save_auto();

  fflush(stdout);

//  SAYF("libafl pre: initialised AFL\n");

  // We'll keep the arguments, i.e. no ReleaseStringUTFChars calls
  // TODO: make a local copy of the arguments so that they can be released again
}

JNIEXPORT jbyteArray JNICALL Java_net_praseodym_activelearner_AFL_run(JNIEnv *env, jobject obj, jstring jtestcase) {
  jbyte *testcase = (*env)->GetByteArrayElements(env, jtestcase, NULL);
  jsize testcase_length = (*env)->GetArrayLength(env, jtestcase);

  // testcase[testcase_length] = 0;
  // SAYF("run\n");
  // SAYF("testcase with length %d: %s\n", testcase_length, testcase);

  // put testcase in out_file / out_fd
  //SAYF("out_file: %s\n", out_file);

  // argv for target
  char *argv[] = {NULL};
  child_timed_out = 0;

  stage_name = (u8 *) "learner";
  stage_short = (u8 *) "learner";

  int i = 0;
  do {
    common_fuzz_stuff(argv, (u8 *) testcase, (u32) testcase_length);
    if (child_timed_out) {
      if (i > 25) {
        sleep(5);
      } else if (i > 100) {
        FATAL("Target process timed out too many times, giving up.");
        break;
      }
      i++;
      WARNF("Target process timed out, retrying (attempt %d)", i);
      fflush(stdout);
    }
  } while (child_timed_out);

  __off64_t stdout_position = lseek(stdout_fd, 0, SEEK_CUR);
  // SAYF("lseek: %d\n", stdout_position);
  // SAYF("stdout: %s\n", stdout_buffer);

  (*env)->ReleaseByteArrayElements(env, jtestcase, testcase, 0);

  jbyteArray array = (*env)->NewByteArray(env, (jsize) stdout_position);
  (*env)->SetByteArrayRegion(env, array, 0, (jsize) stdout_position, (jbyte *) stdout_buffer);
  return array;

}

JNIEXPORT void JNICALL Java_net_praseodym_activelearner_AFL_post(JNIEnv *env, jobject obj) {
  //SAYF("post\n");
  //write_bitmap();
  //write_stats_file(0, 0);
  //save_auto();

  // clean up memfd (stdout_fd)
  close(stdout_fd);

  /* Running for more than 30 minutes but still doing first cycle? */

  if (queue_cycle == 1 && get_cur_time() - start_time > 30 * 60 * 1000) {

    SAYF("\n" cYEL "[!] " cRST
    "Stopped during the first cycle, results may be incomplete.\n"
        "    (For info on resuming, see %s/README.)\n", doc_path);

  }

  //fclose(plot_file);
  destroy_queue();
  destroy_extras();
  ck_free(target_path);
  ck_free(sync_id);

  alloc_report();

  OKF("Total executions: %d", total_execs);
  OKF("We're done here. Have a nice day!\n");

  fflush(stdout);
}

JNIEXPORT jint JNICALL Java_net_praseodym_activelearner_AFL_getQueuedDiscovered(JNIEnv *env, jobject obj) {
  return queued_discovered;
}


JNIEXPORT jbyteArray JNICALL Java_net_praseodym_activelearner_AFL_getTraceBitmap(JNIEnv *env, jobject obj) {
  jbyteArray array = (*env)->NewByteArray(env, (jsize) MAP_SIZE);
  (*env)->SetByteArrayRegion(env, array, 0, (jsize) MAP_SIZE, (jbyte *) trace_bits);
  return array;
}