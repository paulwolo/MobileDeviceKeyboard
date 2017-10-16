package mobile.device.keyboard;


import java.util.Comparator;

/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **
 * Comparator class used to compare two Candidate objects. In this comparator, *
 * object c1 is 'less than' object c2 if the word contained in c1 is           *
 * lexicographically less than that of c2. Uses the String.compareTo() method. *
 *   																		   *
 * @author Paul Woloszyn													   *
 ** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class WordComparator implements Comparator<Candidate> {

	@Override
	public int compare(Candidate c1, Candidate c2)
	{
		return c1.getWord().compareTo(c2.getWord()); 
	}
}
