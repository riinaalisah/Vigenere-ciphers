package ui;

import algos.KeyedVigenere;
import algos.NormalVigenere;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ciphers {

    public static void main(String[] args) throws IOException {
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
            NormalVigenere vc = new NormalVigenere();
            System.out.println("You chose normal Vigenère cipher");
            System.out.println("Enter passphrase");
            String passphrase = sc.next();
            vc.encrypt(passphrase, file);

        } else if (cipherNumber == 2) {

            System.out.println("You chose keyed Vigenère cipher");
            System.out.println("Enter keyword");
            String key = sc.next();
            System.out.println("Choose how you want to implement the keyword.\nEnter \"y\" for the choices" +
                    " you want and \"n\" for the choices you don't want.");
            System.out.print("Keyword in reverse order: ");
            String keyInReverse = sc.next();
            System.out.print("Alphabet in reverse order: ");
            String alphInReverse = sc.next();
            System.out.print("Keyword to the end of the alphabet: ");
            String keyToEnd = sc.next();

            KeyedVigenere kv = new KeyedVigenere(key, keyInReverse, alphInReverse, keyToEnd);

        } else {
            System.out.println("Invalid input");
        }
    }
}
