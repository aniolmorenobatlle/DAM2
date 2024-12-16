import os

for dirpath, dirnames, filenames in os.walk("./text-files/"):
    for filename in filenames:
        file = os.path.join(dirpath, filename)
        vowels = {"a": 0, "e": 0, "i": 0, "o": 0, "u": 0}

        with open(file) as f:
            data = f.read()

        for c in data:
            if c in vowels:
                vowels[c] += 1

        with open(file, "a") as f:
            f.write(f"{str(vowels)}\n")
