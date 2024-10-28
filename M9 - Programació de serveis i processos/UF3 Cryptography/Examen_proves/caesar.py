import sys


def decrypt(message):
    alf = "abcdefghijklmnopqrstuvwxyz"
    vocals = "aeiou"
    decryped = None

    for shift in range(1, 26):
        text = ''

        for letter in message.lower():

            index = alf.index(letter)
            new_index = (index - shift) % 26
            text += alf[new_index]

        vowels_count = 0
        costants_count = 0

        for letter in text:
            if letter in vocals:
                vowels_count += 1
            else:
                costants_count += 1


        vowel_ratio = vowels_count / (vowels_count + costants_count)

        if 0.37 <= vowel_ratio <= 0.43 and (decryped is None):
            decryped = text

    return decryped



if len(sys.argv) > 1:
    ciphtext = sys.argv[1]

    print(decrypt(ciphtext))

else:
    print("Please provide a message")