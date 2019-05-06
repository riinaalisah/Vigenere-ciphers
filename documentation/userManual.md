# User Manual

Download jar file from [release](https://github.com/riinaalisah/Vigenere-ciphers/releases/tag/Final)
and add folder *texts* to the same directory with the jar file. Application is started with command
```
java -jar ciphers.jar
```

### Text Files

Application assumes that files to be encrypted/decrypted are situated in a folder called *texts*.
Encrypted/decrypted files are added to the same folder.


### First View

![](https://github.com/riinaalisah/Vigenere-ciphers/blob/master/documentation/pictures/firstView.png)

In the first view user chooses if they want to encrypt or decrypt a text and they enter the
name of the text file to be encrypted/decrypted. If a file with entered name cannot be found in folder *texts*,
application shows an error message.

### Second View, Encryption

![](https://github.com/riinaalisah/Vigenere-ciphers/blob/master/documentation/pictures/secondNormal.png) ![](https://github.com/riinaalisah/Vigenere-ciphers/blob/master/documentation/pictures/secondKeyed.png)

In the second view (when user has chosen to encrypt) user chooses which cipher they want to use. If they choose normal Vigenère,
they just need to enter the passphrase they want to use for encryption. If keyed Vigenère is chosen, they also need to enter the keyword
they want to implement to the alphabet and choose how they want to implement it. If no choices are picked, keyword and alphabet
are in standard order and keyword is situated to the beginning of the alphabet.

If some inputs are missing or faulty (i.e. passphrase contains spaces or special characters), application informs the user and asks them to check their inputs.

Encryption is done by clicking the button *Encrypt!* and user can return to first view by clicking the button *Cancel*. 

### Second View, Decryption

When user has chosen to decrypt a file, the second view is almost identical to the second view when encryption is chosen.
Inputs are the same, but they just need to match the inputs that were used when the file was encrypted.

Decryption is done by clicking the button *Decrypt!* and user can return to first view by clicking the button *Cancel*.
