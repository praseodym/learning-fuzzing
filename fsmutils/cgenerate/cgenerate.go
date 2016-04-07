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
int step(int input) {
	switch (current_state) {`

const footer string = `	}
	return 0;
}

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

	// Print switch for each state and input
	for state := 0; state < states; state++ {
		str := fmt.Sprintf("\t\tcase %d:\n\t\t\tswitch (input) {", state)
		for input := 0; input < inputs; input++ {
			output, target, err := m.Transition(state, input)
			if err != nil {
				panic(err)
			}
			str += fmt.Sprintf("\n\t\t\t\tcase %d:\n\t\t\t\t\tcurrent_state = %d;\n\t\t\t\t\treturn %d;", input, target, output)

		}
		str += "\n\t\t\t\tdefault:\n\t\t\t\t\tinvalid_input();\n\t\t\t}\n"
		fmt.Println(str)
	}

	// Print footer
	fmt.Println(footer)
}
