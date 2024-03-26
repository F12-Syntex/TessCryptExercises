package com.cybersecurity.tasks;

import java.util.HashMap;
import java.util.Map;

public class VigenereCipherTask4 extends Task {
    public VigenereCipherTask4() {
        super(4);
    }

    @Override
    public String decypher() {
        String input = this.getInput();
        char[] inputStringArr = input.toCharArray();

        for (int i = 4; i < 6; i++) {
            String key = this.findKey(inputStringArr, i);
            String decryptedText = this.decryptVigenere(inputStringArr, key);
            if(this.checkIfSolved(decryptedText)) {
                System.out.println("Key: " + key);
                System.out.println("Decrypted text: " + decryptedText);
                return decryptedText;
            }
        }

        throw new RuntimeException("Failed to decrypt the text");
    }

    private String decryptVigenere(char[] inputStringArr, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < inputStringArr.length; i++) {
            char encryptedChar = inputStringArr[i];
            if (Character.isLetter(encryptedChar)) {
                char keyChar = key.charAt(i % keyLength);
                int shift = keyChar - 'A';
                char decryptedChar = (char) ((encryptedChar - 'A' - shift + 26) % 26 + 'A');
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(encryptedChar);
            }
        }

        return decryptedText.toString();
    }

    private String findKey(char[] inputStringArr, int length) {
        String key = "";
        double[] englishFrequencies = { 0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015,
                0.06094, 0.06966, 0.00153, 0.00772, 0.04025, 0.02406, 0.06749,
                0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 0.02758,
                0.00978, 0.02360, 0.00150, 0.01974, 0.00074 };

        for (int i = 0; i < length; i++) {
            Map<Character, Double> groupFrequencies = new HashMap<>();
            for (int j = i; j < inputStringArr.length; j += length) {
                char c = inputStringArr[j];
                if (groupFrequencies.containsKey(c)) {
                    groupFrequencies.put(c, groupFrequencies.get(c) + 1);
                } else {
                    groupFrequencies.put(c, 1.0);
                }
            }

            for (Map.Entry<Character, Double> entry : groupFrequencies.entrySet()) {
                groupFrequencies.put(entry.getKey(), entry.getValue() / (inputStringArr.length / length));
            }

            int shift = findBestShift(groupFrequencies, englishFrequencies);
            key += (char) (shift + 'A');
        }

        return key;
    }

    private int findBestShift(Map<Character, Double> groupFrequencies, double[] englishFrequencies) {
        double bestScore = Double.MAX_VALUE;
        int bestShift = 0;

        for (int shift = 0; shift < 26; shift++) {
            double score = 0;
            for (int i = 0; i < 26; i++) {
                char c = (char) ((i + shift) % 26 + 'A');
                double groupFrequency = groupFrequencies.getOrDefault(c, 0.0);
                score += Math.abs(groupFrequency - englishFrequencies[i]);
            }
            if (score < bestScore) {
                bestScore = score;
                bestShift = shift;
            }
        }

        return bestShift;
    }

    public static void main(String[] args) {
        VigenereCipherTask4 vigenereCipherWithKey = new VigenereCipherTask4();
        String ans = vigenereCipherWithKey.decypher();
        System.out.println(vigenereCipherWithKey.checkIfSolved(ans));
    }
}