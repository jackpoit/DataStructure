package bst;

public class BinarySearchTree<E extends Comparable<E>> {
	private class Node{
		public E e;
		Node left,right;

		public Node(){
			left=null;
			right=null;
		}
		public Node(E e,Node left,Node right){
			this.e=e;
			this.left=left;
			this.right=right;
		}
	}
	private Node root;
	private int size;

	public BinarySearchTree(){
		root=null;
		size=0;
	}

	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size==0;
	}






}
