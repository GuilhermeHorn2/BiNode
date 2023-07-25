package misc;


public class B_search_tree {

	public static class node{
		
		public node node1;//left
		public node node2;//right
		public int data;
		
		node(node node1,node node2,int data){
			this.node1 = node1;
			this.node2 = node2;
			this.data = data;
		}
		
		node(){};
		
		public boolean equals(node a) {
			
			if(this.data == a.data) {
				return true;
			}
			return false;
			
		}
		
		public String toString(){
			return ""+data;
		}
		
	}
	
	public node root = null;
	
	//you can now if the data structure is a tree or a list if this is not null
	public node head = null;
	
	
	
	B_search_tree(int data){
		root = new node(null,null,data);
	}
	
	private node assign_next(int n,node cmp){
		if(n >= cmp.data) {
			return cmp.node2;
		}
		else {
			return cmp.node1;
		}
	}
	
	public void add(int n){
		
		node next = root;

		while(assign_next(n,next) != null){
			
			next = assign_next(n,next);
			
		}
		if(n >= next.data) {
			next.node2 = new node(null,null,n);
		}
		else {
			next.node1 = new node(null,null,n);
		}
		
	}
	
	public void inorder_print(node n,int val,node[] cmp_ptr){
		
		if(n != null){
			inorder_print(n.node1,val,cmp_ptr);
			//System.out.println(n);
			if(n.data == val){
				cmp_ptr[0] = n;
			}
			inorder_print(n.node2,val,cmp_ptr);
		}
	
	}
	
	private int max_value(){
		
		node next = root;
		while(next.node2 != null){
			next = next.node2;
		}
		return next.data;
		
	}
	
	private node min_value() {
		
		node next = root;
		while(next.node1 != null){
			next = next.node1;
		}
		return next;
		
	}
	
	public node inorder_sucessor(node n){
		
		/*The inorder_sucessor is the node with the biggest level(depth) and the min value that is still bigger 
		 * than n
		 */
		
		//1)case there is a direct sucessor in the right
		
		if(n.node2 != null && n.data < root.data){
			return n.node2;
		}
		
		//2)on the right part of the tree,and have to get the direct parent as sucessor
		if(n.data >= root.data && n.node2 != null) {
			
			//go to the right and then go the leftest possible	
			node next = n.node2;

			while(next.node1 != null){
				next = next.node1;//going left
			}
			return next;
				
		}
			
		
		
		//3)i have to find a path to this node the get this min value with the biggest depth
		
		int min = this.max_value()+1;
		node suc = null;
		node next = root;
		while(!next.equals(n)){
			
			//System.out.println(next);
			
			if(next.data > n.data && next.data < min){
				min = next.data; 
				suc = next;
			}
			
			next = assign_next(n.data,next);
			
		}
		return suc;
	}
	
	public static node inorder_sucessor(node n,node[] prev_ptr){
		
		/*When i change from a tree to a list in place,i will create distortions on the way that each node points
		 * to each other,so this overload is to handle a specific case of inorder_sucessor,this function assumes that
		 * the case 2 of the original method,the prev_ptr will point to the sucessor of the node returned from this
		 * method.
		 */
		
		node next = n.node2;

		while(next.node1 != null){
			prev_ptr[0] = next;
			next = next.node1;//going left
		}
		return next;
		
		
		
		
	}
	
	public void to_LinkedList(){
		
		if(this.head != null) {
			return;
		}
		
		node strt = this.min_value();
		
		node curr = strt;
		node next = this.inorder_sucessor(curr);
		
		this.head = curr;
		curr.node1 = null;//prev
		curr.node2 = next;//next
		
		System.out.println(curr);
		node[] prev_ptr = {null}; 
		while(next != null){
			
	
			
			if(next.data >= root.data && next.node2 != null){
				next.node2 = this.inorder_sucessor(next,prev_ptr);
			}
			
			next.node1 = curr;
			
			
			next.node2 = this.inorder_sucessor(next);
			if(next.node2 == null && prev_ptr[0] != null){
				next.node2 = prev_ptr[0];
				prev_ptr[0] = null;
			}
			
			curr = next;
			next = next.node2;
			System.out.println(curr);
		}
		
		
	}
	
}
