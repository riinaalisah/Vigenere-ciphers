package ciphers.util;

/**
 * Class for tableau, used for encryption and decryption.
 */
public class Tableau {

    public char[][] tableau;

    /**
     * Creates a new tableau with 26 alphabet on y-axis and 52 alphabet in x-axis.
     * The first 26 alphabet on x-axis are in upper case, and the last 26 in lower case.
     * After ever row alphabet are shifted one index to left.
     * @param alphabet Array of alphabet to be set on the first row
     */
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

    /**
     * Shifts all alphabet in array one index to left.
     * Handles upper case characters and lower case characters separately.
     * @param alphabet Array of alphabet to be shifted
     * @return Array of alphabet in shifted positions
     */
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

    /**
     * Returns a character in a specific coordinates in tableau.
     * @param y row number
     * @param x column number
     * @return character in row y, column x in tableau
     */
    public Character getCharacter(int y, int x) {
        return tableau[y][x];
    }
}
