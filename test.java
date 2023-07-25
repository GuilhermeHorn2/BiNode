package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import misc.B_search_tree.node;

public class main_misc {
	
	
	public static final int MAX = 1_000_000;
	
	
	
	public static void main(String[] args) {
		
		
	
		B_search_tree tree = new B_search_tree(8);
		tree.add(4);
		tree.add(2);
		tree.add(6);
		tree.add(5);
		tree.add(10);
		tree.add(9);
		
		//just checking if the structure of the tree makes sense
		node[] cmp_ptr = {null};
		cmp_ptr[0] = new node();
		tree.inorder_print(tree.root,9,cmp_ptr);
		//System.out.println(cmp_ptr[0]);

		//
		//System.out.println(tree.inorder_sucessor(cmp_ptr[0]));
		
		tree.to_LinkedList();
		
		
	}
	
	
		
}
