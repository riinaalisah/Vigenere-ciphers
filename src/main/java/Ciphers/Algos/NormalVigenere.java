package Ciphers.Algos;

import Ciphers.Util.AlphabetArray;
import Ciphers.Util.Tableau;

public class NormalVigenere {

    public Tableau tableau;
    char[] characters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    AlphabetArray alphabet = new AlphabetArray();

    public NormalVigenere() {
        alphabet.setAlphabet(characters);
        tableau = new Tableau(characters);
    }

}
