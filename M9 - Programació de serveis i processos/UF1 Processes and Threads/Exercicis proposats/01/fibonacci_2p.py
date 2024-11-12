# Create a Python program (fibonacci_2p) that modify the previous source code to calculate the Fibonacci sequence through multiprogramming (two processes, main and one child that them share the work). Then create a table of programs execution times (fibonacci_1p.py and fibonacci_2p, for values from 25 to 50, using a Shell Script or Python script) and also a bar graph (using Matplotlib) to compare the programs execution times.


import sys
import multiprocessing

def fibonacci(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    
    return fibonacci(n - 1) + fibonacci(n - 2)


def calculate_fibonacci(n, queue):
    result = fibonacci(n)
    queue.put(result)


if len(sys.argv) > 1:
    number = sys.argv[1]
    if number.isnumeric():
        n = int(number)
        queue = multiprocessing.Queue()

        # Crear proces fill per calcular nomes fibonacci(n - 1)
        p = multiprocessing.Process(target=calculate_fibonacci, args=(n - 1, queue))
        p.start()
        
        # Proces principal que calcula el fibonacci(n - 2)
        result_main = fibonacci(n - 2)
        
        # Esperar que el proces fill acabi
        p.join()
        
        # Obtenir el resultat del fill i posar-lo a la cua
        result_child = queue.get()

        # Combiar els resultats
        result = result_child + result_main
        
        print(result)
    
    else:
        print('the argument is not a number!')
else:
    print('the number is missing as an argument!')
