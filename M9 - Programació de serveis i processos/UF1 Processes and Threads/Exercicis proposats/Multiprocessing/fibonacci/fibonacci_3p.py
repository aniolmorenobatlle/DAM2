# 02. Create a Python program (fibonacci_3p.py) that modify the source code from the previous exercise to calculate the Fibonacci sequence through multiprogramming (three processes, main and two child that they share the work). Then create a table of programs execution times (fibonacci_1p.py, fibonacci_2p.py and fibonacci_3p.py, for values from 25 to 50) and also a bar graph to compare the programs execution times.


import sys
import multiprocessing as mp

def fibonacci(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    
    return fibonacci(n - 1) + fibonacci(n - 2)


def calculate_fibonacci1(n, queue):
    result = fibonacci(n)
    queue.put(result)


def calculate_fibonacci2(n, queue):
    result = fibonacci(n)
    queue.put(result)


if len(sys.argv) > 1:
    number = sys.argv[1]
    if number.isnumeric():
        n = int(number)
        queue = mp.Queue()

        # Crear processos fills per calcular en un fibonacci(n - 1) i en un altre fibonacci(n - 2)
        p1 = mp.Process(target = calculate_fibonacci1, args=(n - 1, queue))
        p2 = mp.Process(target = calculate_fibonacci2, args=(n - 2, queue))

        p1.start()
        p2.start()
        
        # Esperar que el proces fill acabi
        p1.join()
        p2.join()
        
        # Obtenir el resultat del fill i posar-lo a la cua
        result_child1 = queue.get()
        result_child2 = queue.get()

        # Combiar els resultats
        result = result_child1 + result_child2
        
        print(result)
    
    else:
        print('the argument is not a number!')
else:
    print('the number is missing as an argument!')
