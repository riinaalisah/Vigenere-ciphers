## Implementation

#### Normal Vigenere *(might be removed later)*

The class is used just for creating list of alphabet and a tableau for encryption/decryption.

#### Keyed Vigenere

Since in keyed Vigenère cipher a keyword is implemented into the alphabet, the Keyed Vigenere class contains methods
 for arranging the alphabet key by user's input choices. Alphabet are stored in an array, which in the beginning contains
  alphabet in alphabetical order, and the class methods shift places of alphabet in the array and implement the entered
   keyword into the array appropriately. 

#### Encryption

The encryption method uses File handler to read a file word by word. Word is split into an array of characters,
 which is then processed character by character. Then the correct alphabet is read from tableau by using index of read
  character (x-axis) and index of next character from passphrase (y-axis), as in ``tableau[y][x]``. The passphrase is
   read character by character again and again until the whole file is encrypted. When the encryption is complete,
    the method returns the encrypted file. 

#### Decryption

Decryption works mostly the same way as encryption does. The passphrase is read the same way (character by character),
 but the correct letter to write is found from the alphabet key. First, index of read character is found from the row with
  row number y (the index on the next character from passphrase), as in ``char[] = tableau[y]`` and the letter to write
   is found from the alphabet key using the found index, as in ``letter = alphabetKey[char[indexOfReadChar]]``

#### File Handler

File handler class is in charge of reading the file chosen by user, and creating and writing the encrypted/decrypted text
 into a new file. It reads and writes files word by word.
 
#### Tableau
Tableau is the key element of the ciphers. Tableau is a two-dimensional array and it is created by inserting 
alphabet key to every row. After every row the alphabet switch places to left, so the first alphabet is the last alphabet
 on the next row.

#### User Interface (class *ciphers*)

UI is a simple text based interface with a few steps:
1. User chooses the file to encrypt/decrypt
2. User chooses if they want to encrypt or decrypt
    - If **encryption** was chosen:
        1. User chooses which cipher they want to use
            - If normal Vignère was chosen:
                1. User enters passphrase
            
            - If keyed Vigenère was chosen:
                1. User enters keyword
                2. User chooses how they want to implement the keyword
        
    - If **decryption** was chosen:
        1. User answers which cipher was used to encrypt the text
            - If normal Vigenère was used:
                1. User enters the passphrase that was used
            - If Keyed Vigenère was used:
                1. User enters the keyword that was used
                2. User answers how the keyword was implemented
                3. User enter the passphrase that was used
                
3. Encryption/decryption happens
4. Output: how much time was used



