import datetime as dati
import socket
import threading as th


HOST = "192.168.1.41"
PORT = 8000

clients = []
nicknames = []

def show_text(t):
    print(f"{dati.datetime.now()} {t}")

def broadcast(co, ci, m):
    for c in ci:
        if c != co:
            c.send(m)
            show_text(f"send {c.getpeername()} message {m.decode('utf-8')}")

def handle(co, cl):
    while True:
        try:
            message = co.recv(1024)
            broadcast(co, cl, message)

        except:
            index = clients.index(co)
            clients.remove(co)
            co.close()
            nickname = nicknames[index]
            broadcast(co, cl, f"{nickname} left the chat!".encode('utf-8'))
            nicknames.remove(nickname)
            break

def main():
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.bind((HOST, PORT))
    server.listen()

    show_text(f"Server is running on {HOST}:{PORT}")

    while True:
        conn, addr = server.accept()
        show_text(f"Connected with {addr}")

        conn.send("NICK".encode('utf-8'))
        nickname = conn.recv(1024).decode('uft-8')
        nicknames.append(nickname)
        clients.append(conn)

        show_text(f"New client: {nickname}")
        broadcast(conn, clients, f"{nickname} joined!".encode('utf-8'))
        conn.send("Connected to server!".encode('utf-8'))

        thread = th.Thread(target=handle, args=(conn, clients))
        thread.start()

if __name__ == '__main__':
    main()