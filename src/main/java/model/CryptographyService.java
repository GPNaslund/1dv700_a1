package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Service class for providing cryptography functionality.
 */
public class CryptographyService {

    /**
     * Main method for executing the cryptography actions.
     *
     * @param algorithm The algorithm to use.
     * @param method The method to perform.
     * @param key The key that the algorithm should use.
     * @param filePath The file path to the .txt file.
     * @throws IOException If reading/writing to file.
     */
    public void execute(
            EncryptionAlgorithm algorithm,
            CryptographyMethod method,
            CryptographyKey key,
            FilePath filePath) throws IOException {

        CryptographyAlgorithm algorithmImplementation = getAlgorithmInstance(algorithm);
        String content = readFileContent(filePath);
        String result;
        if (method == CryptographyMethod.ENCRYPT) {
            result = algorithmImplementation.encrypt(content, key);
        } else {
            result = algorithmImplementation.decrypt(content, key);
        }

        writeFileContent(filePath, result);

    }

    /**
     * Method for getting the correct algorithm instance.
     *
     * @param algorithm The EncryptionAlgorithm ENUM that correlates to an instance.
     * @return The cryptography algorithm instance.
     */
    private CryptographyAlgorithm getAlgorithmInstance(EncryptionAlgorithm algorithm) {
        switch (algorithm) {
            case CAESAR_CIPHER:
                return new CaesarCipher();
            case RAIL_FENCE_CIPHER:
                return new RailFenceCipher();
            default:
                throw new IllegalArgumentException("Invalid encryption algorithm " + algorithm.toString());
        }
    }

    /**
     * Method for reading the files content.
     *
     * @param filePath The path to the file.
     * @return The content of the file.
     * @throws IOException If I/O operation fails.
     */
    private String readFileContent(FilePath filePath) throws IOException {
        return Files.readString(Paths.get(filePath.getFile().getAbsolutePath()));
    }

    /**
     * Method for writing to file.
     *
     * @param filePath The path to the file.
     * @param content The content to write.
     * @throws IOException If any I/O operation fails.
     */
    private void writeFileContent(FilePath filePath, String content) throws IOException {
        Files.writeString(Paths.get(filePath.getFile().getAbsolutePath()), content);
    }
}
