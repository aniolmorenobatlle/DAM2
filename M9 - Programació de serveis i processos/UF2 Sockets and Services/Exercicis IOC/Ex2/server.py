# Exercici 2: Crear un servidor que processi múltiples clients

# Servidor: Crearem un servidor que pugui acceptar connexions simultànies de múltiples clients. Cada client que es connecti rebrà un missatge personalitzat (com el seu nom o la seva adreça IP).
# Client: El client s'ha de connectar i enviar el seu nom al servidor, i el servidor li respondrà amb un missatge personalitzat.


import socket
import threading as th

HOST = "192.168.19.244"
PORT = 8000


def handle_client(conn, addr):
    """ Gestionar  cada client de forma independent """
    print(f"[NOVA CONNEXIÓ] Client connectat des de {addr}")

    # Rebre nom del client
    client_name = conn.recv(1024).decode('utf-8')
    print(f"[MISSATGE] El client {addr} diu que es diu {client_name}")

    # Enviar una resposta personalitzada
    response = f"Hola {client_name}, benvingut! Connexió exitosa!!"
    conn.sendall(response.encode('utf-8'))

    # Tancar connexió
    conn.close()
    print(f"[DESCONNEXIÓ] Client {addr} s'ha desconnectat!!")


server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind((HOST, PORT))
server_socket.listen()

print(f"Servidor escoltant a {HOST}:{PORT}")


# Multiples clients
while True:

    conn, addr = server_socket.accept()
    client_thread = th.Thread(target=handle_client, args=(conn, addr))
    client_thread.start()