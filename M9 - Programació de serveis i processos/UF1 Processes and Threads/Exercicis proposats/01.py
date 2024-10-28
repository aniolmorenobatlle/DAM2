# Create a Python program (fibonacci_2p) that modify the previous source code to calculate the Fibonacci sequence through multiprogramming (two processes, main and one child that them share the work). Then create a table of programs execution times (fibonacci_1p.py and fibonacci_2p, for values from 25 to 50, using a Shell Script or Python script) and also a bar graph (using Matplotlib) to compare the programs execution times.

import multiprocessing as mp


import sys
def fibonacci(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    return fibonacci(n - 1) + fibonacci(n - 2)
    

if len(sys.argv) > 1:
    number = sys.argv[1]
    
    if number.isnumeric():
        print(fibonacci(int(number)))
    else:
        print('the argument is not a number!')

else:
    print('the number is missing as an argument!')