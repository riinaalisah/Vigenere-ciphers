package Ciphers.Algos;

import Ciphers.Util.AlphabetArray;
import Ciphers.Util.Tableau;
import Ciphers.IO.FileHandler;

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
        AlphabetArray alphabet = new AlphabetArray();
        alphabet.setAlphabet(tableau.tableau[0]);

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

                Character charX = wordInArray[i];
                charX = Character.toUpperCase(charX);

                if (!alphabet.alphabetContainsCharacter(charX)) {
                    wordToWrite += charX;
                    continue;
                }

                Character charY = passphraseInArray[index];
                charY = Character.toUpperCase(charY);

                char[] charsAtCharYRow = tableau.tableau[alphabet.getIndexOfCharacter(charY)];


                Character letter = null;
                for (int n = 0; n < charsAtCharYRow.length; n++) {
                    //System.out.print(charsAtCharYRow[n]);
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
            fileHandler.writeWord(wordToWrite + " ");
            if (endOfFile) {
                break;
            }
        }
        fileHandler.close();
        return fileHandler.getOutputFile();

    }

}
