# Testing Document

## Unit Testing

JUnit has been used for unit testing.

Code coverage:

![](https://github.com/riinaalisah/Vigenere-ciphers/blob/master/documentation/coco.png)


#### Algos
Package contains classes for both Vigenère ciphers and a class for actual encryption/decryption. [Normal Vigenère](https://github.com/riinaalisah/Vigenere-ciphers/blob/master/src/main/java/ciphers/algos/NormalVigenere.java) doesn't need much testing, since it only sets standard alphabet and tableau, and those have their own classes ([AlphabetArray](https://github.com/riinaalisah/Vigenere-ciphers/blob/master/src/main/java/ciphers/util/AlphabetArray.java), [Tableau](https://github.com/riinaalisah/Vigenere-ciphers/blob/master/src/main/java/ciphers/util/Tableau.java)). [Keyed Vigenère](https://github.com/riinaalisah/Vigenere-ciphers/blob/master/src/main/java/ciphers/algos/KeyedVigenere.java) however has many methods for organising the alphabet, and therefore there are also many tests. Tests focus on making sure that the alphabet are organised correctly. 

Encryption and decryption have separate tests, although the logic is situated in same class. The tests make sure that the content of encrypted/decrypted file is correct by comparing them to files created and checked manually.

#### IO

Package contains only [FileHandler](https://github.com/riinaalisah/Vigenere-ciphers/blob/master/src/main/java/ciphers/io/FileHandler.java) class. Tests for FileHandler check that words are read and written correctly, and that created (encrypted/decrypted) files are named correctly.


#### UI

[UserInterface](https://github.com/riinaalisah/Vigenere-ciphers/blob/master/src/main/java/ciphers/ui/UserInterface.java) class is not tested with JUnit. UI is created using JavaFX and it has been tested manually.

#### Util

Util package contains classes [AlphabetArray](https://github.com/riinaalisah/Vigenere-ciphers/blob/master/src/main/java/ciphers/util/AlphabetArray.java) and [Tableau](https://github.com/riinaalisah/Vigenere-ciphers/blob/master/src/main/java/ciphers/util/Tableau.java). Both classes contain quite simple array functions, but they have still been tested extensively, of course.


---

## Performance Testing

The text used for tests is the book [The Odyssey](http://www.gutenberg.org/cache/epub/1727/pg1727.txt) by 
Homer, which has 132 774 words and 685 419 characters.

#### Encryption

There is really no difference between the performance of the two Vigenère ciphers. Both ciphers encrypt the text in about 250ms, and changes in passphrase length, or - in the keyed Vigenère's case - keyword length and different choices do not affect the result. I did multiple runs with both ciphers and different kinds of passphrases, keys and choices, but the time used was always between 230-250ms.


#### Decryption

The results were the same as with encryption: no difference in performance.

