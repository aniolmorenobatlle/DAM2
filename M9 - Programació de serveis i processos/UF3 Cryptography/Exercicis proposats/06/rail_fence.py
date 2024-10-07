# 06. Create a program in Python language, using the imperative procedural programming paradigm, that encrypts and decrypts messages using the rail fence cipher with the English alphabet. It must be verified that the arguments of the program execution are acceptable.


def encrypt():
    word = 'wearediscoveredrunatonce'
    word_length = len(word)
    rails = 4

    for _ in range(rails):
        print('*' * word_length)

    

encrypt();