package ciphers.algos;

import ciphers.util.AlphabetArray;
import ciphers.util.Tableau;

public class KeyedVigenere {

    public String key;
    String keyInReverse;
    String alphInReverse;
    String keyToRight;

    public Tableau tableau;
    public char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                                'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public AlphabetArray alphabet = new AlphabetArray(52);
    public AlphabetArray originalAlphabet = new AlphabetArray(52);

    public void setChoicesAndKey(String key, String keyInReverse, String alphInReverse, String keyToRight) {
        originalAlphabet.setAlphabet(characters);
        alphabet.setAlphabet(characters);

        char[] keyParts = wordToArray(key);
        String toKey = "";
        for (int i = 0; i < keyParts.length; i++) {
            toKey += alphabet.charToUpperCase(keyParts[i]);
        }

        this.key = toKey;
        this.keyInReverse = keyInReverse;
        this.alphInReverse = alphInReverse;
        this.keyToRight = keyToRight;
    }

    public void setAlphabet() {
        //alphabet.setAlphabet(characters);

        if (this.alphInReverse.equals("y")) {
            reverseAlphabet();
        }

        if (this.keyInReverse.equals("y")) {
            reverseKey();
        }

        removeDuplicateAlphabetsFromKey();

        if (this.keyToRight.equals("y")) {
            insertKeyToRight();
        } else {
            insertKeyToLeft();
        }

        tableau = new Tableau(alphabet.getAlphabet());
    }


    public void removeDuplicateAlphabetsFromKey() {
        String newKey = "";
        char[] keyParts = wordToArray(key);
        AlphabetArray foundChars = new AlphabetArray(keyParts.length);
        for (int i = 0; i < keyParts.length; i++) {
            if (!foundChars.containsCharacter(keyParts[i])) {
                foundChars.setCharacter(keyParts[i], i);
            }
        }
        for (int i = 0; i < foundChars.getAlphabet().length; i++) {
            if (foundChars.getCharacter(i) != '\u0000') {
                newKey += foundChars.getCharacter(i);
            }
        }

        this.key = newKey;
    }

    /**
     * Puts alphabet in reverse order
     */
    public void reverseAlphabet() {
        char[] newAlphabet = new char[52];
        int index = 0;

        /**
         * Capital letters
         */
        for (int i = 25; i >= 0; i--) {
            newAlphabet[index] = this.characters[i];
            index++;
        }
        /**
         * Small letters
         */
        for (int i = 51; i >= 26; i--) {
            newAlphabet[index] = this.characters[i];
            index++;
        }

        this.alphabet.setAlphabet(newAlphabet);
    }

    /**
     * Puts keyword in reverse order
     */
    public void reverseKey() {
        char[] parts = key.toCharArray();
        String reversed = "";
        for (int i = parts.length - 1; i >= 0; i--) {
            reversed += parts[i];
        }
        this.key = reversed;
    }

    /**
     * Inserts keyword to the right side (end) of the alphabet key
     */
    private void insertKeyToRight() {

        char[] keyParts = key.toCharArray();
        removeKeyCharsFromAlphabet(keyParts);

        /**
         * Fills alphabet array after removing characters in the keyword from alphabet.
         */
        char[] newAlphabet = new char[52];
        int index = 0;
        int length = alphabet.getAlphabet().length;

        // Capital letters
        for (int i = 0; i < length / 2; i++) {
            if (alphabet.getCharacter(i) != '-') {
                newAlphabet[index] = alphabet.getCharacter(i);
                index++;
            }
        }
        // Insert key parts (capital letters)
        index = 0;
        for (int i = 0; i < length / 2; i++) {
            if (newAlphabet[i] == '\u0000') {
                newAlphabet[i] = keyParts[index];
                index++;
            }
        }

        // Small letters
        index = 26;
        for (int i = length / 2; i < length; i++) {
            if (alphabet.getCharacter(i) != '-') {
                newAlphabet[index] = alphabet.getCharacter(i);
                index++;
            }
        }

        // Insert key parts (small letters)
        index = 0;
        for (int i = length / 2; i < length; i++) {
            if (newAlphabet[i] == '\u0000') {
                newAlphabet[i] = originalAlphabet.charToLowerCase(keyParts[index]);
                index++;
            }
        }

        alphabet.setAlphabet(newAlphabet);

    }

    /**
     * Inserts keyword to the left side (beginning) of the alphabet key
     */
    private void insertKeyToLeft() {
        char[] keyParts = key.toCharArray();
        removeKeyCharsFromAlphabet(keyParts);

        /**
         * Fills alphabet array after removing characters in the keyword from alphabet.
         */
        char[] newAlphabet = new char[52];
        int startIndex = keyParts.length;
        int index = 0;
        int length = alphabet.getAlphabet().length;

        // Capital letters
        for (int i = startIndex; i < length / 2; i++) {
            if (alphabet.getCharacter(index) != '-') {
                newAlphabet[i] = alphabet.getCharacter(index);
            } else {
                i--;
            }
            index++;
        }
        /**
         * Inserts characters in the keyword the to the beginning of the alphabet key.
         */
        for (int i = 0; i < keyParts.length; i++) {
            newAlphabet[i] = keyParts[i];
        }
        // Small letters
        index = 26;
        for (int i = length / 2 + startIndex; i < length; i++) {
            if (alphabet.getCharacter(index) != '-') {
                newAlphabet[i] = alphabet.getCharacter(index);
            } else {
                i--;
            }
            index++;
        }

        index = 0;
        for (int i = 26; i < 26 + startIndex; i++) {
            newAlphabet[i] = originalAlphabet.charToLowerCase(keyParts[index]);
            index++;
        }

        alphabet.setAlphabet(newAlphabet);
    }

    /**
     * Removes characters in the keyword from character list
     *
     * @param keyParts Characters in keyword in an array
     */
    private void removeKeyCharsFromAlphabet(char[] keyParts) {

        for (int i = 0; i < keyParts.length; i++) {
            char c = keyParts[i];
            if (alphabet.containsCharacter(c)) {
                alphabet.removeCharacter(alphabet.charToLowerCase(c));
                alphabet.removeCharacter(c);
            }
        }
    }

    public char[] wordToArray(String word) {
        char[] array = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            array[i] = word.charAt(i);
        }
        return array;
    }

}
