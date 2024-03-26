package com.cybersecurity.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.cybersecurity.utils.FileUtils;

import lombok.Data;

@Data
public abstract class Task {
    protected int taskNumber;
    protected TextType textType;

    public Task(int taskNumber) {
        this.taskNumber = taskNumber;
        this.textType = TextType.TESS26;
    }

    public abstract String decypher();

    /**
     * Get the input file content
     * 
     * @return the input file content
     */
    public String getInput() {
        String fileName = String.format("cexercise%d.txt", this.taskNumber);
        Path path = FileUtils.getPath("comp5580", "res", "inputfiles", "personal_input", fileName);
        return readFile(path);
    }

    /**
     * get the content of the input file which contains the decyphered text
     * 
     * @return the sample file content
     */
    public String getDecypheredTextSampleInput() {
        Path path = FileUtils.getPath("comp5580", "res", "inputfiles", this.textType.getFileName());
        return readFile(path);
    }

    /**
     * Read the file given the path
     * 
     * @param path the path of the file
     * @return the file content
     */
    private String readFile(Path path) {
        try {
            String fileContent = new String(Files.readAllBytes(path));
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Check if the current string is the solved string
     * 
     * @param current the current string
     * @return true if the current string is the solved string, false otherwise
     */
    public boolean checkIfSolved(String current) {
        return this.getDecypheredTextSampleInput().contains(current);
    }

    /**
     * Shift the character by the given shift
     * 
     * @param c     the character to shift
     * @param shift the shift value
     * @return the shifted character
     */
    protected char shiftCharacter(char c, int shift) {
        char base = Character.isLowerCase(c) ? 'a' : 'A';
        return (char) ((c - base + shift) % 26 + base);
    }
}
