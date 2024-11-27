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


def generate_participants():
    print('\nList of Participants:')

    players = random.randint(2, 6)
    participants = ['Referee']

    for i in range(1, players + 1):
        participants.append(f'Player {i}')

    return participants


def throw_dice(participants):
    print('\nThrowing dice:')

    results = {}

    for participant in participants:
        results[participant] = random.randint(1, 6)
    
    for participant, result in results.items():
        print(f'\t{participant} - {result}')

    return results


def compare_with_referee(results, participants):
    referee_result = results['Referee']
    print(f'\nReferee rolled: {referee_result}')

    same_result = False
    participants_to_remove = []

    for participant, result in results.items():
        if participant != 'Referee' and result == referee_result:
            print(f'{participant} eliminated, as they have the same number as the referee.')
            participants_to_remove.append(participant)
            same_result = True

    for participant in participants_to_remove:
        participants.remove(participant)

    if not same_result:
        print('Nobody has been elminated as they didn\'t rolled the same number as the referee.')

    return participants


def check_winner(participants):
    print(f'\nThe Winner is:')

    if 'Referee' in participants:
        participants.remove('Referee')

    print('\nRemaining participnats without the referee:')
    for participant in participants:
        print(f'\t{participant}')



if __name__ == '__main__':
    participants = generate_participants()
    
    for participant in participants:
        print(f'\t{participant}')

    results = throw_dice(participants)
    
    participants = compare_with_referee(results, participants)
    print('\nRemaining participants')
    
    for participant in participants:
        print(f'\t{participant}')

    check_winner(participants)
    