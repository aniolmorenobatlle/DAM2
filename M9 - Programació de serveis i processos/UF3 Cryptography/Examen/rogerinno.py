import sys
import random



def xifrar(message):

    alf = 'abcdefghijklmnopqrstuvwyz '
    list_alf = list(alf)
    list_message = list(message)
    numbers = []


    for i in range(len(list_alf)):
        numbers.append(f"{random.randrange(10)}0{i + 1}")


    # for i in range(len(numbers)):
    #     print(f"{i}. {numbers[i]}")


    # for i in range(len(message)):
        # print(f"{i}. {message[i]}")


    # for j in range(len(list_alf)):
        # print(f"{j}. {list_alf[j]}")


    for letter in list_message:
        
        for i in list_alf:

            if letter == i:
                print(letter)


    return numbers



def desxifrar(message):
    return


if len(sys.argv) > 0:
    function = sys.argv[1]
    ciphertext = sys.argv[2]


    if function == 'x':
        print(xifrar(ciphertext))

    elif function == 'd':
        print(desxifrar(ciphertext))

    else:
        print("No s'ha indicat l'operació.")

else:
    print("Propocioneu els arguments requirits.")