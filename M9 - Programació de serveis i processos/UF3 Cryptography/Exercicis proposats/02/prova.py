import itertools

# The encrypted message
encrypted_message = [14, 11, 22, 23]  # Example encrypted message

# Create a list of letters for the 4x4 Polybius square (using A-P)
letters = [chr(i) for i in range(65, 81)]  # A-P (16 letters)

# Function to decode the message using a given grid
def decode_polybius_4x4(grid, message):
    decrypted_message = ""
    for num in message:
        # Adjusting the formula for 4x4 grid (1-based indexing)
        row, col = divmod(num - 11, 4)  # 11 is the offset for 1-based indexing (11 = A1)
        decrypted_message += grid[row * 4 + col]  # Convert to 0-based index
    return decrypted_message

# Generate all possible 4x4 grids
for grid in itertools.permutations(letters):
    # Decode the message using the current grid
    decoded = decode_polybius_4x4(grid, encrypted_message)
    print(f"Grid: {''.join(grid)} => Decoded Message: {decoded}")


# paraula es: sari