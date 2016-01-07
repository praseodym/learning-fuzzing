//
// Created by mark on 1/6/16.
//

#ifndef RERSPROBLEM1_ASSERT_H
#define RERSPROBLEM1_ASSERT_H

#include <sys/cdefs.h>
#include <stdlib.h>

#if defined __cplusplus && __GNUC_PREREQ (2,95)
# define __ASSERT_VOID_CAST static_cast<void>
#else
# define __ASSERT_VOID_CAST (void)
#endif

extern void __assert_fail (const char *__assertion, const char *__file,
                           unsigned int __line, const char *__function) {
//        __THROW __attribute__ ((__noreturn__));
  printf("assert:%s\n", __assertion);
  exit(3);
}

# define assert(expr)							\
  ((expr)								\
   ? __ASSERT_VOID_CAST (0)						\
   : __assert_fail (__STRING(expr), __FILE__, __LINE__, __ASSERT_FUNCTION))

# if defined __cplusplus ? __GNUC_PREREQ (2, 6) : __GNUC_PREREQ (2, 4)
#   define __ASSERT_FUNCTION	__PRETTY_FUNCTION__
# else
#  if defined __STDC_VERSION__ && __STDC_VERSION__ >= 199901L
#   define __ASSERT_FUNCTION	__func__
#  else
#   define __ASSERT_FUNCTION	((const char *) 0)
#  endif
# endif

#endif //RERSPROBLEM1_ASSERT_H
