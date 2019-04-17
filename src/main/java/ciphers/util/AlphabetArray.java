package ciphers.util;

public class AlphabetArray {

    private char[] alphabet;

    public AlphabetArray(int length) {
        this.alphabet = new char[length];
    }

    public void setAlphabet(char[] characters) {
        for (int i = 0; i < characters.length; i++) {
            this.alphabet[i] = characters[i];
        }
    }

    public void setCharacter(char c, int index) {
        this.alphabet[index] = c;
    }

    public char[] getAlphabet() {
        return this.alphabet;
    }

    public char getCharacter(int index) {
        return alphabet[index];
    }

    public int getIndexOfCharacter(char c) {
        for (int i = 0; i < this.alphabet.length; i++) {
            if (this.alphabet[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public boolean containsCharacter(char c) {
        for (int i = 0; i < this.alphabet.length; i++) {
            if (this.alphabet[i] == c) {
                return true;
            }
        }
        return false;
    }

    public void removeCharacter(char c) {
        for (int i = 0; i < this.alphabet.length; i++) {
            if (alphabet[i] == c) {
                alphabet[i] = '-';
            }
        }
    }

    public char charToUpperCase(char c) {
        int indexOfFound = getIndexOfCharacter(c);
        if (indexOfFound > 25) {
            return alphabet[indexOfFound - 26];
        } else {
            return alphabet[indexOfFound];
        }
    }

    public char charToLowerCase(char c) {
        int indexOfFound = getIndexOfCharacter(c);
        if (indexOfFound <= 25) {
            return alphabet[indexOfFound + 26];
        } else {
            return alphabet[indexOfFound];
        }
    }

}
