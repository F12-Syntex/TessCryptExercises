package com.cybersecurity.tasks;

/**
 * Enum for the text type
 */
public enum TextType {
    TESS("tess.txt"), TESS26("tess26.txt"), TESS27("tess27.txt");

    private String fileName;

    /**
     * Constructor for the text type 
     * @param fileName the file name of the text type
     */
    TextType(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get the file name of the text type
     * @return the file name
     */
    public String getFileName() {
        return this.fileName;
    }
}
