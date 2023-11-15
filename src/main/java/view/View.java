package view;

import java.util.List;

/**
 * Represents a view interface.
 */
public interface View {
    void displayHeader(String header);

    String getUserInput(String inputQuestion);

    void displayMessage(String message);

    void displayList(List<String> listToDisplay);
}

