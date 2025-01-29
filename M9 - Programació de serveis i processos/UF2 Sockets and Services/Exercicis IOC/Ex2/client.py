import socket

ip_server = input("Introdueix la ip del servidor: ")
port_server = int(input("Introdueix el port del servidor: "))

HOST = ip_server
PORT = port_server

client_name = input("Introdueix el teu nom: ")

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
    client_socket.connect((HOST, PORT))

    # Enviar nom del servidor
    client_socket.sendall(client_name.encode('utf-8'))

    # Rebre resposta del servidor
    data = client_socket.recv(1024)
    print("Missatge del servidor:", data.decode('utf-8'))