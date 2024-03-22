package com.cybersecurity.tasks;

public class VigenereCipherTask3 extends Task {
    public VigenereCipherTask3() {
        super(3);
    }

    @Override
    public String decypher() {
        String input = this.getInput();
        char[] inputStringArr = input.toCharArray();


        String key = findKey(inputStringArr, 6, 6);
        String cypher = decrypt(inputStringArr, key).trim();

        System.out.println("Key: " + key);
        System.out.println("Plaintext: " + cypher);

        return cypher;
    }

    private String findKey(char[] ciphertext, int minKeyLength, int maxKeyLength) {
        String bestKey = "";
        double bestScore = Double.MIN_VALUE;

        for (int keyLength = minKeyLength; keyLength <= maxKeyLength; keyLength++) {
            StringBuilder key = new StringBuilder();

            for (int i = 0; i < keyLength; i++) {
                int maxFrequency = 0;
                char mostFrequentChar = 'A';

                for (char c = 'A'; c <= 'Z'; c++) {
                    int frequency = 0;
                    for (int j = i; j < ciphertext.length; j += keyLength) {
                        if (ciphertext[j] == c) {
                            frequency++;
                        }
                    }
                    if (frequency > maxFrequency) {
                        maxFrequency = frequency;
                        mostFrequentChar = c;
                    }
                }
                key.append((char) ((mostFrequentChar - 'E' + 26) % 26 + 'A'));
            }

            String decryptedText = decrypt(ciphertext, key.toString());
            double score = calculateScore(decryptedText);

            if (score > bestScore) {
                bestScore = score;
                bestKey = key.toString();
            }
        }

        return bestKey;
    }

    private String decrypt(char[] ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length; i++) {
            char c = ciphertext[i];
            if (Character.isLetter(c)) {
                char keyChar = key.charAt(i % key.length());
                char decryptedChar = (char) ((c - keyChar + 26) % 26 + 'A');
                plaintext.append(decryptedChar);
            } else {
                plaintext.append(c);
            }
        }

        return plaintext.toString();
    }

    private double calculateScore(String decryptedText) {

        String originalText = this.getSampleFile();

        // Count the frequency of each letter in the original text
        int[] originalFrequencyCounts = new int[26];
        int originalTotalLetters = 0;

        for (char c : originalText.toCharArray()) {
            if (Character.isLetter(c)) {
                originalFrequencyCounts[Character.toLowerCase(c) - 'a']++;
                originalTotalLetters++;
            }
        }

        // Calculate the frequency percentage for each letter in the original text
        double[] originalFrequencyPercentages = new double[26];
        for (int i = 0; i < 26; i++) {
            originalFrequencyPercentages[i] = (double) originalFrequencyCounts[i] / originalTotalLetters * 100;
        }

        // Count the frequency of each letter in the decrypted text
        int[] decryptedFrequencyCounts = new int[26];
        int decryptedTotalLetters = 0;

        for (char c : decryptedText.toCharArray()) {
            if (Character.isLetter(c)) {
                decryptedFrequencyCounts[Character.toLowerCase(c) - 'a']++;
                decryptedTotalLetters++;
            }
        }

        // Calculate the frequency percentage for each letter in the decrypted text
        double[] decryptedFrequencyPercentages = new double[26];
        for (int i = 0; i < 26; i++) {
            decryptedFrequencyPercentages[i] = (double) decryptedFrequencyCounts[i] / decryptedTotalLetters * 100;
        }

        // Calculate the score by comparing the frequencies of the decrypted text to the
        // original text
        double score = 0;
        for (int i = 0; i < 26; i++) {
            score += 1 - Math.abs(decryptedFrequencyPercentages[i] - originalFrequencyPercentages[i]) / 100;
        }

        return score;
    }

    public static void main(String[] args) {
        VigenereCipherTask3 vigenereCipherWithKey = new VigenereCipherTask3();
        String ans = vigenereCipherWithKey.decypher();
        System.out.println(vigenereCipherWithKey.checkIfSolved(ans));
    }
}