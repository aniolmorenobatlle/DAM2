# 04. Create a program in Python language, using the imperative procedural programming paradigm, that analyzes the frequency of letters in an encrypted message (original message is written in the English language) using a substitution algorithm discovers the letter e. It must be verified that the arguments of the program execution are acceptable.


import sys



def frequency(word):
    alphabet = "abcdefghijklmnopqrstuvwxyz"
    letters_count = [0] * len(alphabet)
    percentages = [0] * len(alphabet)

    # Contar cada quan es repeteix la lletre
    for i in word:
        if i in alphabet:
            index = alphabet.index(i)
            letters_count[index] += 1


    # Calcular percentatge que surt cada lletre
    total_letters = len(word)

    if total_letters > 0:
        for i in range(len(alphabet)):
            percentages[i] = (letters_count[i] / total_letters) * 100


    # Mostrem nomes lletres que surten a la paraula
    for i in range(len(alphabet)):
        if percentages[i] > 0:
            print(f'{alphabet[i]}: {percentages[i]:.2f}%')


    # Trobar la lletre que mes es repeteix
    max_percentage = max(percentages)
    max_index = percentages.index(max_percentage)

    print()

    print(f"letter 'e' must be letter '{alphabet[max_index]}'")




if len(sys.argv) > 1:
    ciphertext = sys.argv[1]

    frequency(ciphertext)

else:
    print("Please provide the function(decrypt or encrypt) and the message as a command line argument.")