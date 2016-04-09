package main

import (
	"bufio"
	"flag"
	"fmt"
	"gitlab.science.ru.nl/rick/fsm"
	"os"
)

const header string = `#include <stdio.h>
#include <stdlib.h>

void invalid_input() {
	printf("invalid_input\n");
	exit(1);
}

int current_state = 0;
`

const epilogue string = `default:
			invalid_input();
	}
	return -1;
}
`

const footer string = `
int main() {
	#ifdef __AFL_HAVE_MANUAL_CONTROL
		__AFL_INIT();
	#endif

	// main i/o-loop
	while (1) {
		// read input
		int input = 0;
		int ret = scanf("%d", &input);
		if (ret == EOF) {
			exit(0);
		} else if (ret == 0) {
			invalid_input();
		} else {
			// operate state machine
			int i = step(input);
			printf("%d\n", i);
			fflush(stdout);
		}
	}
}`

func main() {
	fnopt := flag.String("f", "", "C source code")
	flag.Parse()
	fn := *fnopt

	// Read dot file
	if fn == "" {
		fn = "/dev/stdin"
	}
	f, err := os.Open(fn)
	if err != nil {
		panic(err)
	}

	// Construct fsm
	a := fsm.NewAdapter(fsm.DotParser{})
	a.Scan(bufio.NewScanner(f))
	m := a.FSM()
	states, inputs := m.States(), m.Inputs()

	// Print state, input and output sets in header
	fmt.Println("// states:", a.States())
	fmt.Println("// inputs:", a.Inputs())
	fmt.Println("// outputs:", a.Outputs())

	// Print program header header for c file
	fmt.Println(header)

	// Print switch function for each input
	for state := 0; state < states; state++ {
		fmt.Printf("int step_state_%d(int input) {\n\tswitch (input) {", state)
		for input := 0; input < inputs; input++ {
			output, target, err := m.Transition(state, input)
			if err != nil {
				panic(err)
			}
			fmt.Printf("\n\t\tcase %d:\n\t\t\tcurrent_state = %d;\n\t\t\treturn %d;", input, target, output)
		}
		fmt.Println(epilogue)
	}

	// Print switch for each state
	fmt.Println("int step(int input) {\n\tswitch (current_state) {")
	for state := 0; state < states; state++ {
		fmt.Printf("\t\tcase %d:\n\t\t\treturn step_state_%d(input);\n", state, state)
	}
	fmt.Println(epilogue)

	// Print footer
	fmt.Println(footer)
}
