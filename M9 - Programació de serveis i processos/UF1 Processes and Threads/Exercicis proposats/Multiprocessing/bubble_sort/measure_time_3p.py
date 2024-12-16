import time
import subprocess
import random
import os

# Generar fitxers amb números aleatoris
def generate_random_files(start, end, step, output_dir="data"):
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    for n in range(start, end + 1, step):
        file_name = f"{output_dir}/numbers_{n}.txt"
        with open(file_name, "w") as file:
            numbers = [random.randint(1, 1000) for _ in range(n)]
            file.write("\n".join(map(str, numbers)))
    print(f"Fitxers generats a {output_dir}")


# Executar bubble_sort_1p.py
def run_bubble_sort_1p(file_name):
    start_time = time.time()
    subprocess.run(['python3', 'bubble_sort_1p.py', file_name])
    end_time = time.time()
    return end_time - start_time


# Executar bubble_sort_3p.py
def run_bubble_sort_3p(file_name):
    start_time = time.time()
    subprocess.run(['python3', 'bubble_sort_3p.py', file_name])
    end_time = time.time()
    return end_time - start_time


# Generar fitxers de números
generate_random_files(1000, 2000, 1000)


# Llistes per guardar els temps
n_values = list(range(1000, 2000, 1000))
times_1p = []
times_3p = []


# Mesurar els temps d'execució
for n in n_values:
    file_name = f"data/numbers_{n}.txt"

    print(f"Mesurant temps per {file_name}...")
    time_1p = run_bubble_sort_1p(file_name)
    time_3p = run_bubble_sort_3p(file_name)

    times_1p.append(time_1p)
    times_3p.append(time_3p)


# Guardar els resultats en un fitxer
with open('times_3p.txt', 'w') as file:
    file.write("N\tBubbleSort_1P\tBubbleSort_3P\n")
    for i, n in enumerate(n_values):
        file.write(f"{n}\t{times_1p[i]:.4f}\t{times_3p[i]:.4f}\n")


print("Resultats guardats a times_3p.txt")
