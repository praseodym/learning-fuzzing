#ifndef RERSPROBLEMS_ASSERT_H
#define RERSPROBLEMS_ASSERT_H

#include <sys/cdefs.h>
#include <stdlib.h>

extern void __assert_fail (const char *__assertion, const char *__file,
                           unsigned int __line, const char *__function) {
//        __THROW __attribute__ ((__noreturn__));
  printf("assert:%s\n", __assertion);
  exit(3);
}

# define assert(expr) \
  ((expr) \
   ? (void) (0) \
   : __assert_fail (__STRING(expr), __FILE__, __LINE__, 0))

#endif //RERSPROBLEMS_ASSERT_H
