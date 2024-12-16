import time
import subprocess

def run_fibonacci_1p(n):
    # Començar temporitzador
    start_time = time.time()
    
    # Executar la funció fibonacci_1p.py
    subprocess.run(['python3', 'fibonacci_1p.py', str(n)])

    # Acabar temporitzador
    end_time = time.time()

    # Retornar el temps que ha tardat
    return end_time - start_time


def run_fibonacci_2p(n):
    # Començar temporitzador
    start_time = time.time()
    
    # Executar la funció fibonacci_2p.py
    subprocess.run(['python3', 'fibonacci_2p.py', str(n)])

    # Acabar temporitzador
    end_time = time.time()

    # Retornar el temps que ha tardat
    return end_time - start_time


# Quins números ha de mirar el fibonacci (per exemple, del 25 al 50)
n_values = list(range(10, 41))

times_1p = []
times_2p = []

for n in n_values:
    # Mesurem els temps per cada valor de n
    time_1p = run_fibonacci_1p(n)
    time_2p = run_fibonacci_2p(n)

    # Afegim els temps obtinguts a les llistes corresponents
    times_1p.append(time_1p)
    times_2p.append(time_2p)

# Guardar els resultats a un arxiu
with open("times_2p.txt", 'w') as f:
    for n, t1, t2 in zip(n_values, times_1p, times_2p):
        f.write(f"{n},{t1},{t2}\n")
