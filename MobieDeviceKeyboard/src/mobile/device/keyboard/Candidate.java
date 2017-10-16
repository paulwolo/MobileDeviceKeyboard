package mobile.device.keyboard;

/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **
 * This class defines Candidate objects, which are objects that contain both a *
 * word and a 'confidence.' 'Confidence' is defined as the likelihood/relevance*
 * of an individual Candidate relative to the other Candidates. This is        *
 * represented as an Integer which tracks the frequency of the word's use      *
 * throughout the program's life cycle.     			  	   	        	   *
 * 																			   *
 * @author paulwolo															   *
 ** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class Candidate  {

	/**
	 * String representation of the stored word. 
	 */
	private String word; 
	
	/**
	 * Integer representation tracking the frequency of the word's use. 
	 */
	private Integer confidence; 
	
	/**
	 * Constructor to create new Candidate object. 
	 * 
	 * @param word - String representation of word that represents this new 
	 * Candidate object. 
	 */
	public Candidate(String word) 
	{
		this.word = word; 
		confidence = 1; 
	}
	
	/**
	 * Returns a String representation of the word stored in this Candidate
	 * object
	 * @return - A String representing word in this Candidate. 
	 */
	public String getWord() 
	{
		return word; 
	}
	
	/**
	 * Returns an Integer representation of the number of times that the word
	 * stored in this Candidate object was used throughout the program's
	 * life-cycle. 
	 * 
	 * @return - An Integer representing frequency of this Candidate.
	 */
	public Integer getConfidence() 
	{
		return confidence; 
	}
	
	/**
	 * Increments the confidence of this Candidate by 1. 
	 */
	void incrementConfidence() 
	{
		confidence++; 
	}
	
	/**
	 * Gives a String representation of this Candidate, including the word it 
	 * represents and the frequency it was used. 
	 * 
	 * @return - a String representation of this Candidate.
	 */
	public String toString() 
	{
		return word + " (" + confidence.toString() + ")"; 
	}
	
	/**
	 * Checks to see if two candidates are equal to one another. One candidate
	 * is equal to the other if both the word and the frequency are identical. 
	 * This method is primarily used for testing. 
	 * @param c
	 * @return
	 */
	@Override
	public boolean equals(Object c) {
		return (
				c != null &&
				this.getClass().equals(c.getClass()) &&
				this.getWord().equals( ((Candidate) c).getWord()) && 
				this.getConfidence() == ((Candidate) c).getConfidence()); 
	}
}
