package linkedlist;

public class Main {
	public static void main(String[] args) {
		LinkedList<Integer> linkedList=new LinkedList<>();
		for (int i=0;i<10;i++){
			linkedList.addLast(i);
			System.out.println(linkedList);
		}
		linkedList.add(666,5);
		System.out.println(linkedList);
		linkedList.addFirst(111);
		System.out.println(linkedList);
	}
}
