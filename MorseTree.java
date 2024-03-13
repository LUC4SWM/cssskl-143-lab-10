import com.sun.source.tree.Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * MorseTree.java
 * CSSSKL 143 Binary Search Tree Lab
 * Author: Rob Nash
 *
 * This class reads in data from a text file ("data.txt") and populates a binary tree with an
 * ordering constraint.  See the lab instructions for more information, but in general, dots go right
 * and dashes go left when constructing or traversing a Morse code tree.  Search for TODO
 * in the code to see what code you have to implement.
 *
 * Start with the constructor. In your constructor read each line in from the text file first,
 * calling add() for each {letter, morseCodeStr} pair.
 */

public class MorseTree {
    // Inner class to create the linked structure
    private class TreeNode {
        Character data; // holds a given node’s data
        TreeNode right;
        TreeNode left;

        public TreeNode() {
            this.data = null;
            this.right = null;
            this.left = null;
        }
        public TreeNode(Character data) {
            this.data = data;
            this.right = null;
            this.left = null;
        }

        public void setRight(TreeNode rightNode) {
            this.right = rightNode;
        }

        public void setLeft(TreeNode leftNode) {
            this.left = leftNode;
        }
    }

    // instance variables

    //TODO: Data member called "root" goes here
    private TreeNode root;
    //TODO: Complete the constructor
    public MorseTree() {
        root = null;
        Scanner fin;
        try {
            fin = new Scanner(new File("data.txt"));

            // for each line in the file,
            while (fin.hasNextLine()) {
                // get the letter(char) and the Morse string
                String line = fin.nextLine();
                String[] parts = line.split(" ");
                char lttr = parts[0].charAt(0);
                String mStr = parts[1];

                // call add() with this data
                add(mStr, lttr);

                // print out the letter and Morse string here for debugging
                System.out.println("Adding: " + lttr + " = " + mStr);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void add(String morseStr, char letter) {
        root = insertInSubtree(morseStr, letter, root);
    }

    // TODO: Recursively complete this function. It's only a few characters different from findInSubtree()
	private TreeNode insertInSubtree(String morseStr, char letter, TreeNode subtree) {
		// base case 1 : subtree is null
        if (subtree == null){
            return new TreeNode(letter);
        }
		// base case 2 : morseStr is of length 0
        if (morseStr.isEmpty()){
            subtree.data = letter;
            return subtree;
        }
        char morse = morseStr.charAt(0);
		// recursive case 1: the first char in morseStr is a '.', so recursively traverse tree
        if (morse == '.'){
            subtree.setRight(insertInSubtree(morseStr.substring(1), letter, subtree.right));
        }
		// recursive case 2: the first char in the morseStr is a '-', so recurse accordingly
		if (morse == '-'){
            subtree.setLeft(insertInSubtree(morseStr.substring(1), letter, subtree.left));
        }
		return subtree; // always the last line, always return the node you are working on
	}

    public Character translate(String morseStr) {
        return findInSubtree(morseStr, root);
    }


	private Character findInSubtree(String morseStr, TreeNode subtree) {
		// base case 1 : subtree is null
        if (subtree == null){
            return null;
        }
		// base case 2 : morseStr is of length 0
        else if (morseStr.isEmpty() && subtree.data != null){
            return subtree.data;
        }
        char morse = morseStr.charAt(0);
		// recursive case 1: the first char in morseStr is a '.', so recursively traverse tree
        if (morse == '.'){
            return findInSubtree(morseStr.substring(1), subtree.right);
        }
		// recursive case 2: the first char in the morseStr is a '-', so re-curse accordingly
        else if (morse == '-') {
            return findInSubtree(morseStr.substring(1), subtree.left);
        }
        return null;
	}


    // Non-recursive function that calls other (recursive) functions
	public String translateString(String tokens) {
		String retVal = "";
		// build a scanner here using tokens as input
		// iterate over the tokens calling translate on each token (substring separated by a space)
		// concat these characters and return them

		return retVal;
	}

    public String toMorseCode(Character c) {
        // walk the tree looking for the TreeNode with the char c in it
            // preorder walk?
            // inorder walk?
            // postorder walk?

        // when you've found the char c, report the path from the root to the node
        // and build the morse code by adding a "." when you go right, "-" when you go left
        return new String("You wish.");
    }

    public String toString() {
        return inorderWalk();
    }
    private String inorderWalk() {
        return new String("Another wish.");
    }

    public static void main(String[] args) {
        MorseTree mt = new MorseTree();  // builds our tree using data from a file

        System.out.println(mt.translate("..."));  // prints out S
        System.out.println(mt.translate("---"));  // prints out O
        System.out.println(mt.translate(".......-"));  // prints out null

//        System.out.println(mt.translateString("... --- ..."));  // SOS
//        System.out.println(mt.toMorseCode('S'));  // find where we are in the tree, remember path to root
    }
}
