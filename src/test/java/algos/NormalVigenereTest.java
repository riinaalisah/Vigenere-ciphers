package algos;

import ciphers.algos.NormalVigenere;
import ciphers.util.AlphabetArray;
import ciphers.util.Tableau;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NormalVigenereTest {

    NormalVigenere vigenere;

    @Before
    public void setUp() {
        vigenere = new NormalVigenere();
    }

    @Test
    public void alphabetAreSetCorrectly() {
        String correct = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String alphabetInString = "";
        AlphabetArray alphabetArray = vigenere.getAlphabet();
        char[] alphabet = alphabetArray.getAlphabet();
        for (int i = 0; i < alphabet.length; i++) {
            alphabetInString += alphabet[i];
        }
        assertEquals(correct, alphabetInString);
    }


    @Test
    public void tableauIsSetCorrectly() {
        String correct = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\n" +
                        "BCDEFGHIJKLMNOPQRSTUVWXYZAbcdefghijklmnopqrstuvwxyza\n" +
                        "CDEFGHIJKLMNOPQRSTUVWXYZABcdefghijklmnopqrstuvwxyzab\n" +
                        "DEFGHIJKLMNOPQRSTUVWXYZABCdefghijklmnopqrstuvwxyzabc\n" +
                        "EFGHIJKLMNOPQRSTUVWXYZABCDefghijklmnopqrstuvwxyzabcd\n" +
                        "FGHIJKLMNOPQRSTUVWXYZABCDEfghijklmnopqrstuvwxyzabcde\n" +
                        "GHIJKLMNOPQRSTUVWXYZABCDEFghijklmnopqrstuvwxyzabcdef\n" +
                        "HIJKLMNOPQRSTUVWXYZABCDEFGhijklmnopqrstuvwxyzabcdefg\n" +
                        "IJKLMNOPQRSTUVWXYZABCDEFGHijklmnopqrstuvwxyzabcdefgh\n" +
                        "JKLMNOPQRSTUVWXYZABCDEFGHIjklmnopqrstuvwxyzabcdefghi\n" +
                        "KLMNOPQRSTUVWXYZABCDEFGHIJklmnopqrstuvwxyzabcdefghij\n" +
                        "LMNOPQRSTUVWXYZABCDEFGHIJKlmnopqrstuvwxyzabcdefghijk\n" +
                        "MNOPQRSTUVWXYZABCDEFGHIJKLmnopqrstuvwxyzabcdefghijkl\n" +
                        "NOPQRSTUVWXYZABCDEFGHIJKLMnopqrstuvwxyzabcdefghijklm\n" +
                        "OPQRSTUVWXYZABCDEFGHIJKLMNopqrstuvwxyzabcdefghijklmn\n" +
                        "PQRSTUVWXYZABCDEFGHIJKLMNOpqrstuvwxyzabcdefghijklmno\n" +
                        "QRSTUVWXYZABCDEFGHIJKLMNOPqrstuvwxyzabcdefghijklmnop\n" +
                        "RSTUVWXYZABCDEFGHIJKLMNOPQrstuvwxyzabcdefghijklmnopq\n" +
                        "STUVWXYZABCDEFGHIJKLMNOPQRstuvwxyzabcdefghijklmnopqr\n" +
                        "TUVWXYZABCDEFGHIJKLMNOPQRStuvwxyzabcdefghijklmnopqrs\n" +
                        "UVWXYZABCDEFGHIJKLMNOPQRSTuvwxyzabcdefghijklmnopqrst\n" +
                        "VWXYZABCDEFGHIJKLMNOPQRSTUvwxyzabcdefghijklmnopqrstu\n" +
                        "WXYZABCDEFGHIJKLMNOPQRSTUVwxyzabcdefghijklmnopqrstuv\n" +
                        "XYZABCDEFGHIJKLMNOPQRSTUVWxyzabcdefghijklmnopqrstuvw\n" +
                        "YZABCDEFGHIJKLMNOPQRSTUVWXyzabcdefghijklmnopqrstuvwx\n" +
                        "ZABCDEFGHIJKLMNOPQRSTUVWXYzabcdefghijklmnopqrstuvwxy\n";
        String tableauInString = "";
        Tableau t = vigenere.getTableau();
        char[][] tableau = t.getTableau();
        for (int y = 0; y < tableau.length; y++) {
            for (int x = 0; x < tableau[y].length; x++) {
                tableauInString += tableau[y][x];
            }
            tableauInString += "\n";
        }
        assertEquals(correct, tableauInString);
    }

}
