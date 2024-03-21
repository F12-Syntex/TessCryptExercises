package com.cybersecurity.tasks;

public class CaesarCipher extends Task {

    public CaesarCipher() {
        super(1);
    }

    @Override
    public String decypher() {
        // Get the ciphertext from the input file
        String input = this.getInput();

        // Try all possible shift values (0 to 25) for a Caesar cipher
        for (int shift = 0; shift < 26; shift++) {
            String result = "";

            // Iterate over each character in the ciphertext
            for (int j = 0; j < input.length(); j++) {
                char c = input.charAt(j);

                // Check if the current character is a letter
                if (Character.isLetter(c)) {
                    // If it's a letter, shift it by the current shift value
                    c = shiftCharacter(c, shift);
                }

                // Append the shifted (or original, if not a letter) character to the result
                // string
                result += c;
            }

            // Check if the decrypted result matches the expected plaintext
            if (this.checkIfSolved(result)) {
                // If it matches, return the decrypted plaintext
                return result;
            }
        }

        // If none of the shift values produced the expected plaintext, return an empty
        // string
        return "";
    }

    public static void main(String[] args) {
        CaesarCipher caesarCipher = new CaesarCipher();
        String ans = caesarCipher.decypher();
        System.out.println(caesarCipher.checkIfSolved(ans));
    }
}
