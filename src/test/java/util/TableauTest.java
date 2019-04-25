package util;

import ciphers.util.Tableau;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableauTest {
    Tableau t;

    @Test
    public void normalTableauIsCorrect() {
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
        char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                           'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        Tableau tab = new Tableau(alphabet);
        String tableauInString = "";
        for (int y = 0; y < tab.tableau.length; y++) {
            for (int x = 0; x < tab.tableau[y].length; x++) {
                tableauInString += tab.getCharacter(y, x);
            }
            tableauInString += "\n";
        }
        assertEquals(correct, tableauInString);
    }

    @Test
    public void tableauWithKeywordIsCorrect() {
        String correct = "APLETRBCDFGHIJKMNOQSUVWXYZapletrbcdfghijkmnoqsuvwxyz\n" +
                        "PLETRBCDFGHIJKMNOQSUVWXYZApletrbcdfghijkmnoqsuvwxyza\n" +
                        "LETRBCDFGHIJKMNOQSUVWXYZAPletrbcdfghijkmnoqsuvwxyzap\n" +
                        "ETRBCDFGHIJKMNOQSUVWXYZAPLetrbcdfghijkmnoqsuvwxyzapl\n" +
                        "TRBCDFGHIJKMNOQSUVWXYZAPLEtrbcdfghijkmnoqsuvwxyzaple\n" +
                        "RBCDFGHIJKMNOQSUVWXYZAPLETrbcdfghijkmnoqsuvwxyzaplet\n" +
                        "BCDFGHIJKMNOQSUVWXYZAPLETRbcdfghijkmnoqsuvwxyzapletr\n" +
                        "CDFGHIJKMNOQSUVWXYZAPLETRBcdfghijkmnoqsuvwxyzapletrb\n" +
                        "DFGHIJKMNOQSUVWXYZAPLETRBCdfghijkmnoqsuvwxyzapletrbc\n" +
                        "FGHIJKMNOQSUVWXYZAPLETRBCDfghijkmnoqsuvwxyzapletrbcd\n" +
                        "GHIJKMNOQSUVWXYZAPLETRBCDFghijkmnoqsuvwxyzapletrbcdf\n" +
                        "HIJKMNOQSUVWXYZAPLETRBCDFGhijkmnoqsuvwxyzapletrbcdfg\n" +
                        "IJKMNOQSUVWXYZAPLETRBCDFGHijkmnoqsuvwxyzapletrbcdfgh\n" +
                        "JKMNOQSUVWXYZAPLETRBCDFGHIjkmnoqsuvwxyzapletrbcdfghi\n" +
                        "KMNOQSUVWXYZAPLETRBCDFGHIJkmnoqsuvwxyzapletrbcdfghij\n" +
                        "MNOQSUVWXYZAPLETRBCDFGHIJKmnoqsuvwxyzapletrbcdfghijk\n" +
                        "NOQSUVWXYZAPLETRBCDFGHIJKMnoqsuvwxyzapletrbcdfghijkm\n" +
                        "OQSUVWXYZAPLETRBCDFGHIJKMNoqsuvwxyzapletrbcdfghijkmn\n" +
                        "QSUVWXYZAPLETRBCDFGHIJKMNOqsuvwxyzapletrbcdfghijkmno\n" +
                        "SUVWXYZAPLETRBCDFGHIJKMNOQsuvwxyzapletrbcdfghijkmnoq\n" +
                        "UVWXYZAPLETRBCDFGHIJKMNOQSuvwxyzapletrbcdfghijkmnoqs\n" +
                        "VWXYZAPLETRBCDFGHIJKMNOQSUvwxyzapletrbcdfghijkmnoqsu\n" +
                        "WXYZAPLETRBCDFGHIJKMNOQSUVwxyzapletrbcdfghijkmnoqsuv\n" +
                        "XYZAPLETRBCDFGHIJKMNOQSUVWxyzapletrbcdfghijkmnoqsuvw\n" +
                        "YZAPLETRBCDFGHIJKMNOQSUVWXyzapletrbcdfghijkmnoqsuvwx\n" +
                        "ZAPLETRBCDFGHIJKMNOQSUVWXYzapletrbcdfghijkmnoqsuvwxy\n";
        char[] alphabet = {'A','P','L','E','T','R','B','C','D','F','G','H','I','J','K','M','N','O','Q','S','U','V','W','X','Y','Z',
                           'a','p','l','e','t','r','b','c','d','f','g','h','i','j','k','m','n','o','q','s','u','v','w','x','y','z'};
        Tableau tab = new Tableau(alphabet);
        String tableau = "";
        for (int y = 0; y < tab.tableau.length; y++) {
            for (int x = 0; x < tab.tableau[y].length; x++) {
                tableau += tab.getCharacter(y, x);
            }
            tableau += "\n";
        }
        assertEquals(correct, tableau);
    }

}
