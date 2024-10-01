import random


# Crear una llista.
firstList = [1, 2, 3, 4, 5]
print(firstList)

print();
print();


# Recorrer una llista.
for x in firstList:
    print(x)

print();
print();



# Afegir elements a una llista.
firstList.append(6)
print("Elements afegit: ", firstList)

print();
print();


# Divinar el numero.
guess = int(input("Introdueix un numero: "))

correct_number = random.randint(1, 10)

if guess == correct_number:
    print("Has guanyat!")
else:
    print("Has perdut! El numero correcte era: ", correct_number)

print();
print();


# Convertir de text a binari.
text = open("text.txt", "r")

convert = text.read()

convert = ''.join(format(ord(i), '08b') for i in convert)

binary_text = open("binary_text.txt", "w")

binary_text.write(convert)


print("Text convertit a binari: \n", convert)

print();
print();



# Convertir de binari a text.
binary_text = open("binary_text.txt", "r")

binary_text = binary_text.read()

n = int(binary_text, 2)

text2 = n.to_bytes((n.bit_length() + 7) // 8, 'big').decode()

print("Binari convertit a text: \n", text2)

print();
print();
