package ciphers.ui;

import ciphers.algos.EncryptionAndDecryption;
import ciphers.algos.Encryption;
import ciphers.algos.KeyedVigenere;
import ciphers.algos.NormalVigenere;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;


public class Ciphers extends Application {

    private Scene mainScene;
    private Scene encryptionScene;
    private Scene decryptionScene;
    private File file;
    EncryptionAndDecryption encryptAndDecrypt;

    @Override
    public void start(Stage primaryStage) {

        setMainScene(primaryStage);

        primaryStage.setTitle("Vigenère ciphers");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->{
            stop();
        });
    }

    public void setMainScene(Stage primaryStage) {

        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(10));
        Text text1 = new Text("Choose if you want to encrypt or decrypt");
        Text text2 = new Text("Enter the name of the file");
        Label errorLabel = new Label();

        ToggleGroup group = new ToggleGroup();
        HBox radioButtons = new HBox(10);
        radioButtons.setPadding(new Insets(10));
        RadioButton encr = new RadioButton("Encrypt");
        RadioButton decr = new RadioButton("Decrypt");
        encr.setToggleGroup(group);
        encr.setSelected(true);
        decr.setToggleGroup(group);
        radioButtons.getChildren().addAll(encr, decr);


        HBox inputAndButton = new HBox(10);
        inputAndButton.setPadding(new Insets(10));
        TextField fileNameInput = new TextField();
        Button nextButton = new Button("Next");
        nextButton.setOnAction(e->{
            String fileName = fileNameInput.getText();
            if (!new File("texts/" + fileName).exists()) {
                errorLabel.setText("Text file with that name cannot be found, please try again.");
                errorLabel.setTextFill(Color.RED);
            } else {
                file = new File("texts/" + fileName);
                if (encr.isSelected()) {
                    setEncryptionScene(primaryStage);
                    primaryStage.setScene(encryptionScene);
                } else {
                    setDecryptionScene(primaryStage);
                    primaryStage.setScene(decryptionScene);
                }
            }
        });

        inputAndButton.getChildren().addAll(fileNameInput, nextButton);

        mainBox.getChildren().addAll(text1, radioButtons, text2, inputAndButton, errorLabel);
        mainScene = new Scene(mainBox, 420, 200);
    }

    private void setEncryptionScene(Stage primaryStage) {
        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(10));
        Text fileInfo = new Text("File to be encrypted: " + file.getName());
        Text complete = new Text();

        ToggleGroup group = new ToggleGroup();
        HBox radioButtons = new HBox(10);
        RadioButton normal = new RadioButton("Normal Vigenère");
        normal.setToggleGroup(group);
        normal.setSelected(true);
        RadioButton keyed = new RadioButton("Keyed Vigenère");
        keyed.setToggleGroup(group);
        radioButtons.getChildren().addAll(normal, keyed);

        Text passphraseText = new Text("Enter passphrase");
        TextField passphraseInput = new TextField();

        VBox keyedBox = new VBox(10);
        Text keywordText = new Text("Enter keyword");
        TextField keywordInput = new TextField();
        Text choicesText = new Text("Choose how you want the keyword to be implemented");
        VBox choicesBox = new VBox();
        RadioButton keyInReverse = new RadioButton("Keyword in reversed order");
        keyInReverse.setPadding(new Insets(10));
        RadioButton alphInReverse = new RadioButton("Alphabet in reversed order");
        alphInReverse.setPadding(new Insets(10));
        RadioButton keyToRight = new RadioButton("Keyword to the right side of alphabet");
        keyToRight.setPadding(new Insets(10));
        choicesBox.getChildren().addAll(keyInReverse, alphInReverse, keyToRight);
        keyedBox.getChildren().addAll(keywordText, keywordInput,choicesText, choicesBox);

        HBox buttonBox = new HBox(10);

        Button encryptButton = new Button("Encrypt!");
        encryptButton.setOnAction(e->{
            String passphrase = passphraseInput.getText();
            if (normal.isSelected()) {
                NormalVigenere normalVig = new NormalVigenere();
                try {
                    encryptAndDecrypt = new EncryptionAndDecryption(passphrase, file, normalVig.tableau, true);
                    complete.setText("Encryption complete!");

                } catch (Exception ex) {
                    complete.setText("An error occurred. Please check your inputs.");
                }
            } else {
                boolean keyInRev = false;
                boolean alphInRev = false;
                boolean keyRight = false;
                if (keyInReverse.isSelected()) {
                    keyInRev = true;
                }
                if (alphInReverse.isSelected()) {
                    alphInRev = true;
                }
                if (keyToRight.isSelected()) {
                    keyRight = true;
                }
                String key = keywordInput.getText();
                KeyedVigenere keyedVig = new KeyedVigenere();
                keyedVig.setChoicesAndKey(key, keyInRev, alphInRev, keyRight);
                keyedVig.setAlphabet();
                try {
                    encryptAndDecrypt = new EncryptionAndDecryption(passphrase, file, keyedVig.tableau, true);
                    complete.setText("Encryption complete!");
                } catch (Exception ex) {
                    complete.setText("An error occurred. Please check your inputs.");
                }
            }

        });

        Button backButton = new Button("Cancel");
        backButton.setOnAction(e->{
            primaryStage.setScene(mainScene);
        });

        buttonBox.getChildren().addAll(encryptButton, backButton);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldToggle, Toggle newToggle) {
                if (group.getSelectedToggle() == normal) {
                    mainBox.getChildren().remove(keyedBox);
                } else {
                    mainBox.getChildren().add(4, keyedBox);
                }
            }
        });

        mainBox.getChildren().addAll(fileInfo, radioButtons, passphraseText, passphraseInput, buttonBox, complete);
        encryptionScene = new Scene(mainBox, 400, 450);
    }

    private void setDecryptionScene(Stage primaryStage) {
        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(10));
        Text fileInfo = new Text("File to be decrypted: " + file.getName());
        Text complete = new Text();

        Text whichCipherUsed = new Text("Choose which cipher was used for encryption");
        ToggleGroup group = new ToggleGroup();
        HBox radioButtons = new HBox(10);
        RadioButton normal = new RadioButton("Normal Vigenère");
        normal.setToggleGroup(group);
        normal.setSelected(true);
        RadioButton keyed = new RadioButton("Keyed Vigenère");
        keyed.setToggleGroup(group);
        radioButtons.getChildren().addAll(normal, keyed);

        Text passphraseText = new Text("Enter passphrase that was used for encryption");
        TextField passphraseInput = new TextField();

        VBox keyedBox = new VBox(10);
        Text keywordText = new Text("Enter keyword that was implemented in encryption");
        TextField keywordInput = new TextField();
        Text choicesText = new Text("Choose how the keyword was implemented");
        VBox choicesBox = new VBox();
        RadioButton keyInReverse = new RadioButton("Keyword in reversed order");
        keyInReverse.setPadding(new Insets(10));
        RadioButton alphInReverse = new RadioButton("Alphabet in reversed order");
        alphInReverse.setPadding(new Insets(10));
        RadioButton keyToRight = new RadioButton("Keyword to the right side of alphabet");
        keyToRight.setPadding(new Insets(10));
        choicesBox.getChildren().addAll(keyInReverse, alphInReverse, keyToRight);
        keyedBox.getChildren().addAll(keywordText, keywordInput,choicesText, choicesBox);

        HBox buttonBox = new HBox(10);

        Button decryptButton = new Button("Decrypt!");
        decryptButton.setOnAction(e->{
            String passphrase = passphraseInput.getText();
            if (normal.isSelected()) {
                NormalVigenere normalVig = new NormalVigenere();
                try {
                    encryptAndDecrypt = new EncryptionAndDecryption(passphrase, file, normalVig.tableau, false);
                    complete.setText("Decryption complete!");

                } catch (Exception ex) {
                    complete.setText("An error occurred. Please check your inputs.");
                }
            } else {
                boolean keyInRev = false;
                boolean alphInRev = false;
                boolean keyRight = false;
                if (keyInReverse.isSelected()) {
                    keyInRev = true;
                }
                if (alphInReverse.isSelected()) {
                    alphInRev = true;
                }
                if (keyToRight.isSelected()) {
                    keyRight = true;
                }
                String key = keywordInput.getText();
                KeyedVigenere keyedVig = new KeyedVigenere();
                keyedVig.setChoicesAndKey(key, keyInRev, alphInRev, keyRight);
                keyedVig.setAlphabet();
                try {
                    encryptAndDecrypt = new EncryptionAndDecryption(passphrase, file, keyedVig.tableau, false);
                    complete.setText("Decryption complete!");
                } catch (Exception ex) {
                    complete.setText("An error occurred. Please check your inputs.");
                }
            }
        });

        Button backButton = new Button("Cancel");
        backButton.setOnAction(e->{
            primaryStage.setScene(mainScene);
        });

        buttonBox.getChildren().addAll(decryptButton, backButton);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldToggle, Toggle newToggle) {
                if (group.getSelectedToggle() == normal) {
                    mainBox.getChildren().remove(keyedBox);
                } else {
                    mainBox.getChildren().add(4, keyedBox);
                }
            }
        });

        mainBox.getChildren().addAll(fileInfo, radioButtons, passphraseText, passphraseInput, buttonBox, complete);
        decryptionScene = new Scene(mainBox, 400, 450);

    }



    @Override
    public void stop() {
        System.out.println("Closing");
    }


    public static void main(String[] args) {
        launch(args);
    }






    /*

    static Encryption encryption = new Encryption();
    static EncryptionAndDecryption decryption = new EncryptionAndDecryption();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        System.out.println("WELCOME TO VIGENÈRE CIPHERS!");
        System.out.println();

        File file = chooseFile();
        System.out.println();

        int encryptOrDecrypt = chooseEncryptOrDecrypt();
        System.out.println();

        if (encryptOrDecrypt == 2) {
            decryptChosen(file);

        } else {
            int cipherNumber = chooseCipher();
            System.out.println();
if (!new File("texts/" + fileName).exists()) {
            if (cipherNumber == 1) {
                normalVigenereChosen(file);

            } else if (cipherNumber == 2) {
                keyedVigenereChosen(file);
            }
        }


    }

    public static File chooseFile() throws IOException {
        String fileName;
        while (true) {
            System.out.println("Enter the name of the text file you want to encrypt:");
            fileName = reader.readLine();

            /**
             *  check if file is found in texts directory
             */



    /*
            if (!new File("texts/" + fileName).exists()) {
                System.out.println("Text file with that name cannot be found, please try again.");
                System.out.println();
            } else {
                break;
            }
        }
        return new File("texts/" + fileName);
    }

    public static int chooseEncryptOrDecrypt() {
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


    public static int chooseCipher() throws IOException {
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

    public static void normalVigenereChosen(File file) throws IOException {
        NormalVigenere nv = new NormalVigenere();
        System.out.println("You chose normal Vigenère cipher.");
        System.out.println("Enter passphrase:");
        String passphrase = reader.readLine();

        File encrypted = encryption.encrypt(passphrase, file, nv.tableau);
        System.out.println("Encryption complete");

    }

    public static void keyedVigenereChosen(File file) throws IOException {
        KeyedVigenere kv = new KeyedVigenere();
        System.out.println("You chose keyed Vigenère cipher.");
        System.out.println("Enter keyword:");
        String key = reader.readLine();
        System.out.println();

        System.out.println("Choose how you want to implement the keyword.");
        System.out.println("Enter \"y\" for the choices you want and \"n\" for the choices you don't want.");
        System.out.println();

        String keyInReverse = keyInReverseOrder();
        String alphInReverse = alphabetInReverseOrder();
        String keyToEnd = keywordToEndOfAlphabet();

        System.out.println("Enter passphrase:");
        String passphrase = reader.readLine();

        kv.setChoicesAndKey(key, keyInReverse, alphInReverse, keyToEnd);
        kv.setAlphabet();

        encryption.encrypt(passphrase, file, kv.tableau);
        System.out.println("Encryption complete");
    }

    public static String keyInReverseOrder() throws IOException {
        String keyInReverse;
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
        return keyInReverse;
    }

    public static String alphabetInReverseOrder() throws IOException {
        String alphInReverse;
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
        return alphInReverse;
    }

    public static String keywordToEndOfAlphabet() throws IOException {
        String keyToEnd;
        while (true) {
            System.out.println("Keyword to the end of the alphabet: ");
            keyToEnd = reader.readLine();
            boolean invalidInput = checkInputForChoices(keyToEnd);
            if (invalidInput) {
                printInvalidInput();
            } else {
                break;
            }
        }
        return keyToEnd;
    }


    public static boolean checkInputForChoices(String input) {
        boolean invalid = false;
        if (!input.equals("y") && !input.equals("n")) {
            invalid = true;
        }
        return invalid;
    }

    public static void decryptChosen(File file) throws IOException {

        int normalOrKeyed = decryptedWithNormalOrKeyed(file);
        if (normalOrKeyed == 2) {
            decryptedWithKeyed(file);

        } else {
            System.out.println("Enter the passphrase used for encryption: ");
            String passphrase = reader.readLine();

            char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            ArrayList<Character> characters;
            characters = new ArrayList<Character>();
            for (int i = 0; i < alphabet.length; i++) {
                characters.add(alphabet[i]);
            }

            Tableau tableau = new Tableau(alphabet);
            decryption.decrypt(passphrase, file, tableau);
        }

    }

    public static int decryptedWithNormalOrKeyed(File file) {
        int normalOrKeyed;
        while (true) {
            System.out.println("Is the file encrypted with normal or keyed Vigenère? Enter the correspondet number:");
            System.out.println("1 - Normal Vignère");
            System.out.println("2 - Keyed Vigenère");
            try {
                normalOrKeyed = Integer.parseInt(reader.readLine());
                if (normalOrKeyed != 1 && normalOrKeyed != 2) {
                    printInvalidInput();
                } else {
                    break;
                }
            } catch (Exception e) {
                printInvalidInput();
            }
        }
        return normalOrKeyed;
    }

    public static void decryptedWithKeyed(File file) throws IOException {
        KeyedVigenere kv = new KeyedVigenere();
        System.out.println("Enter the keyword that was implemented in the alphabet:");
        String key = reader.readLine();
        System.out.println("Which choices were picked for implementing the keyword?");
        System.out.println("Enter \"y\" for the picked choices and \"n\" for the unpicked choices.");
        String keyInReverse = keyInReverseOrder();
        String alphInReverse = alphabetInReverseOrder();
        String keyToEnd = keywordToEndOfAlphabet();
        System.out.println("Enter passphrase that was used for encryption:");
        String passphrase = reader.readLine();
        kv.setChoicesAndKey(key, keyInReverse, alphInReverse, keyToEnd);
        kv.setAlphabet();
        decryption.decrypt(passphrase, file, kv.tableau);

    }

    public static void printInvalidInput() {
        System.out.println("INVALID INPUT. Please try again.");
        System.out.println();
    }
    */
}
