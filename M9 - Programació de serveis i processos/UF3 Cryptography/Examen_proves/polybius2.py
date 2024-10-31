import sys


def encrypt(message):

    string = 'abcdefghijklmnopqrstuvwxyz0123456789'
    data = [list(string[i:i+6]) for i in range(0, len(string), 6)]

    positions = []

    for letter in message:
        
        for i in range(len(data)):

            for j in range(len(data[i])):

                if data[i][j] == letter:
                    positions.append(f"{i + 1}{j + 1}")


    return "".join(positions)


def decrypt(message):

    string = 'abcdefghijklmnopqrstuvwxyz0123456789'
    data = [list(string[i:i+6]) for i in range(0, len(string), 6)]


    positions = []

    list_message = list(message)


    for i in range(0, len(list_message), 2):
        row = int(list_message[i])
        column = int(list_message[i + 1])


        positions.append(data[row - 1][column - 1])

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