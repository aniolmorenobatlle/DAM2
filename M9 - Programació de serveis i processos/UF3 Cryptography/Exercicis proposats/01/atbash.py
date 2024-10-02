# 01. Create a program in Python language, using the imperative procedural programming paradigm, that encrypts and decrypts messages using the Atbash encryption method with the English alphabet.


import sys
from string import ascii_lowercase as alf


def atbash(message):
    decrypted = ""
        
    messageList = list(message)
    alfList = list(alf)
    
    reversedListAlf = list(reversed(alfList))
    
    for letter in messageList:

        if letter in reversedListAlf:
            # Posició lletre de l'alfabet.
            index = reversedListAlf.index(letter)

            # Afegir la lletre pero invertit a l'alfabet invertit
            decrypted += alf[index]

    
    return decrypted



if len(sys.argv) > 1:
    ciphertext = sys.argv[1]
    print(atbash(ciphertext))
else:
    print("Please provide a message as a command line argument.")