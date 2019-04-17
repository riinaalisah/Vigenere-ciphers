package ciphers.util;

public class Tableau {

    public char[][] tableau;

    public Tableau(char[] alphabet) {
        tableau = new char[26][52];
        int index = 0;

        for (int y = 0; y < 26; y++) {
            for (int x = 0; x < 52; x++) {
                tableau[y][x] = alphabet[index];
                index++;
            }
            index = 0;
            shiftCharsToLeft(alphabet);
        }
    }

    private char[] shiftCharsToLeft(char[] alphabet) {
        char temp1 = alphabet[0];
        char temp2 = alphabet[26];

        for (int i = 0; i < 25; i++) {
            alphabet[i] = alphabet[i + 1];
        }
        alphabet[25] = temp1;

        for (int i = 26; i < 51; i++) {
            alphabet[i] = alphabet[i + 1];
        }
        alphabet[51] = temp2;

        return alphabet;
    }

    public Character getLetter(int y, int x) {
        return tableau[y][x];
    }
}
