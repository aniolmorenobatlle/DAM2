import sys


def atbash(message):

    decrypted = ''

    abc = 'abcdefghijklmnopqrstvwxyz'

    list_message = list(message)
    list_abc = list(abc)

    reversed_list_abc = list(reversed(list_abc))

    for letter in list_message:

        if letter in reversed_list_abc:

            index = reversed_list_abc.index(letter)

            decrypted += abc[index]

    return decrypted


if len(sys.argv) > 1:

    ciphertext = sys.argv[1]

    print(atbash(ciphertext))

else:
    print("Please provide a message")