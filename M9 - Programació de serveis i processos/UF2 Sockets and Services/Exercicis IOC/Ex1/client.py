import socket

HOST = "192.168.19.244"
PORT = 8000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
    client_socket.connect((HOST, PORT))

    data = client_socket.recv(1024) # Esperar resposta del servidor

    print("Missatge del servidor: ", data.decode('utf-8'))