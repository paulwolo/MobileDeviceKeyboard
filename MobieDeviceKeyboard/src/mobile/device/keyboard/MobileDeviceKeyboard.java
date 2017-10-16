package mobile.device.keyboard;

import java.util.ArrayList;
import java.util.Scanner;

/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **
 * This program gives an interactive display of the MobileDeviceKeyboard,      *
 * which implements an auto-complete algorithm. In this interaction, users     *
 * can type various sentences and words into the scanner. If they leave a      *
 * word untyped at the end of their input, the program will output a list      *
 * of suggested auto-complete words, along with how many times those           *
 * words have been used. It can handle multiple inputs and only exits          *
 * when the user prompts with the command 'EXIT PROGRAM.'    			       *
 * 																		       * 
 * Input is read with the Java Scanner class and the System.in 				   *
 * console input. 															   *
 * 																			   *
 * @author - Paul Woloszyn													   *
 ** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class MobileDeviceKeyboard {

	public static void main(String[] args) throws Exception {
		
		// opens input reader
		Scanner scanner = new Scanner(System.in);
		
		// creates AutocompleteProvider object 
		final AutocompleteProvider provider = new AutocompleteProvider(); 
		
		// Initial instructions for the user. 
		System.out.println("This program implements an autocomplete word suggester. It reads in "
			+ "\nall previously typed words, and keeps track of their frequency. When a new word"
			+ "\nis partially-complete, it suggests a possible word completion. To begin, simply "
			+ "\ntype into the console. When you feel that you need a word suggestion, press enter."
			+ "\n"
			+ "\nFor example, you could type \"this this that this that th\", and then press enter."
			+ "\nThe console will prompt you with suggestions to finish the fragment \"th\" based"
			+ "\non previous input."
			+ "\n"
			+ "\nYour final word before pressing enter will be assumed a fragment. If you are"
			+ "\nfinished with a word and want it stored in the database, but you do not need an"
			+ "\nautocomplete suggestion, please add a space at the end of the line prior to "
			+ "\npressing enter."
			+ "\n"
			+ "\nWhen you are done, please type EXIT PROGRAM."
			); 
		
		// flag to exit input only when the user types EXIT PROGRAM.
		boolean flag = true; 
		while (flag) {
			
			// s represents the most recent line of input. 
			String s = scanner.nextLine(); 
			
			// exits program with user's prompt.
			if (s.equals("EXIT PROGRAM")) {
				flag = false; 
				
			} else {
				
				
				int finalWordIndex = s.lastIndexOf(" "); 
				String fragment;
				
				// means only a fragment was typed into input, no need to train
				if (finalWordIndex == -1) {
					fragment = s;
				
				// isolates the fragment and trains the preceding string. 
				} else {
					fragment = s.substring(finalWordIndex+1, s.length()); 
					provider.train(s.substring(0, finalWordIndex));
				}
				
				// run if the fragment was simply excess white space.
				if (fragment.matches("\\s*")) {
					System.out.println("No fragment was detected. All completed words were saved in the dictionary!");
				
				
				} else {
				
					ArrayList<Candidate> suggestions = provider.getWords(fragment); 
					
					// builds string of upt-to 10 autocomplete suggestions, listed 
					// in order of most-likely to least-likely. 
					StringBuilder sb = new StringBuilder(); 
					StringBuilder sb2 = new StringBuilder(); 
					Integer count = 0; 
					for (int i = 0; (i < 10 && i < suggestions.size()); i++) {
						Candidate suggestion = suggestions.get(i);
						int confidence = suggestion.getConfidence(); 
						if (confidence != 1) {
							sb2.append(suggestion.getWord() + " (used " + confidence + " times)\n"); 
						} else {
							sb2.append(suggestion.getWord() + " (used " + confidence + " time)\n"); 
						}
						count++; 
					}
					
					// if there were no suggestions
					if (count == 0) {
						System.out.println("there are no suggestions for you! sorry! Try a different word fragment.");
					
					// if there is just 1 suggestion
					} else if (count == 1 ) {
						sb.append("Your top suggestion is...\n"); 
						sb.append(sb2); 
						System.out.println(sb); 
					
					// if there are multiple suggestions
					} else {
						sb.append("Your top ");
						sb.append(count.toString()); 
						sb.append(" suggestions are...\n"); 
						sb.append(sb2); 
						System.out.println(sb);
					}
					
				}
			}
		}
		
		System.out.println("thank you!");
		scanner.close(); 
	}

}
