# 05. Create a program in Python language, using the imperative procedural programming paradigm, that decrypts through brute force the following message:
#                                           drobosckczisxpsvdbkdonkwyxqdrovsoedoxkxdc

# For this, the following must be taken into account:
#       ● The original message has been encrypted with Caesar cipher. 
#       ● The alphabet used in the original message is the English alphabet. 
#       ● The shift is unknown.
#       ● The number of vowels with respect to the total number of letters in the original message exceeds 37.00%.



def decrypt ():
    word = "drobosckczisxpsvdbkdonkwyxqdrovsoedoxkxdc"
    vowels = "aeiou"
    vowels_count = 0

    for letter in word:
        if letter in vowels:
            vowels_count += 1

    print("Vocals en la paraula original:", vowels_count)
    
    if (vowels_count / len(word)) > 0.37:
        for i in range(26):
            decrypted = ''
            



decrypt()