import junitx.framework.FileAssert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class VigenereCipherTest {

    VigenereCipher vc;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        vc = new VigenereCipher();
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
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

        String tableau = "";
        for (int y = 0; y < vc.tableau.length; y++) {
            for (int x = 0; x < vc.tableau[y].length; x++) {
                tableau += vc.tableau[y][x];
            }
            tableau += "\n";
        }
        assertEquals(correct, tableau);
    }


    @Test
    public void simpleTextWithOnlyAlphabetIsCorrect() throws IOException {
        File correct = new File("texts/cat_correct.txt");
        FileWriter fw = new FileWriter("texts/cat_correct.txt");
        fw.write("uphl tzw efxmbq rimx ");
        fw.close();

        File file = new File("texts/cat.txt");
        File encrypted = vc.encrypt("spotti", file);
        FileAssert.assertEquals(correct, encrypted);
    }

    @Test
    public void simpleTextWithSomeSpecialCharactersIsCorrect() throws IOException {
        File correct = new File("texts/uni_correct.txt");
        FileWriter fw = new FileWriter("texts/uni_correct.txt");
        fw.write("a hhnwg udainbwg gvbmfrs bg cfxjxkaaim hy pwagbgsa. x zbdm ai oempgjua lbmsmbgo ah dkxbln hhnoz. ");
        fw.close();

        File file = new File("texts/uni.txt");
        File encrypted = vc.encrypt("spotti", file);
        FileAssert.assertEquals(correct, encrypted);
    }


}
