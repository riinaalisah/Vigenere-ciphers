package Ciphers.Algos;

import Ciphers.Util.AlphabetArray;
import Ciphers.Util.Tableau;

import java.util.ArrayList;

public class KeyedVigenere {

    public String key;
    String keyInReverse;
    String alphInReverse;
    String keyToRight;

    public Tableau tableau;
    public char[] characters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public AlphabetArray alphabet = new AlphabetArray();

    public void setChoicesAndKey(String key, String keyInReverse, String alphInReverse, String keyToRight) {
        this.key = key;
        this.keyInReverse = keyInReverse;
        this.alphInReverse = alphInReverse;
        this.keyToRight = keyToRight;
    }

    public void setAlphabet() {
        alphabet.setAlphabet(characters);

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
        ArrayList<Character> foundChars = new ArrayList<>();
        char[] parts = key.toCharArray();
        for (int i = 0; i < parts.length; i++) {
            if (!foundChars.contains(parts[i])) {
                foundChars.add(parts[i]);
            }
        }
        for (Character c : foundChars) {
            newKey += c;
        }

        this.key = newKey;
    }

    /**
     * Puts tableau in reverse order
     */
    public void reverseAlphabet() {
        char[] newAlphabet = new char[26];
        int index = 0;
        for (int i = this.characters.length - 1; i >= 0; i--) {
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
     *  Inserts keyword to the right side (end) of the alphabet key
     */
    private void insertKeyToRight() {

        char[] keyParts = key.toCharArray();
        removeKeyCharsFromAlphabet(keyParts);

        /**
         * Fills alphabet array after removing characters in the keyword from list.
         * Starts from index 0 so the right side of array stays empty for characters in the key.
         */
        char[] newAlphabet = new char[26];
        int index = 0;

        for (int i = 0; i < alphabet.getAlphabet().length; i++) {
            if (alphabet.getCharacter(i) != '-') {
                newAlphabet[index] = alphabet.getCharacter(i);
                index++;
            }
        }

        /**
         * Inserts characters in the keyword to the right side of alphabet key.
         */
        int startIndex = newAlphabet.length - keyParts.length;
        for (int i = 0; i < keyParts.length; i++) {
            newAlphabet[startIndex] = Character.toUpperCase(keyParts[i]);
            startIndex++;
        }

        alphabet.setAlphabet(newAlphabet);

    }

    /**
     *  Inserts keyword to the left side (beginning) of the alphabet key
     */
    private void insertKeyToLeft() {
        char[] keyParts = key.toCharArray();
        removeKeyCharsFromAlphabet(keyParts);

        /**
         * Fills alphabet array after removing characters in the keyword from list.
         * Leaves beginning of array empty for characters in keyword.
         */
        char[] newAlphabet = new char[26];
        int startIndex = keyParts.length;
        int index = 0;
        for (int i = startIndex; i < newAlphabet.length; i++) {
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
            newAlphabet[i] = Character.toUpperCase(keyParts[i]);
            alphabet.setCharacter(Character.toUpperCase((keyParts[i])), i);
        }

        alphabet.setAlphabet(newAlphabet);
    }

    /**
     * Removes characters in the keyword from character list
     * @param keyParts Characters in keyword in an array
     */
    private void removeKeyCharsFromAlphabet(char[] keyParts) {

        for (int i = 0; i < keyParts.length; i++) {
            Character c = Character.toUpperCase(keyParts[i]);
            if (alphabet.alphabetContainsCharacter(c)) {
                alphabet.removeCharacter(c);
            }
        }
    }

}
