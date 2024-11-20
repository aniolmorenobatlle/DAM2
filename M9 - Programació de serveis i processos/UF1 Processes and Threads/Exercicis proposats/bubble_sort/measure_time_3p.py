import time
import subprocess


def run_bubble_sort_1p(n):
    # Començar temporitzador
    start_time = time.time()
    
    # Executar la funció bubble_sort_1p.py
    subprocess.run(['python3', 'bubble_sort_1p.py', str(n)])

    # Acabar temporitzador
    end_time = time.time()

    # Retornar el temps que ha tardat
    return end_time - start_time


def run_bubble_sort_3p(n):
    # Començar temporitzador
    start_time = time.time()
    
    # Executar la funció bubble_sort_3p.py
    subprocess.run(['python3', 'bubble_sort_3p.py', str(n)])

    # Acabar temporitzador
    end_time = time.time()

    # Retornar el temps que ha tardat
    return end_time - start_time



# Quins números ha de mirar el bubble_sort (per exemple, de 1000 a 20000)
n_values = list(range(1000, 21000, 1000))

times_1p = []
times_3p = []

for n in n_values:
    # Mesurem els temps per cada valor de n
    time_1p = run_bubble_sort_1p(n)
    time_3p = run_bubble_sort_3p(n)

    # Afegim els temps obtinguts a les llistes corresponents
    times_1p.append(time_1p)
    times_3p.append(time_3p)


# Guardar els temps en un fitxer
with open('numbers.txt', 'w') as file:
    for i in range(len(n_values)):
        file.write(f'{n_values[i]}\t{times_1p[i]}\t{times_3p[i]}\n')