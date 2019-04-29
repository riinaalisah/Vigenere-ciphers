package ciphers.ui;

import ciphers.algos.Ciphers;
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


public class UserInterface extends Application {

    private Scene mainScene;
    private Scene encryptionScene;
    private Scene decryptionScene;
    private File file;
    Ciphers encryptAndDecrypt;

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
                    encryptAndDecrypt = new Ciphers(passphrase, file, normalVig.getTableau(), true);
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
                KeyedVigenere keyedVig = new KeyedVigenere(key, keyInRev, alphInRev, keyRight);
                keyedVig.organiseAlphabet();
                try {
                    encryptAndDecrypt = new Ciphers(passphrase, file, keyedVig.getTableau(), true);
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
                    encryptAndDecrypt = new Ciphers(passphrase, file, normalVig.getTableau(), false);
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
                KeyedVigenere keyedVig = new KeyedVigenere(key, keyInRev, alphInRev, keyRight);
                keyedVig.organiseAlphabet();
                try {
                    encryptAndDecrypt = new Ciphers(passphrase, file, keyedVig.getTableau(), false);
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
                    mainBox.getChildren().add(5, keyedBox);
                }
            }
        });

        mainBox.getChildren().addAll(fileInfo, whichCipherUsed, radioButtons, passphraseText, passphraseInput, buttonBox, complete);
        decryptionScene = new Scene(mainBox, 400, 450);
    }

    @Override
    public void stop() {
        System.out.println("Closing");
    }


    public static void main(String[] args) {
        launch(args);
    }

}
