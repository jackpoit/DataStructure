package setandmap.map;

import setandmap.FileOperation;
import setandmap.map.Map;

import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

	private class Node {
		K key;
		V value;
		Node left, right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}

		public Node() {
			this(null, null);
		}

		@Override
		public String toString() {
			return key.toString() + ":" + value.toString();
		}
	}

	private Node root;
	private int size;

	public BSTMap() {
		root = null;
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

	@Override
	public void add(K key, V value) {
		root = add(root, key, value);
	}

	//在以node为根的树中添加K,V
	private Node add(Node node, K key, V value) {
		if (node == null) {
			size++;
			return new Node(key, value);
		}
		if (node.key.compareTo(key) > 0) {
			node.left = add(node.left, key, value);
		} else if (node.key.compareTo(key) < 0) {
			node.right = add(node.right, key, value);
		} else {    //相等
			node.value = value;
		}
		return node;
	}

	//
	private Node getNodeNR(K key) {
		Node cur = root;
		while (cur != null) {
			if (cur.key.equals(key)) {
				return cur;
			} else if (cur.key.compareTo(key) > 0) {
				cur = cur.right;
			} else {        //cur.key < key
				cur = cur.left;
			}
		}
		return null;
	}

	private Node getNode(Node node, K key) {
		if (node == null) {
			return null;
		}
		if (key.compareTo(node.key) < 0) {
			return getNode(node.left, key);
		} else if (key.compareTo(node.key) > 0) {
			return getNode(node.right, key);
		} else {
			return node;
		}
	}

	@Override
	public boolean contains(K key) {
		return getNode(root, key) != null;
	}

	@Override
	public V get(K key) {
		Node res = getNode(root, key);
		return res == null ? null : res.value;
	}

	@Override
	public void set(K key, V newValue) {
		Node node = getNode(root, key);
		System.out.println(node);
		if (node == null) {
			throw new IllegalArgumentException(key + "does not exist!");
		}
		node.value = newValue;
	}

	//在以node为根的树中找到最小值的节点并返回
	//注意传入的node 要不为空 不然会空指针异常
	private Node minimum(Node node) {
		if (node.left == null) {
			return node;
		}
		return minimum(node.left);
	}

	//在以node为根的树中删除最小值节点
	//并返回删除后的树
	private Node removeMin(Node node) {
		if (node.left == null) {
			size--;
			return node.right;
//			Node rightNode=node.right;
//			node.right=null;//? 释放空间 ?
//			size--;
//			return rightNode;
		}
		node.left = removeMin(node.left);
		return node;
	}

	@Override
	public V remove(K key) {
		Node node = getNode(root, key);
		if (node != null) {
			root = remove(root, key);
			return node.value;
		}
		return null;
	}

	// 在以node为根的树中 删除值为key的节点 并返回删除后的树
	private Node remove(Node node, K key) {
		if (node == null) {
			return null;
		}
		if (node.key.compareTo(key) > 0) {
			node.left = remove(node.left, key);
			return node;
		} else if (node.key.compareTo(key) < 0) {
			node.right = remove(node.right, key);
			return node;
		} else {
			if (node.right == null) {    // 注意维护size;!!!!
				size--;
				return node.left;
			} else if (node.left == null) {
				size--;
				return node.right;
			} else {
				Node successor = minimum(node.right);
				successor.left = node.left;
				successor.right = removeMin(node.right);

				node.left = node.right = null;

				return successor;
			}
		}
	}


	public static void main(String[] args) {
		System.out.println("pride-and-prejudice");
//		BSTMap<String, Integer> bstMap = new BSTMap<>();
//		bstMap.set("aa", bstMap.get("aa"));
//		System.out.println(bstMap.get("aa"));//空指针和IllegalArgumentException
		ArrayList<String> words = new ArrayList<>();
		if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
			System.out.println("Total words: " + words.size());
			BSTMap<String, Integer> bstMap = new BSTMap<>();
			for (String word : words) {
				if (bstMap.contains(word)) {
					bstMap.set(word, bstMap.get(word) + 1); // 报空指针是因为没找到 get返回的是null  null+1导致的空指针
				} else {
					bstMap.add(word, 1);
				}
			}
			System.out.println("Total different words: " + bstMap.getSize());
			System.out.println("Frequency of PRIDE: " + bstMap.get("pride"));
			System.out.println("Frequency of PREJUDICE: " + bstMap.get("prejudice"));
		}

	}


}
