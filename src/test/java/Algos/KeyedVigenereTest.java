package Algos;

import ciphers.algos.KeyedVigenere;
import org.junit.Before;
import org.junit.Test;

import static junitx.framework.Assert.assertEquals;

public class KeyedVigenereTest {
    KeyedVigenere kv;

    @Before
    public void setUp() {
        kv = new KeyedVigenere();
    }

    @Test
    public void duplicateAlphabetAreRemovedFromKey() {
        kv.setChoicesAndKey("alfohmelldftssa", "n", "n", "n");
        String correct = "ALFOHMEDTS";
        kv.removeDuplicateAlphabetsFromKey();
        assertEquals(correct, kv.key);

    }

    @Test
    public void reversedAlphabetIsCorrect() {
        String correct = "ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba";
        kv.reverseAlphabet();
        String alphInString = "";
        for (int i = 0; i < kv.alphabet.getAlphabet().length; i++) {
            alphInString += kv.alphabet.getCharacter(i);
        }
        assertEquals(correct, alphInString);
    }

    @Test
    public void alphabetStringIsCorrectWithNoChoicesPicked() {
        kv.setChoicesAndKey("appletreecat", "n", "n", "n");
        kv.setAlphabet();
        String correct = "APLETRCBDFGHIJKMNOQSUVWXYZapletrcbdfghijkmnoqsuvwxyz";

        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }


    @Test
    public void keyIsReversedCorrectly() {
        kv.setChoicesAndKey("appletreecat", "y", "n", "n");
        kv.setAlphabet();
        String correct = "TACERLP";
        assertEquals(correct, kv.key);
    }

    @Test
    public void keyIsInsertedToTheRightSideOfAlphabet() {
        kv.setChoicesAndKey("appletreecat", "n", "n", "y");
        kv.setAlphabet();
        String correct = "BDFGHIJKMNOQSUVWXYZAPLETRCbdfghijkmnoqsuvwxyzapletrc";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void reversedKeyAndReversedAlphabetCorrect() {
        kv.setChoicesAndKey("appletreecat", "y", "y", "n");
        kv.setAlphabet();

        String correct = "TACERLPZYXWVUSQONMKJIHGFDBtacerlpzyxwvusqonmkjihgfdb";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void reversedAlphabetAndKeyToRightAlphabetCorrect() {
        kv.setChoicesAndKey("appletreecat", "n", "y", "y");
        kv.setAlphabet();
        String correct = "ZYXWVUSQONMKJIHGFDBAPLETRCzyxwvusqonmkjihgfdbapletrc";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void reversedKeyAndkeyToRightAlphabetCorrect() {
        kv.setChoicesAndKey("appletreecat", "y", "n", "y");
        kv.setAlphabet();
        String correct = "BDFGHIJKMNOQSUVWXYZTACERLPbdfghijkmnoqsuvwxyztacerlp";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void alphabetCorrectAllChoicesPicked() {
        kv.setChoicesAndKey("appletreecat", "y", "y", "y");
        kv.setAlphabet();
        String correct = "ZYXWVUSQONMKJIHGFDBTACERLPzyxwvusqonmkjihgfdbtacerlp";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    private String checkAlphabetArray() {
        String alphabetInString = "";
        for (int i = 0; i < kv.alphabet.getAlphabet().length; i++) {
            alphabetInString += kv.alphabet.getCharacter(i);
        }
        return alphabetInString;
    }


}

