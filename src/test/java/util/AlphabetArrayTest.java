package util;

import ciphers.util.AlphabetArray;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junitx.framework.Assert.assertEquals;
import static junitx.framework.Assert.assertFalse;

public class AlphabetArrayTest {
    AlphabetArray alphabet;
    char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    @Before
    public void setUp() {
        this.alphabet = new AlphabetArray(52);
        alphabet.setAlphabet(characters);
    }

    @Test
    public void alphabetCanBeSet() {
        String correct = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String alphabetInString = "";
        for (int i = 0; i < 52; i++) {
            alphabetInString += alphabet.getCharacter(i);
        }
        assertEquals(correct, alphabetInString);
    }

    @Test
    public void methodGetIndexOfCharacterReturnsCorrectIndex() {
        assertEquals(5, alphabet.getIndexOfCharacter('F'));
    }

    @Test
    public void methodgetIndexReturnsMinusOneIfNotFound() {
        assertEquals(-1, alphabet.getIndexOfCharacter('*'));
    }

    @Test
    public void methodGetCharacterReturnsCorrectCharacter() {
        assertEquals('J', alphabet.getCharacter(9));
        assertEquals('j', alphabet.getCharacter(35));
    }

    @Test
    public void returnsTrueIfCharacterIsFound() {
        assertTrue(alphabet.containsCharacter('H'));
    }

    @Test
    public void returnsFalseIfCharacterIsNotFound() {
        assertFalse(alphabet.containsCharacter('*'));
    }

    @Test
    public void charToUpperCaseReturnCorrectChar() {
        assertEquals('H', alphabet.charToUpperCase('h'));
    }

    @Test
    public void charToUpperCaseReturnsSameCharacterIfAlreadyInUpperCase() {
        assertEquals('H', alphabet.charToUpperCase('H'));
    }

    @Test
    public void charToLowerCaseReturnsCorrectChar() {
        assertEquals('r', alphabet.charToLowerCase('R'));
    }

    @Test
    public void charToLowerCaseReturnSameCharacterIfAlreadyInLowerCase() {
        assertEquals('r', alphabet.charToLowerCase('r'));
    }

}
