import algos.Tableau;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableauTest {
    Tableau t;

    @Before
    public void setup() {
        this.t = new Tableau();
    }

    @Test
    public void normalTableauIsCorrect() {
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
        char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        char[][] tableau = t.create(alphabet);
        String tableauInString = "";
        for (int y = 0; y < tableau.length; y++) {
            for (int x = 0; x < tableau[y].length; x++) {
                tableauInString += tableau[y][x];
            }
            tableauInString += "\n";
        }
        assertEquals(correct, tableauInString);
    }

    @Test
    public void tableauWithKeywordIsCorrect() {
        String correct = "APLETRBCDFGHIJKMNOQSUVWXYZ\n" +
                        "PLETRBCDFGHIJKMNOQSUVWXYZA\n" +
                        "LETRBCDFGHIJKMNOQSUVWXYZAP\n" +
                        "ETRBCDFGHIJKMNOQSUVWXYZAPL\n" +
                        "TRBCDFGHIJKMNOQSUVWXYZAPLE\n" +
                        "RBCDFGHIJKMNOQSUVWXYZAPLET\n" +
                        "BCDFGHIJKMNOQSUVWXYZAPLETR\n" +
                        "CDFGHIJKMNOQSUVWXYZAPLETRB\n" +
                        "DFGHIJKMNOQSUVWXYZAPLETRBC\n" +
                        "FGHIJKMNOQSUVWXYZAPLETRBCD\n" +
                        "GHIJKMNOQSUVWXYZAPLETRBCDF\n" +
                        "HIJKMNOQSUVWXYZAPLETRBCDFG\n" +
                        "IJKMNOQSUVWXYZAPLETRBCDFGH\n" +
                        "JKMNOQSUVWXYZAPLETRBCDFGHI\n" +
                        "KMNOQSUVWXYZAPLETRBCDFGHIJ\n" +
                        "MNOQSUVWXYZAPLETRBCDFGHIJK\n" +
                        "NOQSUVWXYZAPLETRBCDFGHIJKM\n" +
                        "OQSUVWXYZAPLETRBCDFGHIJKMN\n" +
                        "QSUVWXYZAPLETRBCDFGHIJKMNO\n" +
                        "SUVWXYZAPLETRBCDFGHIJKMNOQ\n" +
                        "UVWXYZAPLETRBCDFGHIJKMNOQS\n" +
                        "VWXYZAPLETRBCDFGHIJKMNOQSU\n" +
                        "WXYZAPLETRBCDFGHIJKMNOQSUV\n" +
                        "XYZAPLETRBCDFGHIJKMNOQSUVW\n" +
                        "YZAPLETRBCDFGHIJKMNOQSUVWX\n" +
                        "ZAPLETRBCDFGHIJKMNOQSUVWXY\n";
        char[] alphabet = {'A','P','L','E','T','R','B','C','D','F','G','H','I','J','K','M','N','O','Q','S','U','V','W','X','Y','Z'};
        char[][] correctTableau = t.create(alphabet);
        String tableau = "";
        for (int y = 0; y < correctTableau.length; y++) {
            for (int x = 0; x < correctTableau[y].length; x++) {
                tableau += correctTableau[y][x];
            }
            tableau += "\n";
        }
        assertEquals(correct, tableau);
    }
}
