package tests;

import mobile.device.keyboard.SplayTree;
import static org.junit.Assert.*;

import org.junit.Test;

public class StructureTester {

	
	@Test
	public void testSizeBasic() throws Exception {
		SplayTree tree = new SplayTree(); 
		assertTrue(tree.getSize() == 0); 
		tree.put("mice");
		tree.put("car");
		tree.put("house");
		assertTrue(tree.getSize() == 3); 
	}
	
	
	@Test
	public void testSize2Basic() throws Exception {
		SplayTree tree = new SplayTree(); 
		tree.put("mice");
		tree.put("mice");
		tree.put("house");
		assertTrue(tree.getSize() == 2); 
	}
	
	@Test 
	public void testConfidenceBasic() throws Exception {
		SplayTree tree = new SplayTree(); 
		tree.put("mice");
		tree.put("mice");
		tree.put("mice");
		assertEquals(tree.toString(), "mice (3)"); 
	}
	
	@Test
	public void testInsert1() throws Exception {
		SplayTree tree = new SplayTree(); 
		tree.put("a");
		tree.put("b");
		tree.put("c");
		assertEquals(tree.toString(), "a (1), b (1), c (1)"); 
	}

	@Test
	public void testInsertZigzagLeft() throws Exception {
		SplayTree tree = new SplayTree(); 
		tree.put("a");
		tree.put("z");
		tree.put("k");
		assertEquals(tree.toString(), "a (1), k (1), z (1)"); 
	}
	
	@Test
	public void testInsertZigzagReft() throws Exception {
		SplayTree tree = new SplayTree(); 
		tree.put("z");
		tree.put("a");
		tree.put("k");
		assertEquals(tree.toString(), "a (1), k (1), z (1)"); 
	}
	
	@Test
	public void testInsertZigzigRight() throws Exception {
		SplayTree tree = new SplayTree(); 
		tree.put("k");
		tree.put("a");
		tree.put("z");
		assertEquals(tree.toString(), "a (1), k (1), z (1)"); 
	}
	
