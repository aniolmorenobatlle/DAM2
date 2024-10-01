# 01. Create a program in Python language, using the imperative procedural programming paradigm, that encrypts and decrypts messages using the Atbash encryption method with the English alphabet.


import sys
from string import ascii_lowercase as alf


def encrypt(message):
    encrypted = ""
        
    messageList = list(message)
    alfList = list(alf)
    
    reversedListAlf = list(reversed(alfList))
    
    for letter in messageList:

        if letter in alfList:
            # Posició lletre de l'alfabet.
            index = alfList.index(letter)

            # Afegir la lletre pero invertit a l'alfabet invertit
            encrypted += reversedListAlf[index]

    
    return encrypted



if len(sys.argv) > 1:
    ciphertext = sys.argv[1]
    print(encrypt(ciphertext))
else:
    print("Please provide a message as a command line argument.")