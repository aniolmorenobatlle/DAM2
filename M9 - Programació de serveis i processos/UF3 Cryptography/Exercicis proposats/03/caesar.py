# 03. Create a program in Python language, using the object oriented programming paradigm, that encrypts and decrypts messages using the Caesar cipher with various alphabets (English, Catalan and Spanish). It must be verified that the arguments of the program execution are acceptable


import sys


class Caesar:
    def __init__(self, alphabet):
        self.alf = list(alphabet)


    def encrypt(self, message, space_number):
        encrypted = ""

        for letter in message:
            if letter in self.alf:
                encrypted += self.alf[(self.alf.index(letter) + space_number) % len(self.alf)] # El % es perque si arriba al final de l'abcedari que torni a començar.
        return encrypted


    def decrypt(self, message, space_number):
        decrypted = ""

        for letter in message:
            if letter in self.alf:
                decrypted += self.alf[(self.alf.index(letter) - space_number) % len(self.alf)] # El % es perque si arriba al final de l'abcedari que torni a començar.
        return decrypted


class English(Caesar):
    def __init__(self):
        super().__init__("abcdefghijklmnopqrstuvwxyz")


class Catalan(Caesar):
    def __init__(self):
        super().__init__("abcçdefghijklmnopqrstuvwxyz")


class Spanish(Caesar):
    def __init__(self):
        super().__init__("abcdefghijklmnñopqrstuvwxyz")



# Funcions auxiliars per a la interfície
def encryptEng(message, space_number):
    return English().encrypt(message, space_number)

def decryptEng(message, space_number):
    return English().decrypt(message, space_number)

def encryptCat(message, space_number):
    return Catalan().encrypt(message, space_number)

def decryptCat(message, space_number):
    return Catalan().decrypt(message, space_number)

def encryptEsp(message, space_number):
    return Spanish().encrypt(message, space_number)

def decryptEsp(message, space_number):
    return Spanish().decrypt(message, space_number)



if len(sys.argv) > 1:
    language = sys.argv[1]
    function = sys.argv[2]
    ciphertext = sys.argv[3]
    space_number = int(sys.argv[4])

    if language == "eng":
        if function == "e":
            print(encryptEng(ciphertext, space_number))
        elif function == "d":
            print(decryptEng(ciphertext, space_number))
    
    elif language == "cat":
        if function == "e":
            print(encryptCat(ciphertext, space_number))
        elif function == "d":
            print(decryptCat(ciphertext, space_number))
    
    elif language == "esp":
        if function == "e":
            print(encryptEsp(ciphertext, space_number))
        elif function == "d":
            print(decryptEsp(ciphertext, space_number))

else:
    print("Please provide the language, the function(decrypt or encrypt), the message and the space number as a command line argument.")