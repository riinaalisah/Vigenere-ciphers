import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        for (int y = 0; y < vc.matrix.length; y++) {
            for (int x = 0; x < vc.matrix[y].length; x++) {
                tableau += vc.matrix[y][x];
            }
            tableau += "\n";
        }
        assertEquals(correct, tableau);
    }

    @Test
    public void keystreamIsCorrect() {
        vc.encrypt("helsinki", "cats are pretty cool");
        assertEquals("HELSINKIHELSINKIH", vc.keystream);
    }

    @Test
    public void simpleEncryptionIsCorrect() {
        vc.encrypt("flower", "cats are pretty cute");
        assertEquals("Original: CATSAREPRETTYCUTE\nEncrypted: HLHOEIJAFAXKDNIPI\n", outContent.toString());
    }


}
