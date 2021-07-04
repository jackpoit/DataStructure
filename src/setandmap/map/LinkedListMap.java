package setandmap.map;

import setandmap.FileOperation;

import java.util.ArrayList;

public class LinkedListMap<K, V> implements Map<K, V> {

	private class Node {

		public K key;
		public V value;
		public Node next;

		public Node(K key, V value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public Node(K key, V value) {
			this(key, value, null);
		}

		public Node() {
			this(null, null, null);
		}
	}

	Node dummyHead;
	int size;

	public LinkedListMap() {
		dummyHead = new Node();
		size = 0;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private Node getNode(K key) {
		Node cur = dummyHead.next;
		while (cur != null) {
			if (cur.key.equals(key))
				return cur;
			cur = cur.next;
		}
		return null;
	}

	@Override
	public void add(K k, V v) {
		Node node = getNode(k);
		if (node == null) {
			dummyHead.next = new Node(k, v, dummyHead.next);
			size++;
		} else {
			node.value = v;
		}
	}

	@Override
	public V remove(K k) {
		Node prev=dummyHead;
		while (prev.next!=null){
			if (k.equals(prev.next.key)){
				break;
			}
			prev=prev.next;
		}
		if (prev.next!=null){
			Node delNode=prev.next;
			prev.next = delNode.next;
			delNode.next=null;
			size--;
			return delNode.value;
		}
		return null;
	}

	@Override
	public boolean contains(K k) {
		return getNode(k) != null;
	}

	@Override
	public V get(K k) {
		Node node = getNode(k);
		return node == null ? null : node.value;
	}

	@Override
	public void set(K k, V v) {
		Node node = getNode(k);
		if (node == null)
			throw new IllegalArgumentException(k + " doesn't exist!");
		node.value = v;
	}

	public static void main(String[] args) {
		String fileName="Pride and Prejudice";
		ArrayList<String> words = new ArrayList<>();
		if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
			System.out.println("Total words: " + words.size());

			LinkedListMap<String, Integer> map = new LinkedListMap<>();
			for (String word : words) {
				if (map.contains(word))
					map.set(word, map.get(word) + 1);
				else
					map.add(word, 1);
			}

			System.out.println("Total different words: " + map.getSize());
			System.out.println("Frequency of PRIDE: " + map.get("pride"));
			System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
		}

		System.out.println();

	}


}
