package model;

/**
 * Class that contains functionality for encrypting and decrypting text
 * with the Caesar Cipher.
 */
public class CaesarCipher implements CryptographyAlgorithm {

    @Override
    public String encrypt(String text, CryptographyKey key) {
        return caesarCipher(text, key.getKeyValue(), true);
    }

    @Override
    public String decrypt(String text, CryptographyKey key) {
        return caesarCipher(text, key.getKeyValue(), false);
    }

    private String caesarCipher(String text, int shift, boolean encrypt) {
        // Determine the direction of the shift (right for encryption, left for decryption)
        shift = encrypt ? shift : -shift;
        StringBuilder result = new StringBuilder();

        // Iterate over each character in the text
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                // Determine the starting point ('a' for lowercase, 'A' for uppercase)
                char base = Character.isLowerCase(character) ? 'a' : 'A';

                // Calculate the offset for each character from the base.
                // The modulo 26 ensures that the character remains within the alphabet range.
                int offset = (character - base + shift) % 26;

                // In case of negative shifts or crossing 'a' or 'A', adjust the offset
                if (offset < 0) offset += 26;

                // Add the shifted character to the result
                result.append((char) (base + offset));
            } else {
                // If the character is not a letter, leave it unchanged
                result.append(character);
            }
        }

        // Return the transformed text
        return result.toString();
    }
}