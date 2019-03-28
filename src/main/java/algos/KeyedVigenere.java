package algos;

import java.util.ArrayList;

public class KeyedVigenere {

    char[][] tableau;
    public String key;
    public char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private ArrayList<Character> characters;

    public KeyedVigenere(String key, String keyInReverse, String alphInReverse, String keyToRight) {
        this.key = key;
        Tableau tb = new Tableau();
        characters = new ArrayList<>();

        /**
         * adds alphabet to list
         */
        for (int i = 0; i < alphabet.length; i++) {
            characters.add(alphabet[i]);
        }

        if (alphInReverse.equals("y")) {
            reverseAlphabet();
        }
        if (keyInReverse.equals("y")) {
            key = reverseKey(this.key);
        }

        this.key = removeDuplicateAlphabetsFromKey(key);

        if (keyToRight.equals("y")) {
            insertKeyToRight(this.key);
        } else {
            insertKeyToLeft(this.key);
        }

        /**
         * creates tableau
         */
        tableau = tb.create(alphabet);

    }

    public String removeDuplicateAlphabetsFromKey(String key) {
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

        return newKey;
    }

    /**
     * puts alphabet in reverse order
     */
    private void reverseAlphabet() {
        char[] newAlphabet = new char[26];
        int index = 0;
        for (int i = alphabet.length - 1; i >= 0; i--) {
            newAlphabet[index] = alphabet[i];
            index++;
        }

        alphabet = newAlphabet;
    }

    /**
     * puts entered key in reverse order
     * @param key
     * @return
     */
    public String reverseKey(String key) {
        char[] parts = key.toCharArray();
        String reversed = "";
        for (int i = parts.length - 1; i >= 0; i--) {
            reversed += parts[i];
        }
        return reversed;
    }

    private void insertKeyToRight(String key) {

        char[] keyParts = key.toCharArray();
        removeKeyCharsFromAlphabetList(keyParts);

        /**
         * Fills alphabet array after removing alphabet in the key from list.
         * Starts from index 0 so the right side of alphabet stays empty for alphabet in the key.
         */
        char[] newAlphabet = new char[26];
        for (int i = 0; i < characters.size(); i++) {
            newAlphabet[i] = characters.get(i);
        }

        /**
         * Inserts alphabet in the key to the right side of alphabet.
         */
        int startIndex = newAlphabet.length - keyParts.length;
        for (int i = 0; i < keyParts.length; i++) {
            newAlphabet[startIndex] = Character.toUpperCase(keyParts[i]);
            startIndex++;
        }

        alphabet = newAlphabet;

    }

    private void insertKeyToLeft(String key) {
        char[] keyParts = key.toCharArray();
        removeKeyCharsFromAlphabetList(keyParts);

        /**
         * Fills alphabet array after removing alphabet in the key from list.
         * Leaves beginning of array empty for alphabet in key.
         */
        char[] newAlphabet = new char[26];
        int startIndex = keyParts.length;
        int index = 0;
        for (int i = startIndex; i < newAlphabet.length; i++) {
            newAlphabet[i] = characters.get(index);
            index++;
        }

        /**
         * Inserts alphabet to the beginning of alphabet.
         */
        for (int i = 0; i < keyParts.length; i++) {
            newAlphabet[i] = Character.toUpperCase(keyParts[i]);
        }
        alphabet = newAlphabet;
    }

    /**
     * Removes alphabet in the key from character list
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
