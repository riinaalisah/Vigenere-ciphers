package ciphers.util;

/**
 * Class for array functions, used mostly for organizing alphabet
 */
public class AlphabetArray {

    private char[] alphabet;

    /**
     * Create new array
     * @param length length of new array
     */
    public AlphabetArray(int length) {
        this.alphabet = new char[length];
    }

    /**
     * Set alphabet to array
     * @param characters array of alphabet to be set
     */
    public void setAlphabet(char[] characters) {
        for (int i = 0; i < characters.length; i++) {
            this.alphabet[i] = characters[i];
        }
    }

    /**
     * Set character to wanted index
     * @param c character to be set
     * @param index index where character is set
     */
    public void setCharacter(char c, int index) {
        this.alphabet[index] = c;
    }

    /**
     * Get current alphabet array
     * @return alphabet in character array
     */
    public char[] getAlphabet() {
        return this.alphabet;
    }

    /**
     * Get character in a specific index of array
     * @param index index in question
     * @return character in wanted index
     */
    public char getCharacter(int index) {
        return alphabet[index];
    }

    /**
     * Get index number of specific character.
     * @param c character whose index is wanted
     * @return index of character, or -1 if character is not found
     */
    public int getIndexOfCharacter(char c) {
        for (int i = 0; i < this.alphabet.length; i++) {
            if (this.alphabet[i] == c) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Tells if the array contains a specific character
     * @param c character in question
     * @return true if character is found, false if not found
     */
    public boolean containsCharacter(char c) {
        for (int i = 0; i < this.alphabet.length; i++) {
            if (this.alphabet[i] == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a character from array, replaces tha character with '-'
     * @param c character to be removed
     */
    public void removeCharacter(char c) {
        for (int i = 0; i < this.alphabet.length; i++) {
            if (alphabet[i] == c) {
                alphabet[i] = '-';
            }
        }
    }

    /**
     * Changes a character to upper case.
     * @param c character to be changed
     * @return character in upper case in both cases (input character in lower or upper case)
     */
    public char charToUpperCase(char c) {
        int indexOfFound = getIndexOfCharacter(c);
        if (indexOfFound > 25) {
            return alphabet[indexOfFound - 26];
        } else {
            return alphabet[indexOfFound];
        }
    }


    /**
     * Changes a character to lower case.
     * @param c character to be changed
     * @return character in upper case in both cases (input character in lower or upper case)
     */
    public char charToLowerCase(char c) {
        int indexOfFound = getIndexOfCharacter(c);
        if (indexOfFound <= 25) {
            return alphabet[indexOfFound + 26];
        } else {
            return alphabet[indexOfFound];
        }
    }

}
