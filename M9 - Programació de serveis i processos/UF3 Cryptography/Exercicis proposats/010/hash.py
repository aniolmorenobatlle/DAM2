# 10. Create a program in Python language, using the object oriented programming paradigm, that hash a message with MD5, SHA-1, SHA-256 and SHA-512. 
# The resulting file must be able to be used by another like a module, the main part of the program has to be the body of if __name__ == '__main__': so that it is not executed when it is used by another file as a module.
# It must be verified that the arguments of the program execution are acceptable.

import sys
import hashlib


class Hash:
    def __init__(self, hash_type, message):
        self.hash_type = hash_type
        self.message = message


    def compute_hash(self):
        if self.hash_type == "md5":
            return hashlib.md5(self.message.encode()).hexdigest()
        elif self.hash_type == "sha1":
            return hashlib.sha1(self.message.encode()).hexdigest()
        elif self.hash_type == "sha256":
            return hashlib.sha256(self.message.encode()).hexdigest()
        elif self.hash_type == "sha512":
            return hashlib.sha512(self.message.encode()).hexdigest()
        else:
            return(f"Error. Function {self.hash_type} is unknown!")



def main():
    if len(sys.argv) != 3:
        print("Please provide the hash that you want to use and the message to encrypt.")
        return
    
    hash_type = sys.argv[1]
    filename = sys.argv[2]

    try:
        with open(filename, 'r') as file:
            content = file.read().strip()

        hasher = Hash(hash_type, content)
        print(hasher.compute_hash())

    except FileNotFoundError:
        print("Fitxer no trobat")


if __name__ == "__main__":
    main()

