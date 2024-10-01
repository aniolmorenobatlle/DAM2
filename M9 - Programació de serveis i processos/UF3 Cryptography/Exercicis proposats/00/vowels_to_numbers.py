# 00. Create a program in Python language, using the imperative procedural programming paradigm, that encrypts messages by replacing vowel-number (the number one by the vowel a, the number two by the vowel e, the number three by the vowel i, etc). Also create the program that performs the reverse process.


import sys

def encrypt(message):
    ciphertext = ""
    
    for letter in message:
        position = "aeiou".find(letter.lower())

    if position > -1:
        ciphertext += str(position + 1)
    else:
        ciphertext += letter.lower()

    return ciphertext

if len(sys.argv) > 1:
    plaintext = sys.argv[1]
    print(encrypt(plaintext))
else:
    print("Please provide a message as a command line argument.")

