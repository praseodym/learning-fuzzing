#!/usr/bin/python3

import os
import string


def alphabet_index(char):
    try:
        return string.ascii_uppercase.index(char) + 1
    except ValueError:
        return -1


def write_traces(problem):
    input_file = "../Problem{:d}/Problem{:d}-Traces.txt".format(problem, problem)
    output_dir = "Problem{:d}-Traces".format(problem)
    traces = open(input_file)
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    for trace in traces:
        num = trace[:trace.find("#")].zfill(3)
        symbols = trace[trace.find("# ") + 2:].split(",")
        numeric = list()
        for symbol in symbols:
            index = alphabet_index(symbol)
            if index > 0:
                numeric.append(str(index))
        print("\tTrace {:s}".format(num))
        output_file = open(output_dir + '/' + num + '.txt', 'w')
        output_file.write("\n".join(numeric))


for problem in range(4, 7):
    print("Problem {:d}".format(problem))
    write_traces(problem)
