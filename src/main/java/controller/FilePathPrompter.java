package controller;

import model.FilePath;
import view.View;

/**
 * Controller class for prompting the user for the
 * absolute path to the .txt file to perform cryptography on.
 */
public class FilePathPrompter {
    private View view;

    public FilePathPrompter(View view) {
        this.view = view;
    }

    /**
     * Prompts the user for an absolute path to the .txt file.
     * Validation is done through the constructor of the FilePath instance.
     *
     * @return An instance of FilePath.
     */
    public FilePath getFilePath() {
        while (true) {
            try {
                String userInput = view.getUserInput("Enter the absolute path to the .txt file: ");
                return new FilePath(userInput);
            } catch (Exception e) {
                view.displayMessage(e.getMessage());
            }
        }
    }

}
