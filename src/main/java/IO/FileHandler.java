package IO;

import java.io.*;

public class FileHandler {

    File inputFile;
    File outputFile;
    FileReader reader;
    FileWriter writer;


    public FileHandler(String fileName) throws IOException {
        this.inputFile = new File("texts/" + fileName);
        this.reader = new FileReader(inputFile);
        this.outputFile = new File("texts/" + fileName + "." + "encrypted.txt");
        this.writer = new FileWriter(outputFile);
    }

    public File getOutputFile() {
        return this.outputFile;
    }


    public String nextWord() throws IOException {
        String word = "";
        while (true) {
            char character = (char) reader.read();
            if (character == ' ' || character == '\uFFFF') {
                break;
            }
            word += character;
        }
        System.out.println("NEXT WORD " + word);
        return word;
    }

    public void writeWord(String word) throws IOException {
        writer.write(word);
    }


    public void close() throws IOException {
        this.writer.close();
    }


}
