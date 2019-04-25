## Implementation

#### NormalVigenere

The class is used just for creating an array of alphabet and a tableau for encryption/decryption.

#### KeyedVigenere

Since in keyed Vigenère cipher a keyword is implemented into the alphabet, the Keyed Vigenere class contains methods
 for arranging the alphabet key by user's input choices. Alphabet are stored in an array, which in the beginning contains
  alphabet in alphabetical order, and the class methods shift places of alphabet in the array and implement the entered
   keyword into the array appropriately. 

#### Encryption

The encryption method uses File handler to read a file word by word. Word is split into an array of characters,
 which is then processed character by character. Then the correct alphabet is read from tableau by using index of read
  character (x-axis) and index of next character from passphrase (y-axis), as in . The passphrase is
   read character by character again and again until the whole file is encrypted. When the encryption is complete,
    the method returns the encrypted file. 


#### Ciphers

Both encryption and decryption are done in this class. File is read word by word (using FileHandler). Then the word is split into an array of characters, which is then processed character by character. After every read character, the next character of passphrase is retrieved. Here encryption and decryption differ: with encryption, the correct character to write is fetched from tableau by index of read character (x) and index of passphrase character (y), as in ``tableau[y][x]``. With decryption, index of read character is found from the row with row number y (the index on the next character from passphrase) as in ``char[] = tableau[y]`` and the character to write is found from the alphabet key using the found index, as in ``letter = alphabetKey[char[indexOfReadChar]]``. This is repeated until the end of file is reached.


#### FileHandler

File handler class is in charge of reading the file chosen by user, and creating and writing the encrypted/decrypted text
 into a new file. It reads and writes files word by word.
 
#### Tableau
Tableau is the key element of the ciphers. Tableau is a two-dimensional array and it is created by inserting 
alphabet key to every row. After every row the alphabet switch places to left, so the first alphabet is the last alphabet
 on the next row.

#### UserInterface

UI is created with JavaFX. There are three different scenes: main scene, encryption scene and decryption scene.

###### Main scene
User chooses if they want to encrypt or decrypt and enters the name of the file to be used. The application informs if the name is incorrect, i.e. if a file with that name doesn't exist in application folder *texts*.

###### Encryption scene
Here user chooses which cipher they want to use, enters the passphrase, and in keyed Vigenère's case they also need to enter the key and choose how they want it to be implemented into the alphabet. 

###### Decryption scene
This scene is pretty much the same as encryption scene, only the texts are different. 




