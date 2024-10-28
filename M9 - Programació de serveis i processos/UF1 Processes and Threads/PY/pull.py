import multiprocessing as mp
import random

def parell(n):
    total = 0

    if (n % 2 == 0):
        total += 1

    for i in range(n):
        print(total)

if __name__ == '__main__':
    with mp.Pool() as pl:
        nums = [random.randint(0, 500000)]
        res = pl.map(parell, nums)