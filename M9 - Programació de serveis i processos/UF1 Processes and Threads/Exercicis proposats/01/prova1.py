# fibonacci_2p.py
import sys
import multiprocessing as mp


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
        queue = mp.Queue()
        p = mp.Process(target=calculate_fibonacci, args=(n - 1, queue))
    
        p.start()
    
        result_main = fibonacci(n - 2)
    
        p.join()
    
        result_child = queue.get()
        result_main += result_child
        
        print(result_main)
    
    else:
        print("the argument is not a number!")
else:
    print("the number is missing as an argument!")
