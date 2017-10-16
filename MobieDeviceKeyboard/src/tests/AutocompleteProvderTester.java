package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import mobile.device.keyboard.AutocompleteProvider;
import mobile.device.keyboard.Candidate;

public class AutocompleteProvderTester {

	@Test
	public void testSimple() {
		AutocompleteProvider provider = new AutocompleteProvider(); 
		provider.train("this is the first test.");
		ArrayList<Candidate> list = provider.getWords("t"); 
		
		Candidate t1 = new Candidate("this"); 
		Candidate t2 = new Candidate("the"); 
		Candidate t3 = new Candidate("test"); 
		
		System.out.println(list.get(1)); 
		System.out.println(t2);
		System.out.println(t2.equals(list.get(1)));
		System.out.println(list.contains(t2));
		assertTrue(list.contains(t1)); 
		assertTrue(list.contains(t2)); 
		assertTrue(list.contains(t3)); 
	}
	
	@Test
	public void testCaps() {
		AutocompleteProvider provider = new AutocompleteProvider(); 
		provider.train("THE the tHe ThE the.");
		ArrayList<Candidate> list = provider.getWords("th"); 

		assertTrue(list.get(0).getConfidence() == 5); 
		assertTrue(list.get(0).getWord().equals("the")); 
	}

}
