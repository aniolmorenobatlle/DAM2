import multiprocessing as mp
import random
import sys
import time


def log_time(start_time, message):
    timer = time.time() - start_time
    print(f" {timer:.6f} {message}")


def players_ready(start_time, player):
    log_time(start_time, f"{player} is ready...")


def players_dice_roll(start_time, player, referee_roll, queue):
    dice_roll = random.randint(1, 6)
    
    if dice_roll == referee_roll:
        log_time(start_time, f"{player} throws the dice and reports that it is a {dice_roll}, eliminated") # Since the player it is eliminated is not necessary to kill que process cause it's not necessary to put into the queue

    else:
        log_time(start_time, f"{player} throws the dice and reports that it is a {dice_roll}, continues")
        queue.put(player)


if __name__ == '__main__':
    if len(sys.argv) > 1:
        try:
            num_players = int(sys.argv[1])
        except ValueError:
            print("Please provide a valid number of players.")
            sys.exit(1)

        if num_players < 2 or num_players > 6:
            print("The number of players must be a minimum of two and a maximum of six")
            sys.exit(1)


        start_time = time.time()
        print(" 0.000000 Game is starting...")


        participants = []
        for i in range(1, num_players + 1):
            participants.append(f'P{i}')


        processes = []


        # Proccess for the participants
        for participant in participants:
            p = mp.Process(target=players_ready, args=(start_time, participant))
            processes.append(p)
            p.start()


        for p in processes:
            p.join()

        
        time.sleep(1)

        while len(participants) > 1:

            processes = []

            queue = mp.Queue()

            # Referee rolls the dice
            referee_roll = random.randint(1, 6)
            log_time(start_time, f"Referee throws a dice and reports that it is a {referee_roll}")


            time.sleep(1)


            # Players roll the dice
            for participant in participants:
                p = mp.Process(target=players_dice_roll, args=(start_time, participant, referee_roll, queue))
                processes.append(p)
                p.start()

            for p in processes:
                p.join()

            
            # Collect the remaining participants
            participants = []
            while not queue.empty():
                participants.append(queue.get())

            time.sleep(1)


        if len(participants) == 1:
            log_time(start_time, f"Referee determines that the {participants[0]} wins")

        elif len(participants) == 0:
            log_time(start_time, "There is no winner")


        log_time(start_time, "Game over")

    else:
        print("The number of players must be specified!")
