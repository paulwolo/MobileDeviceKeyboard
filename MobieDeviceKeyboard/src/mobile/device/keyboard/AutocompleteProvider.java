package mobile.device.keyboard;

import java.util.ArrayList;
import java.util.Comparator;

/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **
 * Class to store and retrieve all words that are stored throughout this       *
 * program's life-cycle. Keeps a dictionary of all words that have been read   *
 * into the program, and stores those words in a variation of the Splay Tree.  *
 *  																		   *
 * @author Paul Woloszyn													   *
 ** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class AutocompleteProvider {
	
	/**
	 * Dictionary of all words stored, and the frequency of thier use. 
	 */
	public SplayTree allCandidates; 
	
	/**
	 * Comparator to sort words from highest to lowest frequency of use. 
	 */
	Comparator<Candidate> confidenceComparator;
	
	/**
	 * creates new AutocompleteProvider, with an empty dictionary. 
	 */
	public AutocompleteProvider() 
	{
		allCandidates = new SplayTree(); 
		confidenceComparator  = new ConfidenceComparator();
	}
	
	/**
	 * Retrieves all words in the dictionary that begin with the String prefix 
	 * specified by 'fragment.' Orders the resulting list in order of highest 
	 * frequency words to lowest frequency words. Ignores capitalization. 
	 * 
	 * @param fragment - desired prefix to collect words
	 * @return - list of all Candidates containing words with the 
	 * prefix 'fragment.'
	 */
	public ArrayList<Candidate> getWords(String fragment) 
	{
		ArrayList<Candidate> result = 
				allCandidates.getWords(fragment.toLowerCase()); 
		result.sort(confidenceComparator);
		return result; 
	}
	
	/**
	 * Reads in a String, 'passage', and stores all of the words in that 
	 * string into the Candidate dictionary. Ensures that punctuation 
	 * is removed (other than apostrophes), and ignores all 
	 * capitalization. 
	 * 
	 * @param passage
	 */
	public void train(String passage) 
	{
		String[] words = passage.split("[^'\\w]+");
		for (int i = 0; i < words.length; i++) {
			allCandidates.put(words[i].toLowerCase());		
		}
	}
	
	/**
	 * Returns a string representation of the Dictionary. 
	 * @return - a String representation of the Dictionary. 
	 */
	public String toString() 
	{
		return allCandidates.toString(); 
	}
	
}
