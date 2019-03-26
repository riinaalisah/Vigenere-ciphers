# Specification Document

The aim for this project is to create a program that can encrypt a message using one of two different versions of Vigenère cipher.
 When the message is encrypted, the program creates a text file of the encrypted message.
 
I chose this subject because I am interested in data encryption and information security. I have not done anything similar before
 and that is why I thought Vigenère ciphers would be a good place to start. I also like the  quite simple but efficient idea behind them.


### Algorithms, Data Structures and Time Complexity
The chosen Vigenère ciphers are normal Vigenère cipher and keyed Vigenère cipher. 

**Normal Vigenére cipher** uses a passphrase that is repeated until it is as long as the message. Then the message is encrypted using normal *tabula recta*.

**Keyed Vigenère cipher** uses a different tableau instead. A passphrase is given, but it is incorporated in the alphabet key used in the tableau. Different choices can also be applied: 
- alphabet in reverse order
- keyword on left or right side of the alphabet key
- keyword in reverse order 

The goal is to have time complexity of O(n^2). Since both algorithms use a tableau, two nested for loops are needed.

The main data structures are arrays, since the message needs to be encrypted letter by letter and it is best
 to go through them in an array
  
 
### Input and Output
**Input**:
- message in a text file
- which algorithm user wants to use
    - if keyed Vigenère is used, user also chooses how they want the passphrase to be incorporated in the alphabet key
- passphrase

**Output**:
- time used
- encrypted message in a text file


### Sources

http://rumkin.com/tools/cipher/vigenere.php

http://rumkin.com/tools/cipher/vigenere-keyed.php

https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher





