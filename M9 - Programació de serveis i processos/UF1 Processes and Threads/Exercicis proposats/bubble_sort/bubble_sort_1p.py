# 03. The following program written in Python sorts the numbers contained in a text file using bubble sort:

import os
import sys


def bubble_sort(l_unsorted):
    l_sorted = l_unsorted[:]
    n_sorted = len(l_sorted)

    for i in range(n_sorted - 1):
        for j in range(0, n_sorted - i - 1):
            if l_sorted[j] > l_sorted[j + 1]:
                l_sorted[j], l_sorted[j + 1] = l_sorted[j + 1], l_sorted[j]

    return l_sorted


if len(sys.argv) > 1:
    file_name = sys.argv[1]

    if os.path.exists(file_name):
        unsorted_list = []

        with open(file_name) as file:
            for line in file:
                unsorted_list.append(int(line))

        print(bubble_sort(unsorted_list))

    else:
        print(f"file with name '{file_name}' does not exist!")

else:
    print("the file name is missing as an argument!")
