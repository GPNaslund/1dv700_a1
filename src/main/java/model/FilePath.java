package model;

import java.io.File;

/**
 * Class for holding and validating a file path.
 */
public class FilePath {
    private final File file;

    public FilePath(String path) {
        this.file = new File(path);
        validateFilePath();
    }

    /**
     * Method for validating the file path.
     */
    private void validateFilePath() {
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("File does not exist or is not a regular file: " + file.getName());
        }
        if (!getFileExtension(file).equalsIgnoreCase("txt")) {
            throw new IllegalArgumentException("File is not a .txt file: " + file.getPath());
        }
    }

    /**
     * Method for getting the file extension.
     *
     * @param file The file to check.
     * @return The file extension.
     */
    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf + 1);
    }

    public File getFile() {
        return file;
    }
}
