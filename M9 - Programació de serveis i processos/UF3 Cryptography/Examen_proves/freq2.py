import sys


def freq(message):
    alf = 'abcdefghijklmnopqrstuvwxyz'
    letter_count = [0] * len(alf)
    percentages = [0] * len(alf)

    for letter in message:
        if letter in alf:
            index = alf.index(letter)
            letter_count[index] += 1

    
    total_letter = len(message)


    if total_letter > 0:
        for letter in range(len(alf)):
            percentages[letter] = (letter_count[letter] / total_letter) * 100


    for i in range(len(alf)):
        if percentages[i] > 0:
            print(f"{alf[i]}: {percentages[i]:.2f}%")

    
    max_percentage = max(percentages)
    max_index = percentages.index(max_percentage)

    print()

    print(f"letter 'e' must be letter '{alf[max_index]}'")
    


if len(sys.argv) > 1:
    ciphertext = sys.argv[1]


    try:
        with open(ciphertext, 'r') as file:
            content = file.read().strip()

        freq(content)

    except FileNotFoundError:
        print("File not found")



else:
    print("Please provide the function(decrypt or encrypt) and the message as a command line argument.")