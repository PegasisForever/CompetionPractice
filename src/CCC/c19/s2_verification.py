#! /usr/bin/python3

import sys
from math import sqrt
from itertools import count, islice

expected_output = sys.argv[2].split("\n")
output = sys.argv[3].split("\n")

def is_prime(n):
    if n < 2:
        return False

    for number in islice(count(2), int(sqrt(n) - 1)):
        if n % number == 0:
            return False

    return True


for i in range(len(output)):
    expected_a, expected_b = expected_output[i].split(" ")
    expected_a = int(expected_a)
    expected_b = int(expected_b)
    expected_sum = expected_a + expected_b

    actual_a, actual_b = output[i].split(" ")
    actual_a = int(actual_a)
    actual_b = int(actual_b)
    actual_sum = expected_a + expected_b

    if not is_prime(actual_a) or not is_prime(actual_b) or actual_sum!=expected_sum:
        sys.exit(1)

sys.exit(0)
