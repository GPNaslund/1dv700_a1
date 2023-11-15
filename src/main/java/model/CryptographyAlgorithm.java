package model;

/**
 * Interface for the different cryptography algorithms.
 */
public interface CryptographyAlgorithm {
    String encrypt(String text, CryptographyKey key);
    String decrypt(String text, CryptographyKey key);
}
