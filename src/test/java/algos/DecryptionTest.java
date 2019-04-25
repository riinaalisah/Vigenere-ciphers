package algos;

import ciphers.algos.Ciphers;
import ciphers.algos.KeyedVigenere;
import ciphers.util.Tableau;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;


public class DecryptionTest {
    String passphrase = "passphrase";
    Tableau tableau;
    File file;
    File correctFile = new File("src/test/testTexts/decryptionTestCorrect.txt");
    Ciphers decryption;


    @Test
    public void decryptionCorrectWithNormalTableau() throws IOException {
        file = new File("src/test/testTexts/textForDecryptionTestNormal.txt");
        char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        tableau = new Tableau(characters);
        decryption = new Ciphers(passphrase, file, tableau, false);
        File decrypted = new File("texts/textForDecryptionTestNormal.decrypted.txt");
        assertTrue(FileUtils.contentEquals(correctFile, decrypted));
    }


    @Test
    public void decryptionCorrectWithkeyedAllChoices() throws IOException {
        file = new File("src/test/testTexts/textForDecryptTestKeyedAllChoices.txt");
        KeyedVigenere kv = new KeyedVigenere();
        kv.setChoicesAndKey("keyphrase", true, true, true);
        kv.setAlphabet();
        decryption = new Ciphers(passphrase, file, kv.tableau, false);
        File decrypted = new File("texts/textForDecryptTestKeyedAllChoices.decrypted.txt");
        assertTrue(FileUtils.contentEquals(correctFile, decrypted));
    }

    @After
    public void tearDown() {
        File remove1 = new File("texts/textForDecryptionTestNormal.decrypted.txt");
        File remove2 = new File("texts/textForDecryptTestKeyedAllChoices.decrypted.txt");
        remove1.delete();
        remove2.delete();
    }

}
