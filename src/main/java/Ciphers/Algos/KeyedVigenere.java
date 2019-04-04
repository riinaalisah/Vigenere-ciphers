package Ciphers.Algos;

import Ciphers.Util.Tableau;

import java.util.ArrayList;

public class KeyedVigenere {

    public String key;
    String keyInReverse;
    String alphInReverse;
    String keyToRight;

    public Tableau tableau;
    public char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public ArrayList<Character> characters;

    public KeyedVigenere() {
        characters = new ArrayList<>();


    }

    public void setChoicesAndKey(String key, String keyInReverse, String alphInReverse, String keyToRight) {
        this.key = key;
        this.keyInReverse = keyInReverse;
        this.alphInReverse = alphInReverse;
        this.keyToRight = keyToRight;
    }

    public void setAlphabet() {

        if (this.alphInReverse.equals("y")) {
            reverseAlphabet();
        }
        /**
         * adds alphabet to list
         */
        for (int i = 0; i < alphabet.length; i++) {
            characters.add(alphabet[i]);
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

        /**
         * creates tableau
         */
        tableau = new Tableau(alphabet);
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
     * puts tableau in reverse order
     */
    public void reverseAlphabet() {
        char[] newAlphabet = new char[26];
        int index = 0;
        for (int i = this.alphabet.length - 1; i >= 0; i--) {
            newAlphabet[index] = this.alphabet[i];
            index++;
        }

        this.alphabet = newAlphabet;
    }

    /**
     * puts key in reverse order
     */
    public void reverseKey() {
        char[] parts = key.toCharArray();
        String reversed = "";
        for (int i = parts.length - 1; i >= 0; i--) {
            reversed += parts[i];
        }
        this.key = reversed;
    }

    private void insertKeyToRight() {

        char[] keyParts = key.toCharArray();
        removeKeyCharsFromAlphabetList(keyParts);

        /**
         * Fills tableau array after removing tableau in the key from list.
         * Starts from index 0 so the right side of tableau stays empty for tableau in the key.
         */
        char[] newAlphabet = new char[26];
        for (int i = 0; i < characters.size(); i++) {
            newAlphabet[i] = characters.get(i);
        }

        /**
         * Inserts tableau in the key to the right side of tableau.
         */
        int startIndex = newAlphabet.length - keyParts.length;
        for (int i = 0; i < keyParts.length; i++) {
            newAlphabet[startIndex] = Character.toUpperCase(keyParts[i]);
            characters.add(startIndex, Character.toUpperCase(keyParts[i]));
            startIndex++;
        }

        alphabet = newAlphabet;

    }

    private void insertKeyToLeft() {
        char[] keyParts = key.toCharArray();
        removeKeyCharsFromAlphabetList(keyParts);

        /**
         * Fills tableau array after removing tableau in the key from list.
         * Leaves beginning of array empty for tableau in key.
         */
        char[] newAlphabet = new char[26];
        int startIndex = keyParts.length;
        int index = 0;
        for (int i = startIndex; i < newAlphabet.length; i++) {
            newAlphabet[i] = characters.get(index);
            index++;
        }

        /**
         * Inserts tableau to the beginning of tableau.
         */
        for (int i = 0; i < keyParts.length; i++) {
            newAlphabet[i] = Character.toUpperCase(keyParts[i]);
            characters.add(i, Character.toUpperCase((keyParts[i])));
        }
        alphabet = newAlphabet;
    }

    /**
     * Removes tableau in the key from character list
     * @param keyParts
     */
    private void removeKeyCharsFromAlphabetList(char[] keyParts) {

        for (int i = 0; i < keyParts.length; i++) {
            Character c = Character.toUpperCase(keyParts[i]);
            if (characters.contains(c)) {
                characters.remove(c);
            }
        }
    }

}
