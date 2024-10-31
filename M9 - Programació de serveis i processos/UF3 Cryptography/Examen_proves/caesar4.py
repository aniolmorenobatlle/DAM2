import sys

alf = 'abcdefghijklmnopqrstuvwxyz'

def decrypt(message, space_number):
    decrypted = ''
    for letter in message:
        if letter in alf:
            decrypted += alf[(alf.index(letter) - space_number) % len(alf)]
    return decrypted


def encrypt(message, space_number):
    encrypted = ''
    for letter in message:
        if letter in alf:
            encrypted += alf[(alf.index(letter) + space_number) % len(alf)]
    return encrypted


if len(sys.argv) > 3:
    function = sys.argv[1]
    chipertext = sys.argv[2]
    space_number = int(sys.argv[3])

    if function == 'd':
        print(decrypt(chipertext, space_number))
    elif function == 'e':
        print(encrypt(chipertext, space_number))
    else:
        print("Please specify 'e' for encryption or 'd' for decryption.")
else:
    print("Please provide encryption/decryption flag, the message, and the space number.")
