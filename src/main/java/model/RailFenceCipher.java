package model;

/**
 * Class with utility to perform encryption and decryption
 * with the rail fence cipher.
 */
public class RailFenceCipher implements CryptographyAlgorithm {

    /**
     * Method for encrypting with the rail fence cipher algorithm.
     *
     * @param text The text to encrypt.
     * @param key The key to use in the encryption.
     * @return The encrypted text.
     */
    @Override
    public String encrypt(String text, CryptographyKey key) {
        int numRails = key.getKeyValue();
        if (numRails <= 1) return text;

        StringBuilder[] rails = new StringBuilder[numRails];
        for (int i = 0; i < numRails; i++) {
            rails[i] = new StringBuilder();
        }

        int rail = 0;
        boolean down = true;

        for (char c : text.toCharArray()) {
            rails[rail].append(c);
            if (rail == numRails - 1) {
                down = false;
            } else if (rail == 0) {
                down = true;
            }
            rail += down ? 1 : -1;
        }

        StringBuilder encryptedText = new StringBuilder();
        for (StringBuilder railLine : rails) {
            encryptedText.append(railLine.toString());
        }

        return encryptedText.toString();
    }

    /**
     * Method for decrypting with the rail fence cipher algorithm.
     *
     * @param text The text to decrypt
     * @param key The key to use during decrypting.
     * @return The decrypted text.
     */
    @Override
    public String decrypt(String text, CryptographyKey key) {
        int numRails = key.getKeyValue();
        if (numRails <= 1) return text;

        int[] railSizes = new int[numRails];
        boolean down = true;
        int rail = 0;

        for (int i = 0; i < text.length(); i++) {
            railSizes[rail]++;
            if (rail == numRails - 1) {
                down = false;
            } else if (rail == 0) {
                down = true;
            }
            rail += down ? 1 : -1;
        }

        String[] rails = new String[numRails];
        int index = 0;

        for (int i = 0; i < numRails; i++) {
            rails[i] = text.substring(index, index + railSizes[i]);
            index += railSizes[i];
        }

        StringBuilder decryptedText = new StringBuilder();
        rail = 0;
        down = true;
        int[] railPos = new int[numRails];

        for (int i = 0; i < text.length(); i++) {
            decryptedText.append(rails[rail].charAt(railPos[rail]));
            railPos[rail]++;

            if (rail == numRails - 1) {
                down = false;
            } else if (rail == 0) {
                down = true;
            }
            rail += down ? 1 : -1;
        }

        return decryptedText.toString();
    }
}