# 04. Create a Python program (bubble_sort_pool.py) that modify the source code from the previous exercise to sort a text file through multiprogramming with a pool. Then create a table of programs execution times (bubble_sort_1p.py, bubble_sort_3p.py and bubble_sort_pool.py) for text files with 1000, 2000, 3000, …, 20000 numbers and also a bar graph to compare the programs execution times.


import os
import sys
import multiprocessing as mp


def bubble_sort(l_unsorted):
    l_sorted = l_unsorted[:]
    n_sorted = len(l_sorted)

    for i in range(n_sorted - 1):
        for j in range(0, n_sorted - i - 1):
            if l_sorted[j] > l_sorted[j + 1]:
                l_sorted[j], l_sorted[j + 1] = l_sorted[j + 1], l_sorted[j]

    return l_sorted


# Funció que ordena una part de la llista i guarda el resultat.s
def sort_half(part):
    return bubble_sort(part)


if len(sys.argv) > 1:
    file_name = sys.argv[1]

    if os.path.exists(file_name):
        unsorted_list = []

        with open(file_name) as file:
            for line in file:
                unsorted_list.append(int(line))


        # Dividir la llista en dues parts
        mid = len(unsorted_list) // 2 # Per trobar la meitat de la llista. // perque aixi no dona decimals
        parts = [unsorted_list[:mid], unsorted_list[mid:]]

        # Pool per ordenar les parts
        with mp.Pool(processes=2) as pool:
            sorted_parts = pool.map(sort_half, parts)

        
        # Unir els resultats
        sorted_list = sorted_parts[0] + sorted_parts[1]

        print(bubble_sort(sorted_list))

    else:
        print(f"file with name '{file_name}' does not exist!")

else:
    print("the file name is missing as an argument!")
