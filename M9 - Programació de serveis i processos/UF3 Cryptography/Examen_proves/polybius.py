import sys


def encrypt(message):

    table = [
        ['a', 'b', 'c', 'd', 'e', 'f'],
        ['g', 'h', 'i', 'j', 'k', 'l'],
        ['m', 'n', 'o', 'p', 'q', 'r'],
        ['s', 't', 'u', 'v', 'w', 'x'],
        ['y', 'z', '0', '1', '2', '3'],
        ['4', '5', '6', '7', '8', '9']
    ]

    positions = []

    for letter in message:

        for i in range(len(table)):

            for j in range(len(table[i])):

                if table[i][j] == letter:
                    positions.append(f"{i + 1}{j + 1}")


    return "".join(positions)


def decrypt(message):


    table = [
        ['a', 'b', 'c', 'd', 'e', 'f'],
        ['g', 'h', 'i', 'j', 'k', 'l'],
        ['m', 'n', 'o', 'p', 'q', 'r'],
        ['s', 't', 'u', 'v', 'w', 'x'],
        ['y', 'z', '0', '1', '2', '3'],
        ['4', '5', '6', '7', '8', '9']
    ]

    positions = []

    list_message = list(message)

    for i in range(0, len(list_message), 2):
        row = int(list_message[i])
        column = int(list_message[i + 1])

        positions.append(table[row - 1][column - 1])

    return "".join(positions)


if len(sys.argv) > 1:
    function = sys.argv[1]
    ciphertext = sys.argv[2]

    if function == 'e':
        print(encrypt(ciphertext))

    elif function == 'd':
        print(decrypt(ciphertext))


else:
    print("Please provide a message")