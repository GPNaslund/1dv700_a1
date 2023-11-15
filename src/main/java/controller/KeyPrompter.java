package controller;

import model.CryptographyKey;
import model.EncryptionAlgorithm;
import view.View;

/**
 * Controller class for prompting the user for the
 * encryption key to use with the cryptography algorithm.
 */
public class KeyPrompter {
    private View view;

    public KeyPrompter(View view) {
        this.view = view;
    }

    /**
     * Method for prompting the user for a key to use with the algorithm.
     * @param algorithm The algorithm that is going to use the key. Based
     *                  on the algorithm different prompts appear.
     * @return An instance of CryptographyKey that validates the input.
     */
    public CryptographyKey getKey(EncryptionAlgorithm algorithm) {
        while (true) {
            String input;

            if (algorithm == EncryptionAlgorithm.CAESAR_CIPHER) {
                input = view.getUserInput("Enter the shift key for the Caesar Cipher (0-25): ");
            } else if (algorithm == EncryptionAlgorithm.RAIL_FENCE_CIPHER) {
                input = view.getUserInput("Enter the number of rails for the Rail Fence Cipher (min 2): ");
            } else {
                view.displayMessage("Unknown encryption algorithm.");
                input = "";
            }

            try {
                return new CryptographyKey(algorithm, input);
            } catch (IllegalArgumentException e) {
                view.displayMessage(e.getMessage());
            }
        }
    }

}
