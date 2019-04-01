package algos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Encryption {

    public File encrypt(String passphrase, File textFile, Tableau tableau, ArrayList<Character> characters) throws IOException {

        File encrypted = new File("texts/" + passphrase + ".txt");
        Scanner scanner = new Scanner(textFile);
        FileWriter fw = new FileWriter(encrypted);
        int keyLength = passphrase.length();

        char[] keyInArray = passphrase.toCharArray();
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

                Character letter = tableau.tableau[characters.indexOf(charY)][characters.indexOf(charX)];

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
}
