package algos;

import ciphers.algos.KeyedVigenere;
import org.junit.Test;

import static junitx.framework.Assert.assertEquals;

public class KeyedVigenereTest {
    KeyedVigenere vigenere;

    @Test
    public void keyIsChangedCorrectlyToUpperCase() {
        vigenere = new KeyedVigenere("keyToUpperCase", false, false, false);
        assertEquals("KEYTOUPPERCASE", vigenere.getKey());
    }

    @Test
    public void duplicateAlphabetAreRemovedFromKey() {
        vigenere = new KeyedVigenere("alfohmelldftssa", false, false, false);
        vigenere.organiseAlphabet();
        String correct = "ALFOHMEDTS";
        assertEquals(correct, vigenere.getKey());
    }

    @Test
    public void keyIsReversedCorrectly() {
        vigenere = new KeyedVigenere("appletreecat", true, false, false);
        vigenere.organiseAlphabet();
        String correct = "TACERLP";
        assertEquals(correct, vigenere.getKey());
    }

    @Test
    public void alphabetAreReversedCorrectly() {
        vigenere = new KeyedVigenere("", false, true, false);
        vigenere.organiseAlphabet();
        String correct = "ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba";
        String alphInString = checkAlphabetArray();
        assertEquals(correct, alphInString);
    }

    @Test
    public void noChoicesPicked() {
        vigenere = new KeyedVigenere("appletreecat", false, false, false);
        vigenere.organiseAlphabet();
        String correct = "APLETRCBDFGHIJKMNOQSUVWXYZapletrcbdfghijkmnoqsuvwxyz";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void keyInReverse() {
        vigenere = new KeyedVigenere("appletreecat", true, false, false);
        vigenere.organiseAlphabet();
        String correct = "TACERLPBDFGHIJKMNOQSUVWXYZtacerlpbdfghijkmnoqsuvwxyz";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void alphabetInReverse() {
        vigenere = new KeyedVigenere("appletreecat", false, true, false);
        vigenere.organiseAlphabet();
        String correct = "APLETRCZYXWVUSQONMKJIHGFDBapletrczyxwvusqonmkjihgfdb";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }


    @Test
    public void keyToRight() {
        vigenere = new KeyedVigenere("appletreecat", false, false, true);
        vigenere.organiseAlphabet();
        String correct = "BDFGHIJKMNOQSUVWXYZAPLETRCbdfghijkmnoqsuvwxyzapletrc";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void keyInReverseAndAlphabetInReverse() {
        vigenere = new KeyedVigenere("appletreecat", true, true, false);
        vigenere.organiseAlphabet();
        String correct = "TACERLPZYXWVUSQONMKJIHGFDBtacerlpzyxwvusqonmkjihgfdb";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void alphabetInReverseAndKeyToRight() {
        vigenere = new KeyedVigenere("appletreecat", false, true, true);
        vigenere.organiseAlphabet();
        String correct = "ZYXWVUSQONMKJIHGFDBAPLETRCzyxwvusqonmkjihgfdbapletrc";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void keyInReverseAndKeyToRight() {
        vigenere = new KeyedVigenere("appletreecat", true, false, true);
        vigenere.organiseAlphabet();
        String correct = "BDFGHIJKMNOQSUVWXYZTACERLPbdfghijkmnoqsuvwxyztacerlp";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void allChoicesPicked() {
        vigenere = new KeyedVigenere("appletreecat", true, true, true);
        vigenere.organiseAlphabet();
        String correct = "ZYXWVUSQONMKJIHGFDBTACERLPzyxwvusqonmkjihgfdbtacerlp";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }


    private String checkAlphabetArray() {
        String alphabetInString = "";
        char[] alphabet = vigenere.getAlphabet().getAlphabet();
        for (int i = 0; i < alphabet.length; i++) {
            alphabetInString += alphabet[i];
        }
        return alphabetInString;
    }


}

