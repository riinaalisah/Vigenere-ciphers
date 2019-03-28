package algos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NormalVigenere {

    public Tableau tableau;
    char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    /**
     * list currently used for finding indexes for tableau
     */
    private ArrayList<Character> characters;

    public NormalVigenere() {
        characters = new ArrayList<Character>();
        for (int i = 0; i < alphabet.length; i++) {
            characters.add(alphabet[i]);
        }

        tableau = new Tableau(alphabet);
    }

    /*
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
    */

}
