# 07. Create a program in Python language, using the imperative procedural programming paradigm, that encrypts and decrypts messages using the columnar cipher. It must be verified that the arguments of the program execution are acceptable.


import sys


def encrypt():
    alphabet = "abcdefghijklmnopqrstuvwxyz"
    word = 'theskyisblue'
    key = 'cat'
    key_list = list(key)

    print('plaintext: ' + word)
    print('key: ' + key)

    print()


    # \t per fer un espai de tabulador.
    print('\t'.join(key_list))


    # Crear una llista per acumular les posicions
    positions = []

    for i in key:

        # Sumem 1 per començar des de 1
        position = alphabet.index(i) + 1
        
        # Convertim a string per poder fer un join per imprimir
        positions.append(str(position))

    

    print('\t'.join(positions))


    position_word = []

    # Recorrem la paraula en blocs de 3 lletres
    for i in range(0, len(word), 3):

        # Afegim les tres lletres corresponents a la llista
        position_word.append('\t'.join(word[i:i + 3])) # Es per agafa les 3 primeres lletres, ex: (word[0:3])  sortida: "the"



    # Unim els blocs amb un salt de línia
    print('\n'.join(position_word))    

    print()


    key_list.sort()

    print(key_list)



encrypt();
