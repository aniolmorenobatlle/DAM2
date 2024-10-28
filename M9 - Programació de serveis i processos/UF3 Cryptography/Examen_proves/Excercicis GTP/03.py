def decrypt_caesar_cipher(ciphertext):
    # Prova totes les posicions de desplaçament
    for shift in range(1, 26):
        decrypted_message = ""

        # Desxifra cada lletra
        for char in ciphertext:
            if char.isalpha():  # Comprova si és una lletra
                # Troba la lletra original
                shifted = ord(char) - shift
                if char.islower():
                    if shifted < ord('a'):
                        shifted += 26
                elif char.isupper():
                    if shifted < ord('A'):
                        shifted += 26
                
                decrypted_message += chr(shifted)
            else:
                decrypted_message += char  # Manté els caràcters no alfabètics

        print(f"Shift {shift}: {decrypted_message}")

# Missatge xifrat
ciphertext = "wkhuh duh pdqb hqfulswlrq dojrulwkpv"
decrypt_caesar_cipher(ciphertext)
