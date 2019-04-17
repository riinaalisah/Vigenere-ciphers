package ciphers.algos;

import ciphers.util.AlphabetArray;
import ciphers.util.Tableau;
import ciphers.io.FileHandler;

import java.io.File;
import java.io.IOException;


public class Decryption {

    /**
     * Method used for decryption
     * @param passphrase Used for row indexing
     * @param textFile File to decrypt
     * @param tableau Used to find correct letters for decrypted text
     * @return Decrypted file
     * @throws IOException
     */
    public File decrypt(String passphrase, File textFile, Tableau tableau) throws IOException {

        FileHandler fileHandler = new FileHandler(textFile.getName(), false);
        int passphraseLength = passphrase.length();
        char[] passphraseInArray = passphrase.toCharArray();
        AlphabetArray alphabet = new AlphabetArray(52);
        alphabet.setAlphabet(tableau.tableau[0]);

        for (int i = 0; i < passphraseInArray.length; i++) {
            passphraseInArray[i] = alphabet.charToUpperCase(passphraseInArray[i]);
        }

        int index = 0;
        boolean endOfFile = false;

        while (true) {
            String word = fileHandler.nextWord();
            if (word.contains("ENDOFFILEREACHED")) {
                endOfFile = true;
                word = word.replace("ENDOFFILEREACHED", "");
            }

            char[] wordInArray = word.toCharArray();
            String wordToWrite = "";

            for (int i = 0; i < wordInArray.length; i++) {

                char charX = wordInArray[i];

                if (!alphabet.containsCharacter(charX)) {
                    wordToWrite += charX;
                    continue;
                }

                char charY = passphraseInArray[index];

                char[] charsAtCharYRow = tableau.tableau[alphabet.getIndexOfCharacter(charY)];


                char letter = '\u0000';
                for (int n = 0; n < charsAtCharYRow.length; n++) {
                    if (charsAtCharYRow[n] == charX) {
                        letter = alphabet.getCharacter(n);
                        break;
                    }
                }
                wordToWrite += letter;

                index++;
                if (index == passphraseLength) {
                    index = 0;
                }

            }
            if (!endOfFile) {
                fileHandler.writeWord(wordToWrite + " ");
            } else {
                fileHandler.writeWord(wordToWrite);
            }

            if (endOfFile) {
                break;
            }
        }
        fileHandler.close();
        return fileHandler.getOutputFile();

    }

}
