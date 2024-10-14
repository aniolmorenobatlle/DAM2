# 05. Create a program in Python language, using the imperative procedural programming paradigm, that decrypts through brute force the following message:
#                                           drobosckczisxpsvdbkdonkwyxqdrovsoedoxkxdc

# For this, the following must be taken into account:
#       ● The original message has been encrypted with Caesar cipher.
#       ● The alphabet used in the original message is the English alphabet.
#       ● The shift is unknown.
#       ● The number of vowels with respect to the total number of letters in the original message exceeds 37.00%.


import sys


def decrypt(message):
    alphabet = "abcdefghijklmnopqrstuvwxyz"
    vocals = 'aeiou'
    decrypted = None
    
    # Prova tots els possibles desplaçaments de 1 a 25, ja que l'abc hi han 26 lletres
    for shift in range(1, 26):
        text = ''

        # Important posar .lower que sino peta
        for i in message.lower():
            index = alphabet.index(i)
            new_index = (index - shift) % 26
            text += alphabet[new_index]
        
        vowel_count = 0
        constant_count = 0


        # Mirar si es vocal o consonant
        for letter in text:
            if letter in vocals:
                vowel_count += 1
            else:
                constant_count += 1


        # Calcular proporcio de vocals
        vowel_ratio = vowel_count / (vowel_count + constant_count)
        

        # Mirar si està dins del rang de vocals
        if 0.37 <= vowel_ratio <= 0.43 and (decrypted is None):
            decrypted = text

    return decrypted



if len(sys.argv) > 1:
    ciphertext = sys.argv[1]

    print(decrypt(ciphertext))

else:
    print("Please provide the function(decrypt or encrypt) and the message as a command line argument.")