#! /usr/bin/python3

import sys

expected_output = sys.argv[2].split("\n")
output = sys.argv[3].split("\n")

output_matrix = [[int(num) for num in output[i].split(' ')] for i in range(3)]

for x in range(3):
    if not (output_matrix[x][1] - output_matrix[x][0] == output_matrix[x][2] - output_matrix[x][1]):
        sys.exit(1)

for y in range(3):
    if not (output_matrix[1][y] - output_matrix[0][y] == output_matrix[2][y] - output_matrix[1][y]):
        sys.exit(1)

sys.exit(0)
