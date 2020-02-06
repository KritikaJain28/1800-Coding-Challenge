# 1800-Coding-Challenge

This coding challenge is to write a program that will show a user possible matches for a list
of provided phone numbers.
Your program should be a command line application that reads from files specified as
command-line arguments or STDIN when no files are given. Each line of these files will
contain a single phone number.
For each phone number read, your program should output all possible word replacements
from a dictionary. Your program should try to replace every digit of the provided phone
number with a letter from a dictionary word; however, if no match can be made, a single
digit can be left as is at that point. No two consecutive digits can remain unchanged and
the program should skip over a number (producing no output) if a match cannot be made.
Your program should allow the user to set a dictionary with the -d command-line option,
but it's fine to use a reasonable default for your system. The dictionary is expected to have
one word per line.
All punctuation and whitespace should be ignored in both phone numbers and the
dictionary file. The program should not be case sensitive, letting "a" == "A". Output should
be capital letters and digits separated at word boundaries with a single dash (-), one
possible word encoding per line.

Please find the below details that i have incorporated as the solution to the stated problem :

1. Phone number can be loaded from command line arguments as well as from the standard input. Complete valid file path should be mentioned in arguments for the application to load the file containing phone numbers. 
2. Application will switch to standard input only when no argument given or more than one argument has been specified.
3. Using a default dictionary for the system. It is being loaded from resources/dictionary.prop file.
4. The system removes all the punctuation and white spaces in a phone number that is being loaded.
5. Output is in Capital letters and digits separated by word boundaries i.e '.' is being replaced by '-'.
6. Incorporated the Unit test cases for the requirements mentioned in the problem statement as well.
