package linkedlist;

public class LinkedList<E> {

	private class Node{
		public E e;
		public Node next;
		public Node(){
			this.next=null;
			this.e=null;
		}
		public Node(E e,Node node){
			this.e=e;
			this.next=node;
		}
		public Node(E e){
			this.e=e;
			this.next=null;
		}
	}
	private Node head;
	private int size;

	public LinkedList(){

	}
	public LinkedList(E e,Node node){

	}

}
