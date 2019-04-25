package ciphers.algos;

import ciphers.util.AlphabetArray;
import ciphers.util.Tableau;

/**
 * Class for normal Vigenère cipher
 */
public class NormalVigenere {

    public Tableau tableau;
    char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    AlphabetArray alphabet = new AlphabetArray(52);

    /**
     * Creates a new instance of normal Vigenère cipher.
     * Sets alphabet and tableau with regular alphabet array.
     */
    public NormalVigenere() {
        alphabet.setAlphabet(characters);
        tableau = new Tableau(characters);
    }

}
