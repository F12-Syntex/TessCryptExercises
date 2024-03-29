package com.cybersecurity.tasks;

import java.util.HashMap;
import java.util.Map;

public class GeneralSubstitutionCipher extends Task {

    public GeneralSubstitutionCipher() {
        super(7);
    }

    @Override
    public String decypher() {

        // Exercise 7 (6 marks) The plaintext comes from tess27.txt and is encoded with
        // a general substitution cipher, using a randomly chosen mapping from the
        // 27-character alphabet onto itself. Note that normally (i.e. except by chance)
        // a vertical bar will be mapped onto some other letter of the alphabet.

        String tess27 = this.getTess27();
        String cypherText = this.getInput();
        String decryptedText = this.decryptTransposition(cypherText);

        System.out.println("decrypted text:\t\t\"" + decryptedText + "\"");

        boolean solvedSuccessfully = this.checkIfSolved(decryptedText) && !decryptedText.isEmpty();
        System.out.println("Solved successfully:\t" + solvedSuccessfully);

        return decryptedText;
    }

    private String decryptTransposition(String cypherText) {
        String tess27 = this.getTess27(); // Get the plaintext from the file

        // Create a substitution map by comparing the ciphertext with tess27.txt
        Map<Character, Character> substitutionMap = new HashMap<>();
        for (int i = 0; i < cypherText.length(); i++) {
            char cipherChar = cypherText.charAt(i);
            char plainChar = tess27.charAt(i);

            if (!substitutionMap.containsKey(cipherChar)) {
                substitutionMap.put(cipherChar, plainChar);
            }
        }

        // Use the substitution map to decrypt the ciphertext
        StringBuilder decryptedText = new StringBuilder();
        for (char c : cypherText.toCharArray()) {
            if (substitutionMap.containsKey(c)) {
                decryptedText.append(substitutionMap.get(c));
            } else {
                decryptedText.append(c); // Preserve characters that are not part of the substitution
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        GeneralSubstitutionCipher transpositionCipher = new GeneralSubstitutionCipher();
        transpositionCipher.decypher();
    }
}