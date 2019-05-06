package ciphers.io;

import java.io.*;

/**
 * Used for writing and reading files.
 */
public class FileHandler {

    File inputFile;
    File outputFile;
    FileReader reader;
    FileWriter writer;


    /**
     * Creates a new FileHandler
     *
     * @param inputFile File to be read
     * @param encrypt   True if text is to be encrypted, false if decrypted. Helps with file naming.
     * @throws IOException in case of an error
     */
    public FileHandler(File inputFile, boolean encrypt) throws IOException {
        this.inputFile = inputFile;
        String fileName = inputFile.getName();
        String[] fileNameInArray = fileName.split("\\.");
        fileName = fileNameInArray[0];
        this.reader = new FileReader(inputFile);

        // Create file to be written to
        if (encrypt) {
            this.outputFile = new File("texts/" + fileName + ".encrypted.txt");
        } else {
            this.outputFile = new File("texts/" + fileName + ".decrypted.txt");
        }

        this.writer = new FileWriter(outputFile);
    }

    /**
     * Reads and returns the next word from input file.
     *
     * @return Read word. Adds 'ENDOFFILEREACHED' to the end of the word if it is last.
     * @throws IOException in case of an error
     */
    public String nextWord() throws IOException {
        String word = "";
        while (true) {
            int characterAsInt = reader.read();
            // Check if it is the end of file
            if (characterAsInt == -1) {
                return word + "ENDOFFILEREACHED";
            }

            String character = Character.toString((char) characterAsInt);
            if (character.equals(" ")) {
                break;
            }

            word += character;
        }
        return word;
    }

    public void writeWord(String word) throws IOException {
        writer.write(word);
    }

    public File getOutputFile() {
        return this.outputFile;
    }

    public void close() throws IOException {
        this.writer.close();
    }


}
