package controller;

import model.CryptographyService;
import view.ConsoleMenuView;
import view.View;

/**
 * The main starting point of the application.
 */
public class App {
    public static void main(String[] args) {
        View view = new ConsoleMenuView();
        AlgorithmPrompter algorithmPrompter = new AlgorithmPrompter(view);
        CryptoMethodPrompter cryptoMethodPrompter = new CryptoMethodPrompter(view);
        KeyPrompter keyPrompter = new KeyPrompter(view);
        FilePathPrompter filePathPrompter = new FilePathPrompter(view);
        CryptographyService cryptographyService = new CryptographyService();
        RestartPrompter restartPrompter = new RestartPrompter(view);

        MainController mainController = new MainController(
                view,
                algorithmPrompter,
                cryptoMethodPrompter,
                keyPrompter,
                filePathPrompter,
                cryptographyService,
                restartPrompter
        );

        boolean isActive = mainController.run();
        while (isActive) {
            isActive = mainController.run();
        }
    }
}
