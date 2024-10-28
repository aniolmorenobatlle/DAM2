import sys


def decrypt(message):
    alf = 'abcdefghijklmnopqrstuvwxyz'
    vowels = 'aeiou'
    decrypted = None

    for shift in range(1, 26):

        text = ''

        for letter in message.lower():

            index = alf.index(letter)
            new_index = (index - shift) % 26
            text += alf[new_index]

        
        vowels_count = 0
        consonants_count = 0

        for letter in text:
            
            if letter in vowels:
                vowels_count += 1
            else:
                consonants_count += 1

        
        vowel_ratio = vowels_count / (vowels_count + consonants_count)

        if 0.37 <= vowel_ratio <= 0.43 and (decrypted is None):
            decrypted = text

    return decrypted





if len(sys.argv) > 1:
    ciphertext = sys.argv[1]

    print(decrypt(ciphertext))


else:
    print("Please provide a message")