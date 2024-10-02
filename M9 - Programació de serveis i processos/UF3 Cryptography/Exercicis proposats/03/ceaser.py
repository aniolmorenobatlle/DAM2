# 03. Create a program in Python language, using the object oriented programming paradigm, that encrypts and decrypts messages using the Caesar cipher with various alphabets (English, Catalan and Spanish). It must be verified that the arguments of the program execution are acceptable


import sys


def encrypt():
    return



def decrypt():
    return



if len(sys.argv) > 1:
    function = sys.argv[1]
    ciphertext = sys.argv[2]

    if function == "e":
        print(encrypt(ciphertext))
    
    elif function == "d":
        print(decrypt(ciphertext))

else:
    print("Please provide a message as a command line argument.")