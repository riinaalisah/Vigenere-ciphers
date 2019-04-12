package Algos;

import Ciphers.Algos.Encryption;
import Ciphers.Algos.KeyedVigenere;
import Ciphers.Util.Tableau;
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

    // TODO !!!NEEDS TO BE CORRECTED WHEN ALGORITHMS ARE MADE CASE SENSITIVE!!!
    @Test
    public void encryptionCorrectWithNormalTableau() throws IOException {
        char[] characters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        tableau = new Tableau(characters);
        File correct = new File("texts/encryptionTestCorrectNormal.txt");
        File encrypted = encryption.encrypt(passphrase, this.file, tableau);
        assertTrue(FileUtils.contentEquals(correct, encrypted));
    }


    // TODO !! ALSO NEEDS TO BE FIXED!!
    @Test
    public void encryptionCorrectWithKeyedVigenereAllChoicesPicked() throws IOException {
        KeyedVigenere kv = new KeyedVigenere();
        kv.setChoicesAndKey("keyphrase", "y", "y", "y");
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
