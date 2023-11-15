package controller;

import model.EncryptionAlgorithm;
import view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for prompting the user for the algorithm
 * to use.
 */
public class AlgorithmPrompter {
    private View view;

    public AlgorithmPrompter(View view) {
        this.view = view;
    }

    /**
     * Prompts the user for which algorithm to use.
     * Uses all the values from the ENUM EncryptionAlgorithm to
     * build the menu and return corresponding ENUM.
     *
     * @return The selected EncryptionAlgorithm.
     */
    public EncryptionAlgorithm getEncryptionAlgorithm() {
        EncryptionAlgorithm[] algorithms = EncryptionAlgorithm.values();
        List<String> displayOptions = new ArrayList<>();
        for (int i = 0; i < algorithms.length; i++) {
            if (algorithms[i] != EncryptionAlgorithm.UNKOWN) {
                displayOptions.add((i + 1) + ". " + algorithms[i].toString());
            }
        }
        displayOptions.add("0. EXIT");
        while (true) {
            view.displayList(displayOptions);
            String input = view.getUserInput("Choose an option: ");

            try {
                int choice = Integer.parseInt(input);
                if (choice == 0) {
                    return EncryptionAlgorithm.UNKOWN;
                } else if (choice > 0 && choice <= algorithms.length) {
                    return algorithms[choice - 1];
                } else {
                    view.displayMessage("Invalid option. Please try again!");
                }
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a number.");
            }
        }
    }
}
