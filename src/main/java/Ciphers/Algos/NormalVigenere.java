package Ciphers.Algos;

import Ciphers.Util.Tableau;

import java.util.ArrayList;

public class NormalVigenere {

    public Tableau tableau;
    char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    /**
     * list currently used for finding indexes for tableau
     */
    public ArrayList<Character> characters;

    public NormalVigenere() {
        characters = new ArrayList<Character>();
        for (int i = 0; i < alphabet.length; i++) {
            characters.add(alphabet[i]);
        }

        tableau = new Tableau(alphabet);
    }

}
