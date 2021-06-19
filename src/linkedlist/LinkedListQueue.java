package linkedlist;

import Queue.Queue;

public class LinkedListQueue<E> implements Queue<E> {
	private LinkedList<E> list;
	public LinkedListQueue(){
		list=new LinkedList<>();
	}
	@Override
	public int getSize(){
		return list.getSize();
	}

	@Override
	public boolean isEmpty(){
		return list.isEmpty();
	}

	@Override
	public void enqueue(E e){
		list.addFirst(e);
	}

	@Override
	public E dequeue(){
		return list.removeLast();
	}

	@Override
	public E getFront(){
		return list.getFirst();
	}

	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append("Queue:front[");
		for (int i=0;i<list.getSize();i++){
			res.append(list.get(list.getSize()-1-i));
			if (i!=list.getSize()-1){
				res.append(",");
			}
		}
		res.append("]tail");
		return res.toString();
	}
	public static void main(String[] args) {
		LinkedListQueue<Integer> loopQueue=new LinkedListQueue<>();
		for (int i=0;i<20;i++){
			loopQueue.enqueue(i);
			System.out.println(loopQueue);
			if (i%3==2){
				loopQueue.dequeue();
				System.out.println(loopQueue);
			}
		}
	}
}
