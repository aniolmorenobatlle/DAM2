# 00. Create a program in Python language, using the imperative procedural programming paradigm, that encrypts messages by replacing vowel-number (the number one by the vowel a, the number two by the vowel e, the number three by the vowel i, etc). Also create the program that performs the reverse process.


import sys

def decrypt(message):
    plaintext = ""

    for letter in message:
        position = "12345".find(letter)

    if position > -1:
        plaintext += "aeiou"[position:position + 1]
    else:
        plaintext += letter.lower()
    
    return plaintext


if len(sys.argv) > 1:
    ciphertext = sys.argv[1]
    print(decrypt(ciphertext))
else:
    print("Please provide a message as a command line argument.")