package algos;

import java.util.ArrayList;

public class KeyedVigenere {

    char[][] tableau;
    char[] chars = {'A','B','C','D','E','F','G','H','I','J','K','L'
            ,'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private ArrayList<Character> characters;

    public KeyedVigenere(String key, String keyInReverse, String alphInReverse, String keyToRight) {
        characters = new ArrayList<>();

        if (alphInReverse.equals("y")) {
            reverseAlphabet();
        }
        if (keyInReverse.equals("y")) {
            key = reverseKey(key);
        }
        if (keyToRight.equals("y")) {
            moveKeyToRight(key);
        }

    }

    /**
     * puts alphabet in reverse order
     */
    private void reverseAlphabet() {
        char[] newAlphabet = new char[26];
        int index = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            newAlphabet[index] = chars[i];
            index++;
        }

        chars = newAlphabet;
    }

    /**
     * puts entered key in reverse order
     * @param key
     * @return
     */
    private String reverseKey(String key) {
        char[] parts = key.toCharArray();
        String reversed = "";
        for (int i = parts.length - 1; i >= 0; i--) {
            reversed += parts[i];
        }

        return reversed;
    }

    private void moveKeyToRight(String key) {

        char[] keyChars = key.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            characters.add(chars[i]);
        }

        for (int i = 0; i < keyChars.length; i++) {
            Character c = Character.toUpperCase(keyChars[i]);
            if (characters.contains(c)) {
                characters.remove(c);
            }
        }

        char[] newAlphabet = new char[26];
        for (int i = 0; i < characters.size(); i++) {
            newAlphabet[i] = characters.get(i);
        }
        int startIndex = newAlphabet.length - keyChars.length;
        System.out.println("START " + startIndex);
        for (int i = 0; i < keyChars.length; i++) {
            newAlphabet[startIndex] = keyChars[i];
            startIndex++;
        }
        for (int i = 0; i < newAlphabet.length; i++) {
            System.out.print(newAlphabet[i]);
        }

        chars = newAlphabet;

    }
}
