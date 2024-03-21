package com.cybersecurity.tasks;

public class VigenereCipherWithKey extends Task {

    private final String KEY = "TESSOFTHEDURBERVILLES";

    public VigenereCipherWithKey() {
        super(2);
    }

    @Override
    public String decypher() {
        String input = this.getInput();
        char[] inputStringArr = input.toCharArray();
        char[] inputKeyArr = KEY.toCharArray();

        for (int i = 0; i < inputStringArr.length; i++) {
            if (Character.isLetter(inputStringArr[i])) {
                inputStringArr[i] = (char) ((inputStringArr[i] - inputKeyArr[i % inputKeyArr.length] + 26) % 26 + 'A');
            }
        }

        return new String(inputStringArr).trim();
    }

    public static void main(String[] args) {
        VigenereCipherWithKey vigenereCipherWithKey = new VigenereCipherWithKey();
        String ans = vigenereCipherWithKey.decypher();
        System.out.println(vigenereCipherWithKey.checkIfSolved(ans));
    }

}
