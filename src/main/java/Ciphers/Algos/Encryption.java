package Ciphers.Algos;

import Ciphers.IO.FileHandler;
import Ciphers.Util.AlphabetArray;
import Ciphers.Util.Tableau;

import java.io.File;
import java.io.IOException;

public class Encryption {

    /**
     * Method used for encryption
     * @param passphrase Used for row indexing
     * @param textFile File to encrypt
     * @param tableau Used to find correct letters for encrypted text
     * @return
     * @throws IOException
     */
    public File encrypt(String passphrase, File textFile, Tableau tableau) throws IOException {

        FileHandler fileHandler = new FileHandler(textFile.getName(), true);
        char[] passphraseInArray = wordToArray(passphrase);
        int index = 0;
        boolean endOfFile = false;
        String readWord;
        String wordToWrite;
        AlphabetArray alphabet = new AlphabetArray();
        alphabet.setAlphabet(tableau.tableau[0]);


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
                if (!alphabet.alphabetContainsCharacter(charX)) {
                    wordToWrite += charX;
                    continue;
                }

                Character charY = passphraseInArray[index];
                charY = Character.toUpperCase(charY);

                Character letter = tableau.getLetter(alphabet.getIndexOfCharacter(charY), alphabet.getIndexOfCharacter(charX));
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
