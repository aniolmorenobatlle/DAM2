import random
import socket
import threading as th

HOST = "192.168.19.244"
PORT = 8000


def handle_client(conn, addr, toss_final):
    """ Gestionar  cada client de forma independent """
    print(f"[NOVA CONNEXIÓ] Client connectat des de {addr}")
    print()

    # Rebre dades del client
    message = conn.recv(1024).decode('utf-8')

    client_username, toss_result = message.split('|')

    print(f"[MISSATGE] El client {addr} es diu {client_username} i el resultat del tir és {toss_result}")

    # Enviar una resposta personalitzada
    response = f"Hola {client_username}, benvingut! Connexió exitosa!!"
    conn.sendall(response.encode('utf-8'))

    # Tancar connexió
    # conn.close()
    # print(f"[DESCONNEXIÓ] Client {addr} s'ha desconnectat!!")


if __name__ == '__main__':
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((HOST, PORT))
    server_socket.listen()

    print(f"Servidor escoltant a {HOST}:{PORT}")


    # Multiples clients
    while True:

        conn, addr = server_socket.accept()

        toss = random.randint(1, 2)
        toss_final = ''

        if toss == 1:
            toss_final = 'Coin'
        else:
            toss_final = 'Tail'

        client_thread = th.Thread(target=handle_client, args=(conn, addr, toss_final))
        client_thread.start()