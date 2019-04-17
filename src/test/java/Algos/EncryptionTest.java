package Algos;

import ciphers.algos.Encryption;
import ciphers.algos.KeyedVigenere;
import ciphers.util.Tableau;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import static junit.framework.TestCase.assertTrue;

public class EncryptionTest {
    String passphrase = "passphrase";
    File file = new File("textForEncryptTests.txt");
    Tableau tableau;
    Encryption encryption;

    @Before
    public void setUp() {
        encryption = new Encryption();
    }

    @Test
    public void encryptionCorrectWithNormalTableau() throws IOException {
        char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        tableau = new Tableau(characters);
        File correct = new File("texts/encryptionTestCorrectNormal.txt");
        File encrypted = encryption.encrypt(passphrase, this.file, tableau);
        assertTrue(FileUtils.contentEquals(correct, encrypted));
    }

    @Test
    public void encryptionCorrectWithKeyedVigenereAllChoicesPicked() throws IOException {
        KeyedVigenere kv = new KeyedVigenere();
        kv.setChoicesAndKey("keyphrase", true, true, true);
        kv.setAlphabet();
        tableau = kv.tableau;
        File correct = new File("texts/encryptionTestCorrectKeyedAllChoices.txt");
        File encrypted = encryption.encrypt("passphrase", file, tableau);
        assertTrue(FileUtils.contentEquals(correct, encrypted));
    }

    @After
    public void tearDown() {
        File remove = new File("texts/textForEncryptTests.encrypted.txt");
        remove.delete();
    }
}
