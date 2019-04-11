package Util;

import Ciphers.Util.AlphabetArray;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junitx.framework.Assert.assertEquals;
import static junitx.framework.Assert.assertFalse;

public class AlphabetArrayTest {
    AlphabetArray alphabet;
    char[] characters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    @Before
    public void setUp() {
        this.alphabet = new AlphabetArray();
    }

    @Test
    public void alphabetCanBeSet() {
        alphabet.setAlphabet(characters);
        String correct = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetInString = "";
        for (int i = 0; i < 26; i++) {
            alphabetInString += alphabet.getCharacter(i);
        }
        assertEquals(correct, alphabetInString);
    }

    @Test
    public void methodGetIndexOfCharacterReturnsCorrectIndex() {
        alphabet.setAlphabet(characters);
        assertEquals(5, alphabet.getIndexOfCharacter('F'));
    }

    @Test
    public void methodgetIndexReturnsMinusOneIfNotFound() {
        alphabet.setAlphabet(characters);
        assertEquals(-1, alphabet.getIndexOfCharacter('*'));
    }

    @Test
    public void methodGetCharacterReturnsCorrectCharacter() {
        alphabet.setAlphabet(characters);
        assertEquals((Character) 'J', alphabet.getCharacter(9));
    }

    @Test
    public void returnsTrueIfCharacterIsFound() {
        alphabet.setAlphabet(characters);
        assertTrue(alphabet.alphabetContainsCharacter('H'));
    }

    @Test
    public void returnsFalseIfCharacterIsNotFound() {
        alphabet.setAlphabet(characters);
        assertFalse(alphabet.alphabetContainsCharacter('*'));
    }

}
