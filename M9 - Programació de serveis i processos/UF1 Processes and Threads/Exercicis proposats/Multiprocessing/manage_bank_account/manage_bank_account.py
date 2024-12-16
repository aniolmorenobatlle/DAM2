# 05. The following program written in Python sometimes does not return a correct result.
# Modify it with some multiprogramming synchronization mechanism so that it returns always the correct result.


import multiprocessing as mp
import time

def deposit(b, t, lock):
    for _ in range(t):
        time.sleep(0.01)

        with lock:
            b.value += 5


def withdraw(b, t, lock):
    for _ in range(t):
        time.sleep(0.01)

        with lock:
            b.value -= 5



if __name__ == '__main__':

    balance = mp.Value('i', 500) # Començar a 500€
    lock = mp.Lock()

    process_deposit = mp.Process(target=deposit, args=(balance, 100, lock))
    process_withdraw = mp.Process(target=withdraw, args=(balance, 100, lock))

    process_deposit.start()
    process_withdraw.start()
    
    process_deposit.join()
    process_withdraw.join()

    print(balance.value)