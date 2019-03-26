import java.io.File;
import java.util.Scanner;

public class Ciphers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Vigenère ciphers!");
        System.out.println("First enter the name of the text file you want to encrypt");
        String fileName = sc.nextLine();

        /**
         *  check if file is found in texts directory
         */
        if (!new File("texts/" + fileName).exists()) {
            System.out.println("Text file with that name cannot be found.");
        }

        File file = new File("texts/" + fileName);

        System.out.println("Which cipher do you want to use? Enter the correspondent number:");
        System.out.println("1 - Normal Vigenère cipher");
        System.out.println("2 - Keyed Vigenère cipher");
        int cipherNumber = sc.nextInt();

        if (cipherNumber == 1) {
            VigenereCipher vc = new VigenereCipher();
            System.out.println("You chose normal Vigenère cipher");
            System.out.println("Enter passphrase");
            String passphrase = sc.next();


        } else if (cipherNumber == 2) {
            System.out.println("You chose keyed Vigenère cipher");

        } else {
            System.out.println("Invalid input");
        }
    }
}
