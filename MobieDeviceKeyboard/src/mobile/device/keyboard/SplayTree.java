package mobile.device.keyboard;

import java.util.ArrayList;

/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **
 * The SplayTree class is the main data structure used to store Candidate      *
 * objects. The Splay Tree is a binary search tree with the added rule where   *
 * every time an element is accessed, it is moved to the root of the tree. In  *
 * the case of this program, elements are only moved to the root when they     *
 * are inserted. This allows recently-inserted elements, along with elements   *
 * inserted most-often, to trend towards the root of the tree. If a String     *
 * to insert is new, then a new Node is created and that Node is moved to the  *
 * root. If a String to insert is old, then the confidence of that string      *
 * is updated by 1, and the Node containing that string is moved to the top    *
 * of the list.  															   *
 *  																		   *
 * @author Paul Woloszyn    												   *
 ** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class SplayTree {
	
	/**
	 * The root of the tree, which is either Null or a Node. 
	 */
	Node root = null; 
	
	/**
	 * The number of unique words in the tree.
	 */
	private int size = 0; 
	
	/**
	 * Comparator used to orient the Candidates in the tree alphabetically. 
	 */
	WordComparator comparator = new WordComparator(); 
	
	
	/* ********************************************************************** */
	/*             														      */
	/*				          BEGIN PRIVATE NODE CLASS 			   	   	      */
	/*  																	  */  
	/* ********************************************************************** */
	
	/**
	 * Private class used to store each Candidate in the tree. A Node has 
	 * a candidate key, and a right and left sub-tree. 
	 */
	class Node {
		
		/**
		 * The candidate key within the Node. 
		 */
		Candidate c; 
		
		/**
		 * The left sub-tree of the current Node.
		 */
		Node left; 
		
		/**
		 * The right sub-tree of the current Node. 
		 */
		Node right;
		
		/**
		 * Creates a new Node based on an input string, which should represent
		 * a word candidate to be stored in the tree. 
		 * @param word
		 */
		Node(String word)
		{
			Candidate newC = new Candidate(word); 
			this.c = newC; 
			this.left = null; 
			this.right = null; 
		}
		
		/**
		 * Builds a String rooted at the given node using an InOrder Traversal.
		 * @return String representation of InOrder Traversal.
		 */
		public String toString() 
		{
			
			StringBuilder sb = new StringBuilder(""); 
			
			if (root == null) {
				return "The Tree is Empty"; 
			} else {
				
				// recursively builds left subtree
				if (this.left != null) {
					String s = (this.left).toString(); 
					sb.append(s + ", "); 
				}
				
				// adds current node to String
				sb.append(this.c.toString());
				
				// recursively builds right subtree
				if (this.right != null) {
					String s = (this.right).toString(); 
					sb.append(", " + s); 
				}
				return sb.toString(); 
			}
		}
		
		/**
		 * Builds a String rooted at the given node using a Prerder Traversal.
		 * @return String representation of PreOrder Traversal. 
		 */
		public String toStringPreOrder() 
		{
			
			StringBuilder sb = new StringBuilder(""); 
			
			if (root == null) {
				return "The Tree is Empty"; 
			} else {
				
				// adds current node to String
				sb.append(this.c.toString());
				
				// recursively builds left subtree.
				if (this.left != null) {
					String s = (this.left).toStringPreOrder(); 
					sb.append(", " + s); 
				}
				
				// recursively builds right subtree.
				if (this.right != null) {
					String s = (this.right).toStringPreOrder(); 
					sb.append(", " + s); 
				}
				return sb.toString(); 
			}
		}
		
		/**
		 * Builds a String rooted at the given node using a PostOrder Traversal.
		 * @return String representation of PostOrder Traversal. 
		 */
		public String toStringPostOrder() 
		{
			
			StringBuilder sb = new StringBuilder(""); 
			
			if (root == null) {
				return "The Tree is Empty"; 
			} else {
				
				// recursively builds left subtree.
				if (this.left != null) {
					String s = (this.left).toStringPostOrder(); 
					sb.append(s + ", "); 
				}
				
				// recursively builds right subtree. 
				if (this.right != null) {
					String s = (this.right).toStringPostOrder(); 
					sb.append(s + ", "); 
				}
				
				// adds current node to String 
				sb.append(this.c.toString());
				return sb.toString(); 
			}
		}

		/**
		 * Method to obtain list of all words that begin with the String 
		 * specified by the fragment parameter. Uses a binary search tree
		 * range method to find all strings in the tree which begin with the 
		 * fragment parameter. 
		 * 
		 * @param fragment of string to test. 
		 * @return List of all words in the dictionary that begin with 
		 * the prefix specified by fragment. 
		 */
		public ArrayList<Candidate> getWords(String fragment)
		{
									
			ArrayList<Candidate> result = new ArrayList<Candidate>(); 
			ArrayList<Candidate> leftList = new ArrayList<Candidate>(); 
			ArrayList<Candidate> rightList = new ArrayList<Candidate>(); 
			
			// Checks and recursively calls method on the left subtree if 
			// the left subtree possibly contains any string in range. 
			// Uses the fragment itself as a string to test against the root.
			if (this.left != null && this.c.getWord().compareTo(fragment) > 0) {
				leftList = (this.left).getWords(fragment); 
			}
			
			// creates an upper-limit prefix to test against the root. 
			Character max = Character.MAX_VALUE; 
			String maxLimit = fragment.concat(max.toString()); 
				
			// Checks and recursively calls method on the right subtree if
			// the right subtree possibly contains any string in range. 
			// Uses maxLimit to test against the root. 
			if (this.right != null && 
					(this.c.getWord()).compareTo(maxLimit) < 0) {
				rightList = (this.right).getWords(fragment); 
			}
			
			// Creates complete result list. 
			result.addAll(leftList);
			if (this.c.getWord().startsWith(fragment)) {
				result.add(this.c); 				
			}
			result.addAll(rightList); 

			return result; 
		}
	}
	
	/* ********************************************************************** */
	/*             														      */
	/*				          END PRIVATE NODE CLASS 			   	   	      */
	/*  																	  */  
	/* ********************************************************************** */
	
	
	/**
	 * Public method to insert a String into the tree. If the word
	 * does not exist in the tree, a new Node is formed containing a Candidate 
	 * with object with confidence of 1. It inserts the Candidate into the tree
	 * according to Binary Search Tree property, which means every Candidate to 
	 * the left of a given Node is lexicographically less than itself, and every 
	 * Candidate to the right of a given Node is lexicographically greater than 
	 * itself. After insertion, the tree is updated so that the root is the 
	 * element which was inserted. If a Candidate for the specified word already 
	 * exists in the tree, then the Candidate increases its Confidence by 1, and
	 * the Node containing that confidence is moved to the root of the tree.

	 * @param word String to insert into the Tree.
	 */
	public void put(String word) 
	{
		Node toInsert = new Node(word); 
		root = insert(root, toInsert); 
	}
	
	/**
	 * Helper method for the put method which uses recursion to successfully
	 * insert the new Node and update the tree, or update the identical Node 
	 * and splaying it to the top of the tree. 
	 * 
	 * @param treeRoot the root of the Tree where insertion is taking place.
	 * @param toInsert the Node to insert into the tree. 
	 * @return the root of the new updated tree. 
	 */
	private Node insert(Node treeRoot, Node toInsert) 
	{
		
		// One base case. If the root is null, simply update the root and return. 
		if (treeRoot == null) {
			size++; 
			return toInsert; 
		}

		// Compares the Root Node of the tree to the Node to Insert. 
		int cmp1 = comparator.compare(treeRoot.c, toInsert.c); 
		
		// increments confidence if the two Nodes are identical.
		// No need to update tree.
		if (cmp1 == 0) {
			treeRoot.c.incrementConfidence(); 
			return treeRoot; 
		}
		
		// means new Node belongs in the right-subtree of the current Node. 
		if (cmp1 < 0) {

			// Inserts new Node into the tree, then rotates left so the new
			// Node is the root of the tree. 
			if (treeRoot.right == null) {
				toInsert.left = treeRoot; 
				size++; 
				return toInsert; 
			}
			
			// compares the new Node with the root of the right subtree.
			int cmp2 = comparator.compare(treeRoot.right.c, toInsert.c); 

			// means right subtree is identical to new node. 
			if (cmp2 == 0) {
				
				// increments confidence then readjusts tree. 
				treeRoot.right.c.incrementConfidence(); 
				return rotateLeft(treeRoot, treeRoot.right);
				
			// means new Node belongs in the left subtree of the left subtree. 
			} else if (cmp2 < 0) {
				
				// Inserts recursively and updates using zigzigRight rotation. 
				treeRoot.right.right = insert(treeRoot.right.right, toInsert); 
				return zigzigRight(treeRoot, treeRoot.right, treeRoot.right.right); 
				
			// means new Node belongs in the right subtree of the left subtree. 
			} else if (cmp2 > 0) {
				
				// Inserts recursively and updates using zigzagRight rotation. 
				treeRoot.right.left = insert(treeRoot.right.left, toInsert); 
				return zigzagRight(treeRoot, treeRoot.right, treeRoot.right.left); 
				
			} else {
				System.out.println("gah");
				return null; 
			}
			
		// means the new Node belongs in the left sub-tree of the current Node. 
		// algorithm is symmetric to above so comments are omitted. 
		} else if (cmp1 > 0) {

			
			if (treeRoot.left == null) {
				toInsert.right = treeRoot; 
				size++; 
				return toInsert; 
			}
			
			int cmp2 = comparator.compare(treeRoot.left.c, toInsert.c); 
			
			if (cmp2 == 0) {
				
				treeRoot.left.c.incrementConfidence(); // increm
				return rotateRight(treeRoot, treeRoot.left); 
				
			} else if (cmp2 < 0) {

				treeRoot.left.right = insert(treeRoot.left.right, toInsert); 
				return zigzagLeft(treeRoot, treeRoot.left, treeRoot.left.right); 
				
			} else if (cmp2 > 0) {
				
				treeRoot.left.left = insert(treeRoot.left.left, toInsert); 
				return zigzigLeft(treeRoot, treeRoot.left, treeRoot.left.left); 
				
			} else {
				System.out.println("gah");
				return null; 
			}
			
		} else {
			return null; 
		}
	}

	/**
	 * Helper method to rotate the tree rooted at Node 'treeRoot' with
	 * its right-child at Node 'child.' After rotation, the new tree
	 * still maintains the BST property.
	 * @param treeRoot - the original root-node of the tree.
	 * @param child - the right child of the tree rooted at 'treeRoot'
	 * @return - the root of the updated tree. 
	 */
	private Node rotateRight(Node treeRoot, Node child) 
	{
		treeRoot.left = child.right; 
		child.right = treeRoot; 
		return child; 
	}

	/**
	 * Helper method to rotate the tree rooted at Node 'treeRoot' with 
	 * its left-child at Node 'child.' After rotation, the new tree 
	 * still maintains the BST property. 
	 * 
	 * @param treeRoot - the original root-node of the tree.
	 * @param child - the left child of the tree rooted at 'treeRoot'
	 * @return - the root of the updated tree.
	 */
	private Node rotateLeft(Node treeRoot, Node child) 
	{
		treeRoot.right = child.left; 
		child.left = treeRoot; 
		return child; 
	}

	/**
	 * Helper method to rotate the tree rooted at Node 'treeRoot' 
	 * with its left subtree's left subtree. After rotation, the 
	 * new tree still maintains the BST property. 
	 * 
	 * @param treeRoot - the original root-node of the tree
	 * @param child - the left child of the tree rooted at 'treeRoot'
	 * @param grandChild - the left child of the tree rooted at 'child'
	 * @return - the root of the updated tree. 
	 */
	private Node zigzigLeft(Node treeRoot, Node child, Node grandChild)
	{
		treeRoot.left = child.right; 
		child.left = grandChild.right; 
		grandChild.right = child; 
		child.right = treeRoot; 
		return grandChild; 
	}
	
	/**
	 * Helper method to rotate the tree rooted at Node 'treeRoot' 
	 * with its right subtree's right subtree. After rotation, the 
	 * new tree still maintains the BST property. 
	 * 
	 * @param treeRoot - the original root-node of the tree
	 * @param child - the right child of the tree rooted at 'treeRoot'
	 * @param grandChild - the right child of the tree rooted at 'child'
	 * @return - the root of the updated tree. 
	 */
	private Node zigzigRight(Node treeRoot, Node child, Node grandChild) 
	{
		treeRoot.right = child.left; 
		child.right = grandChild.left; 
		grandChild.left = child; 
		child.left = treeRoot; 
		return grandChild; 
	}
	
	/**
	 * Helper method to rotate the tree rooted at Node 'treeRoot' 
	 * with its left subtree's right subtree. After rotation, the 
	 * new tree still maintains the BST property. 
	 * 
	 * @param treeRoot - the original root-node of the tree
	 * @param child - the left child of the tree rooted at 'treeRoot'
	 * @param grandChild - the right child of the tree rooted at 'child'
	 * @return - the root of the updated tree. 
	 */
	private Node zigzagLeft(Node treeRoot, Node child, Node grandChild) 
	{
		child.right = grandChild.left; 
		treeRoot.left = grandChild.right; 
		grandChild.left = child; 
		grandChild.right = treeRoot; 
		return grandChild; 
	}
	
	/**
	 * Helper method to rotate the tree rooted at Node 'treeRoot' 
	 * with its right subtree's left subtree. After rotation, the 
	 * new tree still maintains the BST property. 
	 * 
	 * @param treeRoot - the original root-node of the tree
	 * @param child - the right child of the tree rooted at 'treeRoot'
	 * @param grandChild - the left child of the tree rooted at 'child'
	 * @return - the root of the updated tree. 
	 */
	private Node zigzagRight(Node treeRoot, Node child, Node grandChild) 
	{
		child.left = grandChild.right; 
		treeRoot.right = grandChild.left; 
		grandChild.left = treeRoot; 
		grandChild.right = child; 
		return grandChild; 
	}

	/**
	 * Returns a String representation of the given Tree. Uses a method in 
	 * the Node class to accomplish the function recursively. 
	 * 
	 * @return - String representation of the tree
	 */
	public String toString() 
	{
		return root.toString();
	}
	
	/**
	 * Returns the number of unique Candidates in the tree regardless of 
	 * their confidence. 
	 * 
	 * @return - number of Nodes in the tree. 
	 */
	public int getSize() 
	{
		return size; 
	}
	
	/**
	 * Returns a string representation of the root of the current tree. 
	 * 
	 * @return - String representing root of tree. 
	 */
	public String getRoot() 
	{
		if (root != null) {
			return root.c.toString(); 
		} else {
			return null; 
		}
	}

	/**
	 * Returns a list of words in the tree that begin with the specified 
	 * fragment parameter. See getWords method in the private Node class 
	 * of this file for more details. 
	 * 
	 * @param fragment - the prefix fragment to test against Candidates.
	 * @return - a list of the words in the tree that begin with the parameter.
	 */
	public ArrayList<Candidate> getWords(String fragment) 
	{
		if (root == null)  {
			return null; 
		}
		ArrayList<Candidate> result = root.getWords(fragment);
		return result; 
	}
	
}
