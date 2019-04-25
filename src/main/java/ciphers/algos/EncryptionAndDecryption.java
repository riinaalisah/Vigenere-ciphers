package ciphers.algos;

import ciphers.util.AlphabetArray;
import ciphers.util.Tableau;
import ciphers.io.FileHandler;

import java.io.File;
import java.io.IOException;

/**
 * Class for encryption and decryption
 */
public class EncryptionAndDecryption {

    private char[] passphraseInArray;
    private AlphabetArray alphabet;
    private Tableau tableau;
    private boolean encrypt;

    /**
     * Sets attributes needed for encryption/decryption.
     * @param passphrase Passphrase entered by user
     * @param textFile File to be encrypted/decrypted
     * @param tableau Tableau to be used
     * @throws IOException
     */
    public EncryptionAndDecryption(String passphrase, File textFile, Tableau tableau, boolean encrypt) throws IOException {
        // Create FileHandler for writing and reading files
        FileHandler fileHandler;
        if (encrypt) {
            fileHandler = new FileHandler(textFile, true);
        } else {
            fileHandler = new FileHandler(textFile, false);
        }

        // Set alphabet array, used to check alphabet in input file
        this.alphabet = new AlphabetArray(52);
        this.alphabet.setAlphabet(tableau.tableau[0]);

        // Passphrase to upper case (since y-axis in tableau is always in upper case)
        this.passphraseInArray = passphrase.toCharArray();
        for (int i = 0; i < passphraseInArray.length; i++) {
            passphraseInArray[i] = alphabet.charToUpperCase(passphraseInArray[i]);
        }
        this.encrypt = encrypt;
        this.tableau = tableau;
        File outputFile = goThrough(fileHandler);
    }


    /**
     * Goes through given text word by word, and creates words to write to the encrypted/decrypted text.
     * @param fileHandler Used to read and write files
     * @return encrypted/decrypted File, retrieved from fileHandler
     * @throws IOException
     */
    public File goThrough(FileHandler fileHandler) throws IOException {
        int index = 0;
        boolean endOfFile = false;

        while (true) {
            String word = fileHandler.nextWord();

            // Check if end of file is reached
            if (word.contains("ENDOFFILEREACHED")) {
                endOfFile = true;
                word = word.replace("ENDOFFILEREACHED", "");
            }
            // Put read word into character array
            char[] wordInArray = wordToArray(word);
            String wordToWrite = "";

            for (int i = 0; i < wordInArray.length; i++) {
                // Next character in read word
                char charX = wordInArray[i];
                // Check if character is included in alphabet. If not, don't replace it (i.e. '!' stays '!').
                if (!alphabet.containsCharacter(charX)) {
                    wordToWrite += charX;
                    continue;
                }

                // Next character in passphrase
                char charY = passphraseInArray[index];

                // Call appropriate method to retrieve the character to write
                if (encrypt) {
                    wordToWrite += encrypt(charY, charX);
                } else {
                    wordToWrite += decrypt(charY, charX);
                }

                // Increase index number. If end of passphrase, start from beginning
                index++;
                if (index == passphraseInArray.length) {
                    index = 0;
                }
            }

            // If not end of file, add space. Only write word if the end.
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

    private char encrypt(char charY, char charX) {
        return tableau.getCharacter(alphabet.getIndexOfCharacter(charY), alphabet.getIndexOfCharacter(charX));
    }

    private char decrypt(char charY, char charX) {
        // Character array on row with index number of charY
        char[] charsAtCharYRow = tableau.tableau[alphabet.getIndexOfCharacter(charY)];

        // Find charX from that row. Character to write is character with the same index from alphabet
        char character = '\u0000';
        for (int n = 0; n < charsAtCharYRow.length; n++) {
            if (charsAtCharYRow[n] == charX) {
                character = alphabet.getCharacter(n);
                break;
            }
        }
        return character;
    }

    /**
     * Puts a word into a character array
     * @param word Word to be put into array
     * @return Word in a character array
     */
    public char[] wordToArray(String word) {
        char[] array = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            array[i] = word.charAt(i);
        }
        return array;
    }

}
