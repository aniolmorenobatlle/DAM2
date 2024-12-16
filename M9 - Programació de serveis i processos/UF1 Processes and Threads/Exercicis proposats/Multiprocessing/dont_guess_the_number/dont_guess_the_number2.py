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
import sys
import random


def generate_player_and_roll(player_id, queue):
    roll = random.randint(1, 6)
    player = f'Player {player_id}'

    # Enviar el jugador i el resultat a la cua
    queue.put((player, roll))



if __name__ == '__main__':
    if len(sys.argv) > 1:
        try:
            num_players = int(sys.argv[1])
        except ValueError:
            print("Please provide a valid number of players.")
            sys.exit(1)


        # Crear la cua per compartir els resultats entre els processos
        queue = mp.Queue()

        # Llançament de l'arbit
        referee_roll = random.randint(1, 6)


        # Crear un proces per cada jugador per generar-lo i llançar el dau
        processes = []
        for player_id in range(1, num_players + 1):
            process = mp.Process(target=generate_player_and_roll, args=(player_id, queue))
            processes.append(process)
            process.start()


        # Esperar que tots els processos acabin
        for process in processes:
            process.join()


        # Recollir els resultats de la cua
        results = []
        while not queue.empty():
            results.append(queue.get())


        # Ordenar els resultats per nom del jugador
        results.sort(key=lambda x: x[0])

        print("\nResults:")
        
        print(f"\tReferee: {referee_roll}")
        for player, roll in results:
            print(f"\t{player}: {roll}")


    else:
        print("Usage: python script.py <number_of_players>")
