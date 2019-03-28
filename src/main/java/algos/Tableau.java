package algos;

public class Tableau {

    public char[][] create(char[] alphabet ) {
        char[][] tableau = new char[26][26];
        int index = 0;

        for (int y = 0; y < 26; y++) {
            for (int x = 0; x < 26; x++) {
                tableau[y][x] = alphabet[index];
                index++;
            }
            index = 0;
            shiftCharsToLeft(alphabet);
        }
        return tableau;
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
