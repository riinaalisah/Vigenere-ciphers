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
        char[] passphraseInArray = wordToArray(passphrase);
        int index = 0;
        boolean endOfFile = false;
        String readWord;
        String wordToWrite;


        while (true) {
            readWord = fileHandler.nextWord();

            // check if end of file is reached
            if (readWord.contains("ENDOFFILEREACHED")) {
                endOfFile = true;
                readWord = readWord.replace("ENDOFFILEREACHED", "");
            }

            char[] wordInArray = wordToArray(readWord);
            wordToWrite = "";

            for (int i = 0; i < wordInArray.length; i++) {
                Character charX = wordInArray[i];
                charX = Character.toUpperCase(charX);

                // check if character isn't included in alphabet (i.e. special character)
                if (!characters.contains(charX)) {
                    wordToWrite += charX;
                    continue;
                }

                Character charY = passphraseInArray[index];
                charY = Character.toUpperCase(charY);

                Character letter = tableau.getLetter(characters.indexOf(charY), characters.indexOf(charX));
                wordToWrite += letter;

                index++;
                if (index == passphrase.length()) {
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


    public char[] wordToArray(String word) {
        char[] array = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            array[i] = word.charAt(i);
        }
        return array;
    }
}
