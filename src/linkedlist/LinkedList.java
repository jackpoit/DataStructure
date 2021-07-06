package linkedlist;

import java.io.DataInputStream;

public class LinkedList<E> {

	private class Node {
		public E e;
		public Node next;

		public Node() {
			this.next = null;
			this.e = null;
		}

		public Node(E e, Node next) {
			this.e = e;
			this.next = next;
		}

		public Node(E e) {
			this.e = e;
			this.next = null;
		}

		@Override
		public String toString() {
			return e.toString();
		}
	}

	private Node dummyHead;
	private int size;

	public LinkedList() {
		dummyHead = new Node(null, null);
		size = 0;
	}

	public LinkedList(E[] arrs) {
		if (arrs == null || arrs.length == 0)
			throw new IllegalArgumentException("arr can not be empty");

		dummyHead = new Node();
		Node cur = dummyHead;
		for (int i = 0; i < arrs.length; i++) {
			cur.next = new Node(arrs[i]);
			cur = cur.next;
		}
		size = arrs.length;

//		Node head;
//		head=new Node(arrs[0]);
//		Node cur=head;
//		for (int i=1;i<arrs.length;i++){
//			cur.next=new Node(arrs[i]);
//			cur=cur.next;
//		}

//		this.val = arr[0];
//		ListNode cur = this;
//		for(int i = 1 ; i < arr.length ; i ++){
//			cur.next = new ListNode(arr[i]);
//			cur = cur.next;
//		}


	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(E e, int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("Add Failed.Illegal index");
		}
		Node prve = dummyHead;
		// 设置虚拟头结点  不用特殊化原来的head节点了
		for (int i = 0; i < index; i++) {
			prve = prve.next;
		}
		prve.next = new Node(e, prve.next);
		size++;
	}

	public void addFirst(E e) {
		add(e, 0);
	}

	public void addLast(E e) {
		add(e, size);
	}

	public E get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IllegalArgumentException("Get Failed.Illegal index");
		}
		Node cur = dummyHead.next;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.e;
	}

	public E getFirst() {
		return get(0);
	}

	public E getLast() {
		return get(size - 1);
	}

	public void set(int index, E e) {
		if (index < 0 || index > size - 1) {
			throw new IllegalArgumentException("Get Failed.Illegal index");
		}
		Node cur = dummyHead.next;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		cur.e = e;
	}

	public boolean contain(E e) {
		Node cur = dummyHead.next;
		while (cur != null) {
			if (cur.e.equals(e)) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

	public E remove(int index) {
		if (index < 0 || index > size - 1) {
			throw new IllegalArgumentException("Get Failed.Illegal index");
		}
		Node prev = dummyHead;
		for (int i = 0; i < index; i++) {
			prev = prev.next;
		}
		Node delNode = prev.next;
		prev.next = delNode.next;
		delNode.next = null; //清除内存
		size--;
		return delNode.e;
	}

	public E removeFirst() {
		return remove(0);
	}

	public E removeLast() {
		return remove(size - 1);
	}

	//
	public void removeElement(E e) {

		Node prev = dummyHead;
		while (prev.next != null) {
			if (prev.next.e.equals(e)) {
				prev.next = prev.next.next;
				size--;
				break;
			}
			prev = prev.next;
		}

//		while(prev.next != null){
//			if(prev.next.e.equals(e))
//				break;
//			prev = prev.next;
//		}
//
//		if(prev.next != null){
//			Node delNode = prev.next;
//			prev.next = delNode.next;
//			delNode.next = null;
//			size --;
//		}

	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
			res.append(cur + "->");
		}
		res.append("NULL");
		return res.toString();
	}

	public static void main(String[] args) {
		Integer[] nums = {1, 2, 4, 5, 765, 7, 7};
		LinkedList<Integer> list = new LinkedList<>(nums);
		System.out.println(list);
	}

}
