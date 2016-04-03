#!/usr/bin/env python3

import os
import string
import subprocess
import sys


def alphabet_index(char):
    try:
        return string.ascii_uppercase.index(char) + 1
    except ValueError:
        return -1


def alphabet_char(index):
    try:
        return string.ascii_uppercase[int(index) - 1]
    except ValueError:
        return index


def run_process(target, file):
    """ Run target with file as input. Expects properly newline-delimited input and output. """
    stdin = open(file)
    process = subprocess.Popen(target, stdin=subprocess.PIPE, stdout=subprocess.PIPE)

    trace = []
    for inline in stdin:
        inline = inline.rstrip()
        process.stdin.write(str.encode(inline + '\n'))
        try:
            process.stdin.flush()
        except BrokenPipeError:
            break
        outline = process.stdout.readline().decode().strip()
        trace.append(alphabet_char(inline) + '\\' + alphabet_char(outline))
        if outline == 'invalid_state' or 'assert:' in outline:
            break

    return trace


def format_trace(trace):
    return str(len(trace)) + ' ' + ' '.join(trace)


def write_traces(target, traces_directory):
    files = filter(lambda f: os.path.isfile(f),
                   map(lambda f: os.path.join(traces_directory, f), os.listdir(traces_directory)))
    for file in files:
        trace = run_process(target, file)
        print(format_trace(trace))


if len(sys.argv) == 3:
    write_traces(sys.argv[1], sys.argv[2])
else:
    sys.exit('Usage: ' + sys.argv[0] + ' <target> <input_traces_directory>')
