
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VigenereCipher {

    char[][] tableau;
    char[] chars = {'A','B','C','D','E','F','G','H','I','J','K','L'
            ,'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    /**
     * list currently used for finding indexes for chars
     */
    private ArrayList<Character> characters;


    /**
     *  creates the tableau
     */
    public VigenereCipher() {
        this.tableau = new char[26][26];
        characters = new ArrayList<Character>();

        int index = 0;

        for (int y = 0; y < 26; y++) {
            for (int x = 0; x < 26; x++) {
                tableau[y][x] = chars[index];

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

    public File encrypt(String key, File textFile) throws IOException {

        File encrypted = new File("texts/" + key + ".txt");
        Scanner scanner = new Scanner(textFile);
        FileWriter fw = new FileWriter(encrypted);
        int keyLength = key.length();

        char[] keyInArray = key.toCharArray();
        int index = 0;

        while (scanner.hasNext()) {
            String word = scanner.next();
            char[] wordInArray = word.toCharArray();

            for (int i = 0; i < wordInArray.length; i++) {

                Character charX = wordInArray[i];
                charX = Character.toUpperCase(charX);

                if (!characters.contains(charX)) {
                    fw.write(charX);
                    continue;
                }

                Character charY = keyInArray[index];
                charY = Character.toUpperCase(charY);

                Character letter = tableau[characters.indexOf(charY)][characters.indexOf(charX)];

                fw.write(Character.toLowerCase(letter));

                index++;
                if (index == keyLength) {
                    index = 0;
                }
            }
            fw.write(" ");
        }
        fw.close();

        return encrypted;
    }

    /*
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
    */


}
