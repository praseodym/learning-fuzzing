#include <stdio.h>
#include <stdlib.h>

void invalid_input() {
  printf("invalid_input\n");
  exit(1);
}

int current_state = 0;
char step(char input) {
  switch (current_state) {
    case 0:
      switch (input) {
        case 'A':
          current_state = 1;
          return 'X';
        case 'B':
          current_state = 2;
          return 'Y';
        case 'C':
          return 'Z';
        default:
          invalid_input();
      }
    case 1:
      switch (input) {
        case 'A':
          current_state = 3;
          return 'Z';
        case 'B':
          return 'Y';
        case 'C':
          return 'Z';
        default:
          invalid_input();
      }
    case 2:
      switch (input) {
        case 'A':
          return 'Z';
        case 'B':
          return 'Y';
        case 'C':
          current_state = 0;
          return 'Z';
        default:
          invalid_input();
      }
    case 3:
      switch (input) {
        case 'A':
          // Some side effect here
          return 'Z';
        case 'B':
          return 'Y';
        case 'C':
          current_state = 0;
          return 'Z';
        default:
          invalid_input();
      }
  }
  return 0;
}

int main() {
#ifdef __AFL_HAVE_MANUAL_CONTROL
  __AFL_INIT();
#endif

  // main i/o-loop
  while (1) {
    // read input
    char input = 0;
    int ret = scanf("%c", &input);
    if (ret == EOF)
      exit(0);
    else if (input >= 'A') {
      // operate state machine
      char c = step(input);
      printf("%c\n", c);
    }
  }
}
