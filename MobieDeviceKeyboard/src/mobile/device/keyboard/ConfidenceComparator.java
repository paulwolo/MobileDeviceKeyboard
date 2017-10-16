package mobile.device.keyboard;

import java.util.Comparator;

/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **
 * Comparator class used to compare two Candidate objects. In this comparator, *
 * object c1 is 'less than' object c2 if the confidence contained in c1 is     *
 * greater than that of c2. If the confidences are equal, it compares the      *
 * Candidates based on the lexicographical order of their stored words. This   *
 * order was chosen so when a sorted list of Candidates was presented using    *
 * this comparator, the list would be sorted so Candidates with the highest    *
 * confidence were at the beginning of the list.   				               *
 * 																		       *
 * @author Paul Woloszyn													   *
 ** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class ConfidenceComparator implements Comparator<Candidate> {

	@Override
	public int compare(Candidate o1, Candidate o2)
	{
		int cmp = o2.getConfidence() - o1.getConfidence(); 
		if (cmp == 0) {
			return o1.getWord().compareTo(o2.getWord()); 
		} else {
			return cmp; 
		}
	}

}
