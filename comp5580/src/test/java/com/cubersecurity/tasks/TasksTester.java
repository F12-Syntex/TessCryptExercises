package com.cubersecurity.tasks;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cybersecurity.tasks.CaesarCipher;
import com.cybersecurity.tasks.VigenereCipherWithKey;

public class TasksTester extends GenericTest {
    @Test
    public void verifyCaesarCipher() {
        CaesarCipher caesarCipher = new CaesarCipher();
        String decypher = caesarCipher.decypher();
        String originalText = this.readFile(TestType.TESS26);

        assertTrue(originalText.contains(decypher));
    }

    @Test
    public void verifyVigenereCipherWithKey() {
        VigenereCipherWithKey vigenereCipherWithKey = new VigenereCipherWithKey();
        String decypher = vigenereCipherWithKey.decypher();
        String originalText = this.readFile(TestType.TESS26);

        assertTrue(originalText.contains(decypher));
    }
}
