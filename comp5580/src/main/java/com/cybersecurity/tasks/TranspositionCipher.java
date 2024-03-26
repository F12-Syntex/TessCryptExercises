package com.cybersecurity.tasks;

public class TranspositionCipher extends Task {

    public TranspositionCipher() {
        super(5);
    }

    @Override
    public String decypher() {
        String cypherText = this.getInput();
        String decryptedText = "";

        for (int columns = 4; columns <= 6; columns++) {
            decryptedText = this.decryptTransposition(cypherText, columns);
            
            if (this.checkIfSolved(decryptedText)) {
                break; // If the text is correctly decrypted, break out of the loop.
            }
        }

        System.out.println("decrypted text:\t\t\"" + decryptedText + "\"");
        boolean solvedSuccessfully = !decryptedText.isEmpty();
        System.out.println("Solved successfully:\t" + solvedSuccessfully);

        return decryptedText;
    }

    private String decryptTransposition(String cypherText, int columns) {
        int rows = (int) Math.ceil((double) cypherText.length() / columns);
        char[][] grid = new char[rows][columns];
        int index = 0;

        String decryptedText = "";

        // Fill the grid column by column
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
                if (index < cypherText.length()) {
                    grid[r][c] = cypherText.charAt(index++);
                } else {
                    grid[r][c] = ' '; 
                }
            }
        }

        // Read the grid row by row to get the decrypted text
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                decryptedText += grid[r][c];
            }
        }

        return decryptedText.trim();
    }

    public static void main(String[] args) {
        TranspositionCipher transpositionCipher = new TranspositionCipher();
        transpositionCipher.decypher();
    }
}