package controller;

import model.*;
import view.View;

/**
 * The main controller for the application flow
 * and holds the data needed to perform the cryptography.
 */
public class MainController {
    private View view;
    private AlgorithmPrompter algorithmPrompter;

    private CryptoMethodPrompter cryptoPrompter;

    private KeyPrompter keyPrompter;

    private FilePathPrompter filePathPrompter;

    private CryptographyService cryptographyService;

    private RestartPrompter restartPrompter;

    public MainController(
            View view,
            AlgorithmPrompter algorithmPrompter,
            CryptoMethodPrompter cryptoPrompter,
            KeyPrompter keyPrompter,
            FilePathPrompter filePathPrompter,
            CryptographyService cryptographyService,
            RestartPrompter restartPrompter
    ) {
        this.view = view;
        this.algorithmPrompter = algorithmPrompter;
        this.cryptoPrompter = cryptoPrompter;
        this.keyPrompter = keyPrompter;
        this.filePathPrompter = filePathPrompter;
        this.cryptographyService = cryptographyService;
        this.restartPrompter = restartPrompter;
    }

    /**
     * The main method of the controller. Will start a
     * series of prompts through specific controllers.
     *
     * @return A boolean indicating if the app should be active or not.
     */
    public boolean run() {
        view.displayHeader("Encryption and decryption app");
        EncryptionAlgorithm algorithm = algorithmPrompter.getEncryptionAlgorithm();
        if (algorithm == EncryptionAlgorithm.UNKOWN) {
            return false;
        }
        CryptographyMethod method = cryptoPrompter.getCryptographyMethod();
        if (method == CryptographyMethod.UNKOWN) {
            return false;
        }
        CryptographyKey key = keyPrompter.getKey(algorithm);
        FilePath filePath = filePathPrompter.getFilePath();
        try {
            cryptographyService.execute(algorithm, method, key, filePath);
            view.displayMessage("Action performed successfully!");
            return restartPrompter.askForRestart();
        } catch (Exception e) {
            view.displayMessage("Action failed!");
            view.displayMessage(e.getMessage());
            return restartPrompter.askForRestart();
        }
    }
}
