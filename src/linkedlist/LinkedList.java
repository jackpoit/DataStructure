package linkedlist;

public class LinkedList<E> {

	private class Node{
		public E e;
		public Node next;
		public Node(){
			this.next=null;
			this.e=null;
		}
		public Node(E e,Node next){
			this.e=e;
			this.next=next;
		}
		public Node(E e){
			this.e=e;
			this.next=null;
		}

		@Override
		public String toString() {
			return e.toString();
		}
	}
	private Node dummyHead;
	private int size;

	public LinkedList(){
		dummyHead=new Node(null,null);
		size=0;
	}

	public int getSize() {
		return size;
	}

	public void add(E e,int index){
		if (index<0||index>size){
			throw new IllegalArgumentException("Add Failed.Illegal index");
		}
		Node prve =dummyHead;//插入的前一个节点，所以是index-1;
		// 设置虚拟头结点 初始变为-1 但是不用特殊化原来的head节点了
		for (int i=-1;i<index-1;i++) {
			prve=prve.next;
		}
		prve.next=new Node(e,prve.next);
		size++;
	}

	public void addFirst(E e){
		add(e,0);
	}
	public void addLast(E e){
		add(e,size);
	}
	public E get(int index){
		if (index<0||index>size-1){
			throw new IllegalArgumentException("Get Failed.Illegal index");
		}
		Node cur=dummyHead.next;
		for (int i=0;i<index;i++) {
			cur=cur.next;
		}
		return cur.e;
	}
	public E getFirst(){
		return get(0);
	}
	public E getLast(){
		return get(size-1);
	}

	public void set(int index,E e){
		if (index<0||index>size-1){
			throw new IllegalArgumentException("Get Failed.Illegal index");
		}
		Node cur=dummyHead.next;
		for (int i=0;i<index;i++){
			cur=cur.next;
		}
		cur.e=e;
	}
	public boolean contain(E e){
		Node cur=dummyHead.next;
		while (cur!=null){
			if (cur.e.equals(e)){
				return true;
			}
			cur=cur.next;
		}
		return false;
	}
	@Override
	public String toString(){
		StringBuilder res=new StringBuilder();
		for (Node cur=dummyHead.next;cur!=null;cur=cur.next){
			res.append(cur+"->");
		}
		res.append("NULL");
		return res.toString();
	}

}
