# manage_bank_account.py
import multiprocessing as mp
import time

def deposit(b, t):
    for u in range(t):
        time.sleep(0.01)
        b.value += 5


def withdraw(b, t):
    for w in range(t):
        time.sleep(0.01)
        b.value -= 5



if __name__ == '__main__':

    balance = mp.Value('i', 500)
    process_deposit = mp.Process(target=deposit, args=(balance, 100))
    process_withdraw = mp.Process(target=withdraw, args=(balance, 100))
    process_deposit.start()
    process_withdraw.start()
    process_deposit.join()
    process_withdraw.join()
    
    print(balance.value)