	@Test
	public void testInsertZigzigLeft() throws Exception {
		SplayTree tree = new SplayTree(); 
		tree.put("k");
		tree.put("z");
		tree.put("a");
		assertEquals(tree.toString(), "a (1), k (1), z (1)"); 
	}
	
	
	@Test 
	public void testInsertRandom() throws Exception {
		SplayTree tree = new SplayTree(); 
		tree.put("c");
		assertEquals(tree.toString(), "c (1)"); 
		tree.put("n");
		assertEquals(tree.toString(), "c (1), n (1)"); 
		tree.put("f");
		assertEquals(tree.toString(), "c (1), f (1), n (1)"); 
		tree.put("d");
		assertEquals(tree.toString(), "c (1), d (1), f (1), n (1)"); 
		tree.put("k");
		assertEquals(tree.toString(), "c (1), d (1), f (1), k (1), n (1)"); 
		tree.put("e");
		assertEquals(tree.toString(), "c (1), d (1), e (1), f (1), k (1), n (1)"); 
		tree.put("a");
		assertEquals(tree.toString(), "a (1), c (1), d (1), e (1), f (1), k (1), n (1)"); 
		tree.put("j");
		assertEquals(tree.toString(), "a (1), c (1), d (1), e (1), f (1), j (1), k (1), n (1)"); 
		tree.put("h");
		assertEquals(tree.toString(), "a (1), c (1), d (1), e (1), f (1), h (1), j (1), k (1), n (1)"); 
		tree.put("b");
		assertEquals(tree.toString(), "a (1), b (1), c (1), d (1), e (1), f (1), h (1), j (1), k (1), n (1)"); 
		tree.put("i");
		assertEquals(tree.toString(), "a (1), b (1), c (1), d (1), e (1), f (1), h (1), i (1), j (1), k (1), n (1)"); 
		tree.put("l");
		assertEquals(tree.toString(), "a (1), b (1), c (1), d (1), e (1), f (1), h (1), i (1), j (1), k (1), l (1), n (1)"); 
		tree.put("m");
		assertEquals(tree.toString(), "a (1), b (1), c (1), d (1), e (1), f (1), h (1), i (1), j (1), k (1), l (1), m (1), n (1)"); 
		tree.put("g");
		assertEquals(tree.toString(), "a (1), b (1), c (1), d (1), e (1), f (1), g (1), h (1), i (1), j (1), k (1), l (1), m (1), n (1)"); 
		assertTrue(tree.getSize() == 14); 
	}
	
	
	@Test
	public void testInsertRepeats() throws Exception {
		SplayTree tree = new SplayTree(); 
		tree.put("a"); //1
		assertEquals(tree.toString(), "a (1)"); 
		tree.put("e"); //1
		assertEquals(tree.toString(), "a (1), e (1)"); 
		tree.put("e"); //2
		assertEquals(tree.toString(), "a (1), e (2)"); 
		tree.put("b"); //1
		assertEquals(tree.toString(), "a (1), b (1), e (2)"); 
		tree.put("a"); //2
		assertEquals(tree.toString(), "a (2), b (1), e (2)"); 
		tree.put("c"); //1
		System.out.println(tree.toString());
		assertEquals(tree.toString(), "a (2), b (1), c (1), e (2)"); 
		tree.put("e"); //3
		System.out.println(tree.toString());
		assertEquals(tree.toString(), "a (2), b (1), c (1), e (3)"); 
		tree.put("c"); //2
		assertEquals(tree.toString(), "a (2), b (1), c (2), e (3)"); 
		tree.put("a"); //3
		assertEquals(tree.toString(), "a (3), b (1), c (2), e (3)"); 
		tree.put("b"); //2
		assertEquals(tree.toString(), "a (3), b (2), c (2), e (3)"); 
		tree.put("c"); //3
		assertEquals(tree.toString(), "a (3), b (2), c (3), e (3)"); 
		tree.put("e"); //4
		assertEquals(tree.toString(), "a (3), b (2), c (3), e (4)"); 
		tree.put("e"); //5
		assertEquals(tree.toString(), "a (3), b (2), c (3), e (5)"); 
		tree.put("a"); //4
		assertEquals(tree.toString(), "a (4), b (2), c (3), e (5)"); 
		tree.put("a"); //5
		assertEquals(tree.toString(), "a (5), b (2), c (3), e (5)"); 
		tree.put("d"); //1
		assertEquals(tree.toString(), "a (5), b (2), c (3), d (1), e (5)"); 
		tree.put("c"); //4
		assertEquals(tree.toString(), "a (5), b (2), c (4), d (1), e (5)"); 
		assertTrue(tree.getSize() == 5); 
	}
	 
	@Test
	public void testInsertHard1() throws Exception {
		SplayTree tree = new SplayTree(); 

		tree.put("a");
		tree.put("c");
		tree.put("e");
		tree.put("g");
		tree.put("i");
		tree.put("k");
		tree.put("m");
		tree.put("o");
		tree.put("b");
		tree.put("f");
		tree.put("j");
		tree.put("n");
		tree.put("d");
		tree.put("l");
		tree.put("h");

		assertEquals(tree.toString(), "a (1), b (1), c (1), d (1), e (1), f (1), g (1), h (1), i (1), j (1), k (1), l (1), m (1), n (1), o (1)"); 
		assertEquals(tree.getRoot(), "h (1)"); 
		
		tree.put("h");
		tree.put("l");
		tree.put("d");
		tree.put("n");
		tree.put("j");
		tree.put("f");
		tree.put("b");
		tree.put("o");
		tree.put("m");
		tree.put("k");
		tree.put("i");
		tree.put("g");
		tree.put("e");
		tree.put("c");
		tree.put("a");

		assertEquals(tree.toString(), "a (2), b (2), c (2), d (2), e (2), f (2), g (2), h (2), i (2), j (2), k (2), l (2), m (2), n (2), o (2)"); 
		assertEquals(tree.getRoot(), "a (2)"); 
	}
}
