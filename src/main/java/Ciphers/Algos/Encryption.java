package Ciphers.Algos;

import Ciphers.IO.FileHandler;
import Ciphers.Util.Tableau;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Encryption {

    /**
     * Method used for encryption
     * @param passphrase Used for row indexing
     * @param textFile File to encrypt
     * @param tableau Used to find correct letters for encrypted text
     * @param characters List of available characters
     * @return
     * @throws IOException
     */
    public File encrypt(String passphrase, File textFile, Tableau tableau, ArrayList<Character> characters) throws IOException {

        FileHandler fileHandler = new FileHandler(textFile.getName(), true);
        int passphraseLength = passphrase.length();
        char[] passphraseInArray = passphrase.toCharArray();

        int index = 0;
        boolean endOfFile = false;

        while (true) {
            String word = fileHandler.nextWord();
            System.out.println("Word: " + word);
            if (word.contains("ENDOFFILEREACHED")) {
                endOfFile = true;
                word = word.replace("ENDOFFILEREACHED", "");
            }
            char[] wordInArray = word.toCharArray();
            String wordToWrite = "";

            for (int i = 0; i < wordInArray.length; i++) {

                Character charX = wordInArray[i];
                charX = Character.toUpperCase(charX);


                if (!characters.contains(charX)) {
                    wordToWrite += charX;
                    continue;
                }

                Character charY = passphraseInArray[index];
                charY = Character.toUpperCase(charY);

                Character letter = tableau.getLetter(characters.indexOf(charY), characters.indexOf(charX));//.tableau[characters.indexOf(charY)][characters.indexOf(charX)];
                wordToWrite += letter;

                index++;
                if (index == passphraseLength) {
                    index = 0;
                }
            }
            fileHandler.writeWord(wordToWrite + " ");
            if (endOfFile) {
                break;
            }
        }
        fileHandler.close();

        return fileHandler.getOutputFile();
    }
}
