package ciphers.io;

import java.io.*;

public class FileHandler {

    File inputFile;
    File outputFile;
    FileReader reader;
    FileWriter writer;


    public FileHandler(File inputFile, boolean encrypt) throws IOException {
        System.out.println("Filun nimi " + inputFile.getName());
        this.inputFile = inputFile;
        String fileName = inputFile.getName();

        String[] fileNameInArray = fileName.split("\\.");
        fileName = fileNameInArray[0];
        System.out.println("Parsettu filu " + fileName);

        this.reader = new FileReader(inputFile);
        if (encrypt) {
            this.outputFile = new File("texts/" + fileName + ".encrypted.txt");
        } else {
            this.outputFile = new File("texts/" + fileName + ".decrypted.txt");
        }

        this.writer = new FileWriter(outputFile);
    }

    public File getOutputFile() {
        return this.outputFile;
    }


    public String nextWord() throws IOException {
        String word = "";
        while (true) {
            int characterAsInt = reader.read();
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

    public void close() throws IOException {
        this.writer.close();
    }


}
