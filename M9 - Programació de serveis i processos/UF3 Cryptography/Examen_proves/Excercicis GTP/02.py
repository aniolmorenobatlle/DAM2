# Exercici 3
# Crea un programa en Python, utilitzant el paradigma de programació orientat a objectes, que encripta i desencripta missatges utilitzant el xifrat ROT13 amb els alfabets anglès, català i espanyol. L’algoritme hauria d'aplicar un desplaçament fix de 13 posicions. Verifica que els arguments de l'execució del programa són acceptables.

# Exemple entrada: Hola món
# Exemple sortida: Uby nàz

import sys


def decrypt(message):
    print()



if len(sys.argv) > 1:

    ciphertext = sys.argv[1]

    print(decrypt(ciphertext))

else:
    print("Please provide a message")