package controller;

import view.View;

/**
 * Controller class for prompting the user if
 * the app should restart.
 */
public class RestartPrompter {
    private View view;

    public RestartPrompter(View view) {
        this.view = view;
    }

    /**
     * Method for prompting the user if the application
     * should restart or not.
     *
     * @return A boolean indicating if the application should restart/be active or
     * not.
     */
    public boolean askForRestart() {
        while (true) {
            String userInput = view.getUserInput("Do you want to start again? y/n");
            if (userInput.equals("y")) {
                return true;
            } else if (userInput.equals("n")) {
                return false;
            } else {
                view.displayMessage("Input must be y or n, try again!");
            }
        }
    }
}
