package ui;

import algos.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ciphers {

    static Encryption encryption = new Encryption();
    static Decryption decryption = new Decryption();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("WELCOME TO VIGENÈRE CIPHERS!");
        System.out.println();


        File file = chooseFile(reader);
        System.out.println();

        int encryptOrDecrypt = chooseEncryptOrDecrypt(reader);
        System.out.println();


        if (encryptOrDecrypt == 2) {
            decryptChosen(reader, file);

        } else {
            int cipherNumber = chooseCipher(reader);
            System.out.println();

            if (cipherNumber == 1) {
                normalVigenereChosen(reader, file);

            } else if (cipherNumber == 2) {
                keyedVigenereChosen(reader, file);
            }
        }


    }

    public static int chooseEncryptOrDecrypt(BufferedReader reader) {
        int encryprOrDecrypt;
        while (true) {
            System.out.println("Do you want to encrypt or decrypt the file? Enter the correspondent number:");
            System.out.println("1 - Encrypt");
            System.out.println("2 - Decrypt");

            try {
                encryprOrDecrypt = Integer.parseInt(reader.readLine());
                if (encryprOrDecrypt != 1 && encryprOrDecrypt != 2) {
                    printInvalidInput();
                } else {
                    break;
                }
            } catch (Exception e) {
                printInvalidInput();
            }
        }
        return encryprOrDecrypt;
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

    public static void decryptChosen(BufferedReader reader, File file) throws IOException {
        System.out.println("Enter the passphrase ");
        String passphrase = reader.readLine();

        char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        ArrayList<Character> characters ;
        characters = new ArrayList<Character>();
        for (int i = 0; i < alphabet.length; i++) {
            characters.add(alphabet[i]);
        }

        Tableau tableau = new Tableau(alphabet);
        decryption.decrypt(passphrase, file, tableau, characters);
    }

    public static void printInvalidInput() {
        System.out.println("INVALID INPUT. Please try again.");
        System.out.println();
    }
}
