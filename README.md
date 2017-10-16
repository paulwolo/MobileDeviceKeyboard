# MobileDeviceKeyboard
Uses input to learn word frequencies, and suggests autocomplete words based on those frequencies.
Hello, 

I provided a class, "MobileDeviceKeyboard.java" to build and run my solution. This 
class has a main method which prompts you with further instructions. When you run the 
program, instructions should populate saying... 

"This program implements an autocomplete word suggester. It reads in 
all previously typed words, and keeps track of their frequency. When a new word
is partially-complete, it suggests a possible word completion. To begin, simply 
type into the console. When you feel that you need a word suggestion, press enter.

For example, you could type "this this that this that th", and then press enter.
The console will prompt you with suggestions to finish the fragment "th" based
on previous input, which includes words "this", "this", "that", "this", and "that"

Your final word before pressing enter will interpreted as a word fragment. If you are
finished with a word and want it stored in the database, but you do not need an
autocomplete suggestion, please add a space at the end of the line prior to 
pressing enter.

When you are done, please type EXIT PROGRAM."

This program uses System.in as the input stream and System.out as output.

The program remembers words containing the characters a-z, A-Z, or 0-9. 

Examples

Input: "Everyone everywhere is everything everyone needs. "
Output: "No fragment was detected. All completed words were saved in the dictionary!"
Input: "Evernote evades everything! everyone envies Elvis. Ev"
Output: "Your top 5 suggestions are...
		 everyone (used 3 times)
		 everything (used 2 times)
		 evades (used 1 time)
		 evernote (used 1 time)
		 everywhere (used 1 time)"
Input: "this thing that I think this thing will hate is a thing that I love to think about. "
Output: "No fragment was detected. All completed words were saved in the dictionary!"
Input: "I think I need to th"
Output: "Your top 4 suggestions are...
		 thing (used 3 times)
		 think (used 3 times)
		 that (used 2 times)
		 this (used 2 times)"
Input: "e"
Output: "Your top 7 suggestions are...
	     everyone (used 3 times)
	     everything (used 2 times)
	     elvis (used 1 time)
	     envies (used 1 time)
	     evades (used 1 time)
	     evernote (used 1 time)
	     everywhere (used 1 time)"
Input: "flip"
Output: "there are no suggestions for you! sorry! Try a different word fragment."
Input: "EXIT PROGRAM"
Output: "thank you!"

If you do not wish to use the main method provided, you can 
still use the AutocompleteProvider class methods directly. 
The two methods are...

public ArrayList<Candidate> String getWords(String fragment); 
public void train(String passage); 

the former will give you an ArrayList of suggestion candidates based on the dictionary.
The latter will read a string passage, and store all words in that string into the dictionary. 
