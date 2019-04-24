package io;

import ciphers.io.FileHandler;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junitx.framework.Assert.assertEquals;

public class FileHandlerTest {
    FileHandler fh;

    @Test
    public void nextWordIsReadCorrectly() throws IOException {
        fh = new FileHandler("textForFileHandlerTests.txt", true);
        assertEquals("this", fh.nextWord());
        assertEquals("text", fh.nextWord());
    }

    @Test
    public void returnsWordPlusENDOFFILEREACHEDWhenFileEnds() throws IOException {
        fh = new FileHandler("textForFileHandlerTests.txt", true);
        String lastWord = "";
        int index = 0;
        while (index < 8) {
            lastWord = fh.nextWord();
            index++;
        }
        assertEquals("class.ENDOFFILEREACHED", lastWord);
    }

    @Test
    public void namesOutputFileCorrectlyWhenEncrypt() throws IOException {
        fh = new FileHandler("textForFileHandlerTests.txt", true);
        assertEquals("textForFileHandlerTests.encrypted.txt", fh.getOutputFile().getName());

    }

    @Test
    public void namesOutputFileCorrectlyWhenDecrypt() throws IOException {
        fh = new FileHandler("textForFileHandlerTests.encrypted.txt", false);
        assertEquals("textForFileHandlerTests.decrypted.txt", fh.getOutputFile().getName());

    }


    @After
    public void tearDown() {
        File decrypted = new File("texts/textForFileHandlerTests.decrypted.txt");
        decrypted.delete();
    }
}
