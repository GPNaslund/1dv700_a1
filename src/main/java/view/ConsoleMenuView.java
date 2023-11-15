package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Represents a Console Menu View.
 */
public class ConsoleMenuView implements View {

    /**
     * Method for displaying a header.
     *
     * @param header The header to display.
     */
    @Override
    public void displayHeader(String header) {
        System.out.println("=== " + header.toUpperCase() + " ===");
    }

    /**
     * Method for getting the user input.
     *
     * @param inputQuestion The question to use when prompting for input.
     * @return The user input.
     */
    @Override
    public String getUserInput(String inputQuestion) {
        BufferedReader reader = new
                BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        try {
            System.out.println(inputQuestion);
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error reading input", e);
        }
    }

    /**
     * Method for displaying a message.
     *
     * @param message The message to display.
     */
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Method for displaying a list of strings.
     *
     * @param listToDisplay The list of strings to display.
     */
    @Override
    public void displayList(List<String> listToDisplay) {
        for (String str : listToDisplay) {
            System.out.println(str);
        }
    }
}
