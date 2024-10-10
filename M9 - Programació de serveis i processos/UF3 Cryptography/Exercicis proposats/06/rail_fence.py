# 06. Create a program in Python language, using the imperative procedural programming paradigm, that encrypts and decrypts messages using the rail fence cipher with the English alphabet. It must be verified that the arguments of the program execution are acceptable.


def encrypt():
    word = 'wearediscoveredrunatonce'
    word_length = len(word)
    columns = word_length
    rows = 4

    files = []

    for i in range(word_length):

        if len(files) < rows:

            # Crear una fila amb * per a totes les posicions
            row = ['*'] * columns
            
            # Posar la lletra en la posició i.
            row[i] = word[i]


            # Afegim la fila a la llista de files
            files.append(''.join(row))

        else:
            break


    for f in files:
        print(f)


encrypt();