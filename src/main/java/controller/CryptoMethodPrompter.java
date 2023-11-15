package controller;

import model.CryptographyMethod;
import model.EncryptionAlgorithm;
import view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for prompting the user for which
 * cryptography method (encrypt/decrypt) to perform.
 */
public class CryptoMethodPrompter {
    private View view;

    public CryptoMethodPrompter(View view) {
        this.view = view;
    }

    /**
     * Prompts the user for which cryptography method to use.
     * Uses the values in the ENUM CryptographyMethod to generate
     * the menu and returns corresponding ENUM.
     *
     * @return The selected CryptographyMethod.
     */
    public CryptographyMethod getCryptographyMethod() {
        CryptographyMethod[] methods = CryptographyMethod.values();
        List<String> displayOptions = new ArrayList<>();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i] != CryptographyMethod.UNKOWN) {
                displayOptions.add((i + 1) + ". " + methods[i].toString());
            }
        }
        displayOptions.add("0. EXIT");
        while (true) {
            view.displayList(displayOptions);
            String input = view.getUserInput("Choose an option: ");

            try {
                int choice = Integer.parseInt(input);
                if (choice == 0) {
                    return CryptographyMethod.UNKOWN;
                } else if (choice > 0 && choice <= methods.length) {
                    return methods[choice - 1];
                } else {
                    view.displayMessage("Invalid option. Please try again!");
                }
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a number.");
            }
        }
    }
}
