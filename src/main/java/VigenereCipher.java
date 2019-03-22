import java.util.ArrayList;

public class VigenereCipher {

    char[][] matrix;
    private String key;
    String keystream;
    private String originalText;
    private String textWithoutSpaces;
    private ArrayList<Character> characters;

    public VigenereCipher() {
        this.matrix = new char[26][26];
        characters = new ArrayList<Character>();
        char[] chars = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        int index = 0;

        for (int y = 0; y < 26; y++) {
            for (int x = 0; x < 26; x++) {
                matrix[y][x] = chars[index];

                if (y == 0) {
                    characters.add(chars[index]);
                }

                index++;
            }
            index = 0;
            shiftCharsToLeft(chars);
        }
    }

    private char[] shiftCharsToLeft(char[] chars) {
        Character temp = chars[0];

        for (int i = 0; i < 25; i++) {
            chars[i] = chars[i+1];
        }
        chars[25] = temp;

        return chars;
    }

    public void encrypt(String key, String text) {
        String encryptedMsg = "";
        this.key = key.toUpperCase();
        this.originalText = text.toUpperCase();
        this.textWithoutSpaces = text.toUpperCase().replaceAll("\\s+","");

        keystream = createKeystream(textWithoutSpaces);

        char[] textInCharArray = textWithoutSpaces.toCharArray();
        char[] keystreamInArray = keystream.toCharArray();


        for (int i = 0; i < textInCharArray.length; i++) {
            char charX = textInCharArray[i];
            char charY = keystreamInArray[i];

            encryptedMsg += matrix[characters.indexOf(charY)][characters.indexOf(charX)];
        }
        System.out.println("Original: " + this.textWithoutSpaces);
        System.out.println("Encrypted: " + encryptedMsg);
    }

    private String createKeystream(String text) {
        String keystream = "";

        char[] textChars = text.toCharArray();
        char[] keyChars = key.toCharArray();
        int keyIndex = 0;

        for (int i = 0; i < textChars.length; i++) {
            keystream += keyChars[keyIndex];
            keyIndex++;
            if (keyIndex == keyChars.length) {
                keyIndex = 0;
            }
        }

        return keystream;
    }


}
