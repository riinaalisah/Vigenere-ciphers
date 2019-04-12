package Algos;

import Ciphers.Algos.NormalVigenere;
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
        String correct = "ABCDEFGHIJKLMNOPQRSTUVWXYZ\n" +
                        "BCDEFGHIJKLMNOPQRSTUVWXYZA\n" +
                        "CDEFGHIJKLMNOPQRSTUVWXYZAB\n" +
                        "DEFGHIJKLMNOPQRSTUVWXYZABC\n" +
                        "EFGHIJKLMNOPQRSTUVWXYZABCD\n" +
                        "FGHIJKLMNOPQRSTUVWXYZABCDE\n" +
                        "GHIJKLMNOPQRSTUVWXYZABCDEF\n" +
                        "HIJKLMNOPQRSTUVWXYZABCDEFG\n" +
                        "IJKLMNOPQRSTUVWXYZABCDEFGH\n" +
                        "JKLMNOPQRSTUVWXYZABCDEFGHI\n" +
                        "KLMNOPQRSTUVWXYZABCDEFGHIJ\n" +
                        "LMNOPQRSTUVWXYZABCDEFGHIJK\n" +
                        "MNOPQRSTUVWXYZABCDEFGHIJKL\n" +
                        "NOPQRSTUVWXYZABCDEFGHIJKLM\n" +
                        "OPQRSTUVWXYZABCDEFGHIJKLMN\n" +
                        "PQRSTUVWXYZABCDEFGHIJKLMNO\n" +
                        "QRSTUVWXYZABCDEFGHIJKLMNOP\n" +
                        "RSTUVWXYZABCDEFGHIJKLMNOPQ\n" +
                        "STUVWXYZABCDEFGHIJKLMNOPQR\n" +
                        "TUVWXYZABCDEFGHIJKLMNOPQRS\n" +
                        "UVWXYZABCDEFGHIJKLMNOPQRST\n" +
                        "VWXYZABCDEFGHIJKLMNOPQRSTU\n" +
                        "WXYZABCDEFGHIJKLMNOPQRSTUV\n" +
                        "XYZABCDEFGHIJKLMNOPQRSTUVW\n" +
                        "YZABCDEFGHIJKLMNOPQRSTUVWX\n" +
                        "ZABCDEFGHIJKLMNOPQRSTUVWXY\n";
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
