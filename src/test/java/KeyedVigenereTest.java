import algos.KeyedVigenere;
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
        String correct = "alfohmedts";
        kv.removeDuplicateAlphabetsFromKey();
        assertEquals(correct, kv.key);

    }

    @Test
    public void reversedAlphabetIsCorrect() {
        String correct = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
        kv.reverseAlphabet();
        String alphInString = "";
        for (int i = 0; i < kv.alphabet.length; i++) {
            alphInString += kv.alphabet[i];
        }
        assertEquals(correct, alphInString);
    }

    @Test
    public void alphabetStringIsCorrectWithNoChoicesPicked() {
        kv.setChoicesAndKey("appletreecat", "n", "n", "n");
        String correct = "APLETRCBDFGHIJKMNOQSUVWXYZ";
        kv.setAlphabet();
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }


    @Test
    public void keyIsReversedCorrectly() {
        kv.setChoicesAndKey("appletreecat", "y", "n", "n");
        kv.setAlphabet();
        String correct = "tacerlp";
        assertEquals(correct, kv.key);
    }

    @Test
    public void keyIsInsertedToTheRightSideOfAlphabet() {
        kv.setChoicesAndKey("appletreecat", "n", "n", "y");
        kv.setAlphabet();
        String correct = "BDFGHIJKMNOQSUVWXYZAPLETRC";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void reversedKeyAndReversedAlphabetCorrect() {
        kv.setChoicesAndKey("appletreecat", "y", "y", "n");
        kv.setAlphabet();

        String correct = "TACERLPZYXWVUSQONMKJIHGFDB";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void reversedAlphabetAndKeyToRightAlphabetCorrect() {
        kv.setChoicesAndKey("appletreecat", "n", "y", "y");
        kv.setAlphabet();
        String correct = "ZYXWVUSQONMKJIHGFDBAPLETRC";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void reversedKeyAndkeyToRightAlphabetCorrect() {
      //  kv = new KeyedVigenere("appletreecat", "y", "n", "y");
        kv.setChoicesAndKey("appletreecat", "y", "n", "y");
        kv.setAlphabet();
        String correct = "BDFGHIJKMNOQSUVWXYZTACERLP";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    @Test
    public void alphabetCorrectAllChoicesPicked() {
       // kv = new KeyedVigenere("appletreecat", "y", "y", "y");
        kv.setChoicesAndKey("appletreecat", "y", "y", "y");
        kv.setAlphabet();
        String correct = "ZYXWVUSQONMKJIHGFDBTACERLP";
        String alphabet = checkAlphabetArray();
        assertEquals(correct, alphabet);
    }

    private String checkAlphabetArray() {
        String alphabet = "";
        for (int i = 0; i < kv.alphabet.length; i++) {
            alphabet += kv.alphabet[i];
        }
        return alphabet;
    }


}
