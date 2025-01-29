import datetime as dati
import socket

print(f"{dati.datetime.now()} server starts")
# Create IPv4 socket as TCP.
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# Assigns localhost address and port 8000 to the socket.
server_socket.bind(('127.0.0.1', 8000))
# Listen to at most one connection at a time.
server_socket.listen(1)

try:
    while True:
        print(f"{dati.datetime.now()} server is ready")
        # Set up a new connection from a client.
        client_socket, client_address = server_socket.accept()
        print(f"{dati.datetime.now()} server has established a connection")
        request = client_socket.recv(1024)
        print(f"{dati.datetime.now()} request: {request}")
        response = b"HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\nHello World!"
        client_socket.send(response)
        print(f"{dati.datetime.now()} response: {response}")
        client_socket.close() # Because server_socket.listen(1).
except KeyboardInterrupt:
    print(f"\n{dati.datetime.now()} server interrupted!") # Control + C.
    
print(f"{dati.datetime.now()} server ends")