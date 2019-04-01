package ui;

import algos.Encryption;
import algos.KeyedVigenere;
import algos.NormalVigenere;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Scanner;

public class Ciphers {

    static Encryption encryption = new Encryption();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("WELCOME TO VIGENÈRE CIPHERS!");
        System.out.println();

        File file = chooseFile(reader);
        System.out.println();
        int cipherNumber = chooseCipher(reader);
        System.out.println();

        if (cipherNumber == 1) {
            normalVigenereChosen(reader, file);

        } else if (cipherNumber == 2) {
            keyedVigenereChosen(reader, file);
        }
    }

    public static File chooseFile(BufferedReader reader) throws IOException {
        String fileName;
        while (true) {
            System.out.println("Enter the name of the text file you want to encrypt:");
            fileName = reader.readLine();

            /**
             *  check if file is found in texts directory
             */
            if (!new File("texts/" + fileName).exists()) {
                System.out.println("Text file with that name cannot be found, please try again.");
                System.out.println();
            } else {
                break;
            }
        }

        return new File("texts/" + fileName);
    }

    public static int chooseCipher(BufferedReader reader) throws IOException {
        int cipherNumber;
        while (true) {
            System.out.println("Which cipher do you want to use? Enter the correspondent number:");
            System.out.println("1 - Normal Vigenère cipher");
            System.out.println("2 - Keyed Vigenère cipher");
            try {
                cipherNumber = Integer.parseInt(reader.readLine());
                if (cipherNumber != 1 && cipherNumber != 2) {
                    printInvalidInput();
                } else {
                    break;
                }
            } catch (Exception e) {
                printInvalidInput();
            }

        }
        return cipherNumber;
    }

    public static void normalVigenereChosen(BufferedReader reader, File file) throws IOException {
        NormalVigenere nv = new NormalVigenere();
        System.out.println("You chose normal Vigenère cipher.");
        System.out.println("Enter passphrase:");
        String passphrase = reader.readLine();

        File encrypted = encryption.encrypt(passphrase, file, nv.tableau, nv.characters);
        System.out.println("Encryption complete");

    }

    public static void keyedVigenereChosen(BufferedReader reader, File file) throws IOException {
        KeyedVigenere kv = new KeyedVigenere();
        System.out.println("You chose keyed Vigenère cipher.");
        System.out.println("Enter keyword:");
        String key = reader.readLine();
        System.out.println();

        System.out.println("Choose how you want to implement the keyword.");
        System.out.println("Enter \"y\" for the choices you want and \"n\" for the choices you don't want.");
        System.out.println();

        String keyInReverse;
        String alphInReverse;
        String keyToRight;

        while (true) {
            System.out.println("Keyword in reverse order: ");
            keyInReverse = reader.readLine();
            boolean invalidInput = checkInputForChoices(keyInReverse);
            if (invalidInput) {
                printInvalidInput();
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Alphabet in reverse order: ");
            alphInReverse = reader.readLine();
            boolean invalidInput = checkInputForChoices(alphInReverse);
            if (invalidInput) {
                printInvalidInput();
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Keyword to the end of the alphabet: ");
            keyToRight = reader.readLine();
            boolean invalidInput = checkInputForChoices(keyToRight);
            if (invalidInput) {
                printInvalidInput();
            } else {
                break;
            }
        }

        System.out.println("Enter passphrase:");
        String passphrase = reader.readLine();

        kv.setChoicesAndKey(key, keyInReverse, alphInReverse, keyToRight);
        kv.setAlphabet();

        for (int i = 0; i < kv.characters.size(); i++) {
            System.out.print(kv.characters.get(i));
        }

        encryption.encrypt(passphrase, file, kv.tableau, kv.characters);
        System.out.println("Encryption complete");
    }

    public static boolean checkInputForChoices(String input) {
        boolean invalid = false;
        if (!input.equals("y") && !input.equals("n")) {
            invalid = true;
        }
        return invalid;
    }

    public static void printInvalidInput() {
        System.out.println("INVALID INPUT. Please try again.");
        System.out.println();
    }
}
