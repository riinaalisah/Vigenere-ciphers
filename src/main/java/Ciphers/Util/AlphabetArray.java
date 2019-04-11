package Ciphers.Util;

public class AlphabetArray {

    private char[] alphabet;

    public AlphabetArray() {
        this.alphabet = new char[26];
    }

    public void setAlphabet(char[] characters) {
        for (int i = 0; i < characters.length; i++) {
            this.alphabet[i] = characters[i];
        }
    }

    public char[] getAlphabet() {
        return this.alphabet;
    }

    public Character getCharacter(int index) {
        return alphabet[index];
    }

    public int getIndexOfCharacter(Character c) {
        for (int i = 0; i < this.alphabet.length; i++) {
            if (this.alphabet[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public boolean alphabetContainsCharacter(Character c) {
        for (int i = 0; i < this.alphabet.length; i++) {
            if (this.alphabet[i] == c) {
                return true;
            }
        }
        return false;
    }

}
