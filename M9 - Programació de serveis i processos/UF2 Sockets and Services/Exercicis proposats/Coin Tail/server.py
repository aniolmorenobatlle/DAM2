import random
import socket
import threading

HOST = "192.168.1.41"
PORT = 8000

clients = []
usernames = []
lock = threading.Lock()


def handle_client(conn, addr):
    """ Gestionar cada client de forma independent """
    global clients, usernames

    print(f"[NOVA CONNEXIÓ] Client connectat des de {addr}")

    username = conn.recv(1024).decode('utf-8')
    
    with lock:
        usernames.append(username)
        clients.append(conn)

    while True:
        conn.sendall("Llança la moneda".encode('utf-8'))

        try:
            message = conn.recv(1024).decode('utf-8')
        except:
            break

        if not message:
            break

        client_username, toss_result = message.split('|')
        print(f"[MISSATGE] {client_username} ha llançat la moneda i ha obtingut {toss_result}")

        if toss_result == 'Tail':
            with lock:
                print(f"[ELIMINAT] {client_username} ha estat eliminat!!")
                conn.sendall("Has estat eliminat!!".encode('utf-8'))
                clients.remove(conn)
                usernames.remove(client_username)
                conn.close()
            break

        # Mirar si hi ha guanyador
        with lock:
            if len(clients) == 1:
                winner = usernames[0]
                print(f"[GUANYADOR] {winner} ha guanyat la partida!!")
                clients[0].sendall(f"Enhorabona {winner}!! Has guanyat la partida!!".encode('utf-8'))
                clients[0].close()
                clients.clear()
                usernames.clear()
                break


if __name__ == '__main__':
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((HOST, PORT))
    server_socket.listen()

    print(f"Servidor escoltant a {HOST}:{PORT}")

    while True:
        conn, addr = server_socket.accept()
        client_thread = threading.Thread(target=handle_client, args=(conn, addr))
        client_thread.start()
