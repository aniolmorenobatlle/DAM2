# 06. Create a Python program (dont_guess_the_number.py) that simulates the game “Don't guess the number” through multiprogramming.

# The game works as follows:
# ● The participants of the game are a referee and some players (between two and six).
# ● All participants will throw a dice, first the referee and then all the players at the same time.
# ● Once all the dice have been thrown, the number indicated by the referee's dice will be compared with the number indicated by each player's dice. Players with the   same number as the referee will be eliminated.
# ● If there are players (not eliminated), the above process will be repeated until there is one player or none.
# ● If there is one player at the end of the game, they will be the winner. 
# 
# Each player will be a child process that:
# ● When it is ready to start, it will indicate it with a message on the screen.
# ● While it is playing: it will roll the dice and then communicate the result to the main process which will be in charge of displaying the corresponding message.
# ● Depending on the result, it will wait for his turn to roll the dice again or it will be eliminated by the main process.
# 
# The main process will take the role of the referee and each message that appears on the screen will be preceded by the time that has passed since the game started.

import multiprocessing as mp
import random
import time
import sys


def generate_participants(num_players):
    print(f"{time.time() - start_time:.6f} List of Participants:")

    participants = ['Referee']

    for i in range(1, num_players + 1):
        participants.append(f'Player {i}')

    return participants


def throw_dice(participants):
    print(f"{time.time() - start_time:.6f} Throwing dice:")

    results = {}

    for participant in participants:
        results[participant] = random.randint(1, 6)

    for participant, result in results.items():
        print(f"{time.time() - start_time:.6f} \t{participant} - {result}")

    return results


def compare_with_referee(results, participants):
    referee_result = results['Referee']
    print(f'{time.time() - start_time:.6f} Referee rolled: {referee_result}')

    same_result = False
    participants_to_remove = []

    for participant, result in results.items():
        if participant != 'Referee' and result == referee_result:
            print(f"{time.time() - start_time:.6f} \t{participant} is eliminated as they rolled the same number as the referee.")
            participants_to_remove.append(participant)
            same_result = True

    for participant in participants_to_remove:
        participants.remove(participant)

    if not same_result:
        print(f"{time.time() - start_time:.6f} \tNobody eliminated as they didn't roll the same number as the referee.")

    return participants


def check_winner(participants):
    # Crear una llista temporal sense el referee
    participants_without_referee = [participant for participant in participants if participant != 'Referee']

    if len(participants_without_referee) == 1:
        print(f"{time.time() - start_time:.6f} The Winner is: {participants_without_referee[0]}")
        return True
    else:
        print('\nRemaining participants:')
        for participant in participants_without_referee:
            print(f"{time.time() - start_time:.6f} \t{participant}")
        
        # Afegir el referee a la primera posició
        if 'Referee' not in participants:
            participants.insert(0, 'Referee')
        
        return False


if __name__ == '__main__':
    start_time = time.time()
    
    if len(sys.argv) != 2:
        print(f"{time.time() - start_time:.6f} The number of player must be specified")
        print(f"{time.time() - start_time:.6f} Game has been interrupted")
        sys.exit(1)


    try:
        num_players = int(sys.argv[1])
    
    except ValueError:
        print(f"{time.time() - start_time:.6f} Incorrect number of players!")
        print(f"{time.time() - start_time:.6f} Game has been interrupted!")
        sys.exit(1)

    if num_players < 2 or num_players > 6:
        print(f"{time.time() - start_time:.6f} The number of players must be a minimum of two and a maximum of six")
        print(f"{time.time() - start_time:.6f} Game has been interrupted!")
        sys.exit(1)

    print(f"{time.time() - start_time:.6f} Game is starting ...")

    participants = generate_participants(num_players)

    for participant in participants:
        print(f"{time.time() - start_time:.6f} \t{participant}")


    while True:
        results = throw_dice(participants)
        participants = compare_with_referee(results, participants)
        
        if check_winner(participants):
            break


    print(f"{time.time() - start_time:.6f} Game over")