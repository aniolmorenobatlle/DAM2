# 03. Create a Python program (bubble_sort_3p.py) that modify the previous source code to sort a text file through multiprogramming (three processes, main and two child, where the children do all the sorting work). Then create a table of programs execution times (bubble_sort_1p.py and bubble_sort_3p.py, for text files with 1000, 2000, 3000, …, 20000 numbers) and also a bar graph to compare the programs execution times.


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
def sort_half(part, result, index):
    result[index] = bubble_sort(part)



if len(sys.argv) > 1:
    file_name = sys.argv[1]

    if os.path.exists(file_name):
        unsorted_list = []

        with open(file_name) as file:
            for line in file:
                unsorted_list.append(int(line))


        # Dividir la llista en dues parts
        mid = len(unsorted_list) // 2 # Per trobar la meitat de la llista. // perque aixi no dona decimals
        part1 = unsorted_list[:mid] # De l'inici fins la meitat
        part2 = unsorted_list[mid:] # De la meitat fins el final

        # Compartir resultats entre els processos
        manager = mp.Manager() # Manager permet compartir dades entre processos
        result = manager.dict() # Crear un diccionari per guardar els resultats dels processos


        # Crear els dos processos
        p1 = mp.Process(target=sort_half, args=(part1, result, 0))
        p2 = mp.Process(target=sort_half, args=(part2, result, 1))

        p1.start()
        p2.start()

        p1.join()
        p2.join()

        # Unir resultats
        sorted_list = result[0] + result[1]

        print(bubble_sort(sorted_list))

    else:
        print(f"file with name '{file_name}' does not exist!")

else:
    print("the file name is missing as an argument!")
