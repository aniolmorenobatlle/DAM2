import random
import socket
import time

ip_server = input("Introdueix la IP del servidor: ")
port_server = int(input("Introdueix el port del servidor: "))

username = input("Introdueix el teu nom: ")

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
    client_socket.connect((ip_server, port_server))

    client_socket.sendall(username.encode('utf-8'))

    while True:
        response = client_socket.recv(1024).decode('utf-8')
        if not response:
            break

        print(response)

        if "eliminat" in response or "guanyat" in response:
            break

        time.sleep(random.uniform(1, 3))
        
        toss = 'Coin' if random.randint(1, 2) == 1 else 'Tail'

        message = f"{username}|{toss}"
        client_socket.sendall(message.encode('utf-8'))
