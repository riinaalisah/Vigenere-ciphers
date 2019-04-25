package ciphers.algos;

import ciphers.util.AlphabetArray;
import ciphers.util.Tableau;

/**
 * Class for keyed Vigenère.
 */
public class KeyedVigenere {

    public String key;
    boolean keyInReverse;
    boolean alphInReverse;
    boolean keyToRight;

    public Tableau tableau;
    public char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                                'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public AlphabetArray alphabet = new AlphabetArray(52);
    // originalAlphabet is used to change characters to upper or lower case,
    // because then the alphabet need to be in alphabetical order
    public AlphabetArray originalAlphabet = new AlphabetArray(52);

    /**
     * Sets the key entered by user and sets users choices on how to implement the key to alphabet.
     * @param key Keyword, entered by user
     * @param keyInReverse True if key is wanted in reverse order
     * @param alphInReverse True if alphabet is wanted in reverse order
     * @param keyToRight True if key is wanted to the right side (end) of alphabet
     */
    public void setChoicesAndKey(String key, boolean keyInReverse, boolean alphInReverse, boolean keyToRight) {
        originalAlphabet.setAlphabet(characters);
        alphabet.setAlphabet(characters);
        keyToUpperCase(key);

        this.keyInReverse = keyInReverse;
        this.alphInReverse = alphInReverse;
        this.keyToRight = keyToRight;
    }

    /**
     * Sets key to upper case
     * @param key Key entered bu user
     */
    private void keyToUpperCase(String key) {
        char[] keyParts = wordToArray(key);
        String toKey = "";
        for (int i = 0; i < keyParts.length; i++) {
            toKey += originalAlphabet.charToUpperCase(keyParts[i]);
        }
        this.key = toKey;
    }

    /**
     * Calls methods to re-organise the alphabet according to user's choices.
     */
    public void setAlphabet() {
        if (this.alphInReverse) {
            reverseAlphabet();
        }
        if (this.keyInReverse) {
            reverseKey();
        }

        // From this point on duplicate characters need to be removed from the key
        removeDuplicateCharactersFromKey();

        if (this.keyToRight) {
            insertKeyToRight();
        } else {
            insertKeyToLeft();
        }

        // Finally, set tableau when alphabet are re-organised
        tableau = new Tableau(alphabet.getAlphabet());
    }

    /**
     * Removes duplicate characters from key and updates the key.
     */
    public void removeDuplicateCharactersFromKey() {
        String newKey = "";
        char[] keyParts = wordToArray(key);
        // Create new Alphabet Array to check for duplicates
        AlphabetArray foundChars = new AlphabetArray(keyParts.length);
        for (int i = 0; i < keyParts.length; i++) {
            if (!foundChars.containsCharacter(keyParts[i])) {
                foundChars.setCharacter(keyParts[i], i);
            }
        }
        // Add characters that were added to foundChars to the new key.
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

        // First add capital letters from Z to A (from index 25 to 0) to indexes 0 to 25
        for (int i = 25; i >= 0; i--) {
            newAlphabet[index] = this.characters[i];
            index++;
        }

        // Then add small letters from z to a (from index 51 to 26) to indexes 26 to 51
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
        // Call method to remove characters found in the key from alphabet
        removeKeyCharsFromAlphabet(keyParts);

        /**
         * Fill alphabet array after removing characters found in the key.
         */
        char[] newAlphabet = new char[52];
        int index = 0;
        int length = alphabet.getAlphabet().length;

        // First go through capital letters.
        // Two indexes are used, because no empty spaces are left between characters,
        // and at this point the alphabet are missing the characters found in the key.
        for (int i = 0; i < length / 2; i++) {
            if (alphabet.getCharacter(i) != '-') {
                newAlphabet[index] = alphabet.getCharacter(i);
                index++;
            }
        }
        // Insert characters found in the key to the empty indexes (capital letters, so only until index 25)
        index = 0;
        for (int i = 0; i < length / 2; i++) {
            if (newAlphabet[i] == '\u0000') {
                newAlphabet[i] = keyParts[index];
                index++;
            }
        }

        // Then go through small letters. Again, two indexes are used to avoid empty spaces.
        index = 26;
        for (int i = length / 2; i < length; i++) {
            if (alphabet.getCharacter(i) != '-') {
                newAlphabet[index] = alphabet.getCharacter(i);
                index++;
            }
        }

        // Insert characters found in the key (small letters, so starting from index 26)
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
        // Call method to remove characters found in the key from alphabet
        removeKeyCharsFromAlphabet(keyParts);

        /**
         * Fill alphabet array after removing characters found in the key.
         */
        char[] newAlphabet = new char[52];
        int startIndex = keyParts.length;
        int index = 0;
        int length = alphabet.getAlphabet().length;

        // First go through capital letters. Leave the beginning empty for characters found in the key
        for (int i = startIndex; i < length / 2; i++) {
            if (alphabet.getCharacter(index) != '-') {
                newAlphabet[i] = alphabet.getCharacter(index);
            } else {
                i--;
            }
            index++;
        }

        // Insert characters found in the key to the beginning (capital letters)
        for (int i = 0; i < keyParts.length; i++) {
            newAlphabet[i] = keyParts[i];
        }

        // Then go through small letters. Again, leave the beginning empty.
        index = 26;
        for (int i = length / 2 + startIndex; i < length; i++) {
            if (alphabet.getCharacter(index) != '-') {
                newAlphabet[i] = alphabet.getCharacter(index);
            } else {
                i--;
            }
            index++;
        }

        // Insert characters found in the key to the beginning (small letters)
        index = 0;
        for (int i = 26; i < 26 + startIndex; i++) {
            newAlphabet[i] = originalAlphabet.charToLowerCase(keyParts[index]);
            index++;
        }

        alphabet.setAlphabet(newAlphabet);
    }

    /**
     * Removes characters found in the key from alphabet
     * @param keyParts Key in a character array
     */
    private void removeKeyCharsFromAlphabet(char[] keyParts) {
        for (int i = 0; i < keyParts.length; i++) {
            char c = keyParts[i];
            if (alphabet.containsCharacter(c)) {
                alphabet.removeCharacter(originalAlphabet.charToLowerCase(c));
                alphabet.removeCharacter(c);
            }
        }
    }

    /**
     * Puts a word into a character array
     * @param word Word to be put into array
     * @return Word in a character array
     */
    public char[] wordToArray(String word) {
        char[] array = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            array[i] = word.charAt(i);
        }
        return array;
    }

}
