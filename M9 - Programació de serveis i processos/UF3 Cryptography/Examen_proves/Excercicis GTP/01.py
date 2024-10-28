# Exercici 1

# Crea un programa en Python, utilitzant el paradigma de programació procedimental, que encripta i desencripta missatges amb el mètode de transposició simple de text. El missatge es transforma invertint l'ordre de les paraules, però sense canviar les lletres dins de cada paraula. Verifica que els arguments de l'execució del programa són acceptables.

# Exemple entrada: Hola com estàs?
# Exemple sortida: estàs? com Hola

import sys


def decrypt(message):

    message_split = message.split(' ')
    message_split.reverse()

    return " ".join(message_split)


if len(sys.argv) > 1:

    ciphertext = sys.argv[1]

    print(decrypt(ciphertext))


else:
    print("Please provide a message")