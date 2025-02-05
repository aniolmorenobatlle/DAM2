import datetime as dati
import socket
import sys
import threading

def show_text(t):
    print(f"{dati.datetime.now()} {t}")

def receive_message(cl, nina):
    while True:
        try:
            message = cl.recv(1024).decode('utf-8')
            if message == "NICK":
                cl.send(nina.encode('utf-8'))
            else:
                print(message)
        
        except:
            show_text("An error occurred!")
            cl.close()
            break

def send_message(cl, nina):
    while True:
        message = input()
        cl.send(f"{nina}: {message}".encode('utf-8'))

def main():
    show_text("Starts")
    nickname = input("Choose a nickname: ")

    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client.connect((sys.argv[1], int(sys.argv[2])))

    show_text(f"{nickname} {client.getsockname() [0]}:{client.getsockname() [1]}")

    receive_thread = threading.Thread(target=receive_message, args=(client, nickname))
    receive_thread.start()

    send_thread = threading.Thread(target=send_message, args=(client, nickname))
    send_thread.start()


if __name__ == "__main__":
    main()