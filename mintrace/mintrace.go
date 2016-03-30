package main

import (
	"bufio"
	"fmt"
	"gitlab.science.ru.nl/rick/fsm"
	"gitlab.science.ru.nl/rick/partition"
	"os"
)

func read(path string) (*fsm.Adapter, *partition.Partition) {
	// Read file and construct FSM
	f, err := os.Open(path)
	if err != nil {
		panic(err)
	}
	a := fsm.NewAdapter(fsm.DotParser{})
	a.Scan(bufio.NewScanner(f))
	m := a.FSM()
	states, inputs, outputs := m.States(), m.Inputs(), m.Outputs()

	// Construct transition (f) and output (g) functions
	fs := make([]func(int) int, 0, inputs)
	gs := make([]func(int) int, 0, inputs)
	for input := 0; input < inputs; input++ {
		f, _ := m.TransitionFunction(input)
		fs = append(fs, f)
		g, _ := m.OutputFunction(input)
		gs = append(gs, g)
	}

	// Construct the partition
	p := partition.New(states, outputs, gs...)
	p.Refine(0, fs...)

	// Print results
	var not string
	if p.Size() != states {
		not = "not "
	}
	fmt.Printf("FSM %s is %sminimal.\n", path, not)

	return a, p
}

func main() {
	if len(os.Args) != 3 {
		fmt.Printf("Usage: %s <fsm1.dot> <fsm2.dot>\n", os.Args[0])
		os.Exit(1)
	}

	a1, _ := read(os.Args[1])
	a2, _ := read(os.Args[2])

	// Add transitions of a2 to a1
	for t := range a2.Transitions() {
		from := "a2_" + t.From
		input := t.Input
		output := t.Output
		to := "a2_" + t.To

		if _, err := a1.Input(input); err != nil {
			panic(err)
		}

		a1.AddState(from)
		a1.AddState(to)
		a1.AddOutput(output)
		a1.AddTransition(from, input, output, to)
	}

	// Determine start states
	start1 := "s0"
	start2 := "a2_s0"
	if _, err := a1.State(start2); err != nil {
		panic(err)
	}

	// Make product FSM
	product := a1.FSM()
	states, inputs, outputs := product.States(), product.Inputs(), product.Outputs()

	// Construct partition for product FSM
	fs := make([]func(int) int, 0, inputs)
	gs := make([]func(int) int, 0, inputs)
	for input := 0; input < inputs; input++ {
		f, _ := product.TransitionFunction(input)
		fs = append(fs, f)
		g, _ := product.OutputFunction(input)
		gs = append(gs, g)
	}

	p := partition.New(states, outputs, gs...)
	p.Refine(0, fs...)

	// Print witness (minimal separating sequence) for start states
	s, _ := a1.State(start1)
	t, _ := a1.State(start2)
	witness := p.Witness(s, t)
	fmt.Println("A minimal separating sequence for the two start states is:")
	for _, i := range witness {
		input, _ := a1.InputName(i)
		fmt.Print(input + " ")
	}
	fmt.Print("\n")

}
