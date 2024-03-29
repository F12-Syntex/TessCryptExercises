package com.cybersecurity.tasks;

public class TranspositionCipher2 extends Task {

    public TranspositionCipher2() {
        super(6);
    }

    @Override
    public String decypher() {
        String cypherText = this.getInput();
        String decryptedText = this.decryptTransposition(cypherText);

        System.out.println("decrypted text:\t\t\"" + decryptedText + "\"");

        boolean solvedSuccessfully = this.checkIfSolved(decryptedText) && !decryptedText.isEmpty();
        System.out.println("Solved successfully:\t" + solvedSuccessfully);

        return decryptedText;
    }

    private String decryptTransposition(String cypherText) {
        String decryptedText = "";

        int numColumns = 6;
        int numRows = (int) Math.ceil((double) cypherText.length() / numColumns);

        // Create a 2D char array to represent the grid
        char[][] grid = new char[numRows][numColumns];

        // Fill the grid column-wise with the ciphertext
        int index = 0;
        for (int col = 0; col < numColumns; col++) {
            for (int row = 0; row < numRows; row++) {
                if (index < cypherText.length()) {
                    grid[row][col] = cypherText.charAt(index);
                    index++;
                } else {
                    grid[row][col] = ' '; // Fill empty cells with spaces
                }
            }
        }

        // Try different column permutations
        int[] columnOrder = { 0, 1, 2, 3, 4, 5 };
        do {
            // Read the plaintext row-wise from the grid based on the column order
            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < numRows; row++) {
                for (int col : columnOrder) {
                    sb.append(grid[row][col]);
                }
            }

            decryptedText = sb.toString().trim(); // Remove trailing spaces

            // Check if the decrypted text matches the expected plaintext
            if (this.checkIfSolved(decryptedText)) {
                return decryptedText;
            }
        } while (nextPermutation(columnOrder));

        return decryptedText;
    }

    private boolean nextPermutation(int[] array) {
        // Find the first pair of adjacent elements where the first element is smaller
        // than the second
        int i = array.length - 2;
        while (i >= 0 && array[i] >= array[i + 1]) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        // Find the smallest element in the subarray array[i+1:] that is greater than
        // array[i]
        int j = array.length - 1;
        while (j > i && array[j] <= array[i]) {
            j--;
        }

        // Swap array[i] and array[j]
        swap(array, i, j);

        // Reverse the subarray array[i+1:]
        reverse(array, i + 1, array.length - 1);

        return true;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void reverse(int[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        TranspositionCipher2 transpositionCipher = new TranspositionCipher2();
        transpositionCipher.decypher();
    }
}