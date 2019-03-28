import algos.KeyedVigenere;
import org.junit.Before;
import org.junit.Test;

import static junitx.framework.Assert.assertEquals;

public class KeyedVigenereTest {
    KeyedVigenere kv;

    @Test
    public void duplicateAlphabetAreRemovedFromKey() {
        kv = new KeyedVigenere("alfohmelldftssa", "n", "n", "n");
        String correct = "alfohmedts";
        String keyWithoutDuplicates = kv.removeDuplicateAlphabetsFromKey("alfohmelldftssa");
        assertEquals(correct, keyWithoutDuplicates);

    }

    @Test
    public void alphabetStringIsCorrectWithNoChoicesPicked() {
        kv = new KeyedVigenere("appletreecat", "n", "n", "n");
        String correct = "APLETRCBDFGHIJKMNOQSUVWXYZ";
        String alphabet = "";
        for (int i = 0; i < kv.alphabet.length; i++) {
            alphabet += kv.alphabet[i];
        }
        assertEquals(correct, alphabet);
    }

    @Test
    public void reversedAlphabetIsCorrect() {
        kv = new KeyedVigenere("lemon", "n", "y", "n");
        String correct = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
        String alphInString = "";
        for (int i = 0; i < kv.alphabet.length; i++) {
            alphInString += kv.alphabet[i];
        }
        assertEquals(correct, alphInString);
    }

    @Test
    public void keyIsReversedCorrectly() {
        kv = new KeyedVigenere("appletreecat", "y", "n", "n");
        String correct = "tacerlp";
        assertEquals(correct, kv.key);
    }

    @Test
    public void keyIsInsertedToTheRightSideOfAlphabet() {
        kv = new KeyedVigenere("appletreecat", "n", "n", "y");
        String correct = "BDFGHIJKMNOQSUVWXYZAPLETRC";
        String alphabet = "";
        for (int i = 0; i < kv.alphabet.length; i++) {
            alphabet += kv.alphabet[i];
        }
        assertEquals(correct, alphabet);
    }

    @Test
    public void reversedKeyAndReversedAlphabetCorrect() {
        kv = new KeyedVigenere("appletreecat", "y", "y", "n");
        String correct = "TACERLPZYXWVUSQONMKJIHGFDB";
        String alphabet = "";
        for (int i = 0; i < kv.alphabet.length; i++) {
            alphabet += kv.alphabet[i];
        }
        assertEquals(correct, alphabet);
    }

    @Test
    public void reversedAlphabetAndKeyToRightAlphabetCorrect() {
        kv = new KeyedVigenere("appletreecat", "n", "y", "y");
        String correct = "ZYXWVUSQONMKJIHGFDBAPLETRC";
        String alphabet = "";
        for (int i = 0; i < kv.alphabet.length; i++) {
            alphabet += kv.alphabet[i];
        }
        assertEquals(correct, alphabet);
    }

    @Test
    public void reversedKeyAndkeyToRightAlphabetCorrect() {
        kv = new KeyedVigenere("appletreecat", "y", "n", "y");
        String correct = "BDFGHIJKMNOQSUVWXYZTACERLP";
        String alphabet = "";
        for (int i = 0; i < kv.alphabet.length; i++) {
            alphabet += kv.alphabet[i];
        }
        assertEquals(correct, alphabet);
    }

    @Test
    public void alphabetCorrectAllChoicesPicked() {
        kv = new KeyedVigenere("appletreecat", "y", "y", "y");
        String correct = "ZYXWVUSQONMKJIHGFDBTACERLP";
        String alphabet = "";
        for (int i = 0; i < kv.alphabet.length; i++) {
            alphabet += kv.alphabet[i];
        }
        assertEquals(correct, alphabet);
    }


}
