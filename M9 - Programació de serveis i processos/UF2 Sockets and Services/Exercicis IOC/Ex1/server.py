# Exercici 1: Crear un servidor i un client bàsic

# Servidor: Crea un servidor que escolti a un port determinat i que enviï un missatge al client quan aquest es connecti.
# Client: El client es connectarà al servidor i imprimirà el missatge que rep.


import socket

HOST = "192.168.19.244"
PORT = 8000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:

    server_socket.bind((HOST, PORT))
    server_socket.listen()

    print(f"Servidor escoltant a {HOST}:{PORT}...")

    conn, addr = server_socket.accept()

    with conn:
        print(f"Connexió establerta amb {addr}")
        message = "Hola, client. Connexió exitosa!!"
        conn.sendall(message.encode('utf-8'))