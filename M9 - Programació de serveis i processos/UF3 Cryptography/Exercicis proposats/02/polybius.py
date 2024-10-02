# 02. Create a program in Python language, using the imperative procedural programming paradigm, that encrypts and decrypts messages using the Polybius square cipher with the English alphabet and the following arrangement:


import sys


def encrypt (message):
    data = [
        ['a', 'b', 'c', 'd', 'e', 'f'],
        ['g', 'h', 'i', 'j', 'k', 'l'],
        ['m', 'n', 'o', 'p', 'q', 'r'],
        ['s', 't', 'u', 'v', 'w', 'x'],
        ['y', 'z', '0', '1', '2', '3'],
        ['4', '5', '6', '7', '8', '9']
    ]

    positions = []

    # Mirem quina es la posicio de la lletra de la paraula.
    for letter in message:
        # Recorre l'array.
        for i in range(len(data)):

            for j in range(len(data[i])):

                if data[i][j] == letter:
                    positions.append(f"{i + 1}{j + 1}")  # +1 perque la llista comença per 0 i hauria de començar per 1.
                                                         # la f es per posar-ho com a string amb els dos valors junts.

    return "".join(positions) # Un join perque sino surt en format llista.


def decrypt (message):
    data = [
        ['a', 'b', 'c', 'd', 'e', 'f'],
        ['g', 'h', 'i', 'j', 'k', 'l'],
        ['m', 'n', 'o', 'p', 'q', 'r'],
        ['s', 't', 'u', 'v', 'w', 'x'],
        ['y', 'z', '0', '1', '2', '3'],
        ['4', '5', '6', '7', '8', '9']
    ]

    positions = []

    # Crear una llista amb el missatge.
    list_message = list(message)

    # Recorrer la llista de dos numeros en dos.
    for i in range(0, len(list_message), 2):
        row = int(list_message[i])
        column = int(list_message[i + 1])

        positions.append(data[row - 1][column - 1]) # Posar -1 perque la llista comença per 0 i hauria de començar per 1.
    

    return "".join(positions)
    


if len(sys.argv) > 1:
    function = sys.argv[1]
    ciphertext = sys.argv[2]

    if function == "e":
        print(encrypt(ciphertext))
    
    elif function == "d":
        print(decrypt(ciphertext))

else:
    print("Please provide the function(decrypt or encrypt) and the message as a command line argument.")