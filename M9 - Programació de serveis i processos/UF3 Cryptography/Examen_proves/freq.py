import sys


def freq(message):

    abc = 'abcdefghijklmnopqrstuvwxyt'
    letter_count = [0] * len(abc)
    percentages = [0] * len(abc)

    for letter in message:
        if letter in abc:
            index = abc.index(letter)
            letter_count[index] += 1


    total_letters = len(message)


    if total_letters > 0:
        for i in range(len(abc)):
            percentages[i] = (letter_count[i] / total_letters) * 100

    
    for i in range(len(abc)):
        if percentages[i] > 0:
            print(f'{abc[i]}: {percentages[i]:.2f}%')


    max_percentage = max(percentages)
    max_index = percentages.index(max_percentage)

    print()

    print(f"letter 'e' must be letter '{abc[max_index]}'")


if len(sys.argv) > 1:

    ciphtext = sys.argv[1]

    print(freq(ciphtext))

else:
    print("Please provide a message")