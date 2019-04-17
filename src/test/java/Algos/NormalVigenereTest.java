package Algos;

import ciphers.algos.NormalVigenere;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NormalVigenereTest {

    NormalVigenere vc;

    @Before
    public void setUp() {
        vc = new NormalVigenere();
    }


    @Test
    public void tableauIsCorrect() {
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
        for (int y = 0; y < vc.tableau.tableau.length; y++) {
            for (int x = 0; x < vc.tableau.tableau[y].length; x++) {
                tableauInString += vc.tableau.tableau[y][x];
            }
            tableauInString += "\n";
        }
        assertEquals(correct, tableauInString);
    }

}
