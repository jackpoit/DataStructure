package Queue;

public class LinkedListQueue<E> implements Queue<E>{
	private class Node{
		public Node next;
		public E e;
		public Node(){
			this(null,null);
		}
		public Node(E e){
			this(e,null);
		}
		public Node(E e,Node next){
			this.next=next;
			this.e=e;
		}

		@Override
		public String toString() {
			return e.toString();
		}
	}
	private Node head,tail;
	private int size;
	public LinkedListQueue(){
		head=null;
		tail=null;
		size=0;
	}
	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public void enqueue(E e) {
		if (tail==null){//说明队列(链表)为空
			tail=new Node(e);
			head=tail;
		}else {
			tail.next=new Node(e);
			tail=tail.next;
		}
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty()){
			throw new IllegalArgumentException("Cannot dequeue from an empty queue");
		}
		Node retNode=head;
		head=head.next;
		retNode.next=null;
		if (head==null){
			tail=null;// 如果head为空 说明链表为空 tail也必须要维护一下 指向空
		}
		size--;

		return retNode.e;
	}

	@Override
	public E getFront() {
		if (isEmpty()){
			throw new IllegalArgumentException("Cannot get from an empty queue");
		}
		return head.e;
	}

	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append("Queue: front[");
		Node cur=head;
		while (cur!=null){
			res.append(cur+"->");
			cur=cur.next;
		}
		res.append("Null] tail");
		return res.toString();
	}
	public static void main(String[] args) {
		LinkedListQueue<Integer> loopQueue=new LinkedListQueue<Integer>();
		for (int i=0;i<10;i++){
			loopQueue.enqueue(i);
			System.out.println(loopQueue);
			if (i%3==2){
				loopQueue.dequeue();
				System.out.println(loopQueue);
			}

		}
	}
}
