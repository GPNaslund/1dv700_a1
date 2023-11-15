package model;

/**
 * Model class for a CryptographyKey.
 * Holds and validates the key.
 */
public class CryptographyKey {
    private final String key;
    private final EncryptionAlgorithm algorithm;

    public CryptographyKey(EncryptionAlgorithm algorithm, String key) {
        this.algorithm = algorithm;
        this.key = validateKey(key);
    }

    /**
     * Returns the stored key.
     * @return The key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Converts the key in string form to integer form.
     * @return The int value of the key, if able to parse to int.
     */
    public int getKeyValue() {
        try {
            return Integer.parseInt(key);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Key value is not a valid integer: " + key);
        }
    }

    /**
     * Validates the key based on the EncryptionAlgorithm.
     * @param key The key to validate.
     * @return The key if valid.
     */
    private String validateKey(String key) {
        switch (algorithm) {
            case CAESAR_CIPHER:
                return validateNumericKey(key, 0, 25);
            case RAIL_FENCE_CIPHER:
                return validateNumericKey(key, 2, Integer.MAX_VALUE);
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + algorithm.name());
        }
    }

    /**
     * Method for validating a numeric key.
     *
     * @param key The key to validate.
     * @param min The minimum value.
     * @param max The maximum value.
     * @return The key if valid.
     */
    private String validateNumericKey(String key, int min, int max) {
        try {
            int keyValue = Integer.parseInt(key);
            if (keyValue >= min && keyValue <= max) {
                return key;
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Key for " + algorithm.name() + " must be numbers");
        }
        if (algorithm == EncryptionAlgorithm.CAESAR_CIPHER) {
            throw new IllegalArgumentException("Key must be between 0 - 25, try again!");
        }
        if (algorithm == EncryptionAlgorithm.RAIL_FENCE_CIPHER) {
            throw new IllegalArgumentException("Key must be 2 or more, try again!");
        }
        throw new IllegalArgumentException("Invalid key for " + algorithm.name());
    }
}
