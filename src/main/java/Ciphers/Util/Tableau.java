package Ciphers.Util;

public class Tableau {

    public char[][] tableau;

    public Tableau(char[] alphabet) {
        tableau = new char[26][26];
        int index = 0;

        for (int y = 0; y < 26; y++) {
            for (int x = 0; x < 26; x++) {
                tableau[y][x] = alphabet[index];
                index++;
            }
            index = 0;
            shiftCharsToLeft(alphabet);
        }
    }

    private char[] shiftCharsToLeft(char[] alphabet) {
        Character temp = alphabet[0];

        for (int i = 0; i < 25; i++) {
            alphabet[i] = alphabet[i+1];
        }
        alphabet[25] = temp;

        return alphabet;
    }
}
