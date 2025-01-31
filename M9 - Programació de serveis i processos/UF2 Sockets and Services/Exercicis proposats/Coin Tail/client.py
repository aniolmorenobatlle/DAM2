import random
import socket

ip_server = input("Introdueix la ip del servidor: ")
port_server = int(input("Introduexi el port del servidor: "))

HOST = ip_server
PORT = port_server

username = input("Introdueix el teu nom per la partida: ")

toss = random.randint(1, 2)
toss_result = ''

if toss == 1:
    toss_result = 'Coin'
else:
    toss_result = 'Tail'

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:

    client_socket.connect((HOST, PORT))

    message = f"{username}|{toss_result}"

    client_socket.sendall(message.encode('utf-8'))
