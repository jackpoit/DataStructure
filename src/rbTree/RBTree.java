package rbTree;


import util.FileOperation;

import java.util.ArrayList;

/**
 * @Author: rua
 * @Date: 2021/8/8 19:50
 * @Description:
 */
public class RBTree<K extends Comparable<K>, V> {
	private static final boolean BLACK = false;
	private static final boolean RED = true;

	private class Node {
		public K key;
		public V value;
		public Node left, right;
		public boolean color;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
			color = RED;
		}
	}

	private Node root;
	private int size;

	public RBTree() {
		root = null;
		size = 0;
	}


	public int getSize() {
		return size;
	}


	public boolean isEmpty() {
		return size == 0;
	}

	// 判断节点node的颜色
	private boolean isRed(Node node) {
		if (node == null)
			return BLACK;
		return node.color;
	}

	private void flipColors(Node node) {
		node.color = RED;
		node.left.color = BLACK;
		node.right.color = BLACK;
	}

	//   node                     x
	//  /   \     左旋转         /  \
	// T1   x   --------->   node   T3
	//     / \              /   \
	//    T2 T3            T1   T2
	private Node leftRotate(Node node) {
		Node x = node.right;
		node.right = x.left;

		x.left = node;

		x.color = node.color;
		node.color = RED;

		return x;
	}

	//     node                   x
	//    /   \     右旋转       /  \
	//   x    T2   ------->   y   node
	//  / \                       /  \
	// y  T1                     T1  T2
	private Node rightRotate(Node node) {
		Node x = node.left;
		node.left = x.right;

		x.right = node;

		x.color = node.color;
		node.color = RED;

		return x;
	}

	public void add(K key, V value) {
		root = add(root, key, value);
		root.color = BLACK; // 最终根节点为黑色节点
	}

	//在以node为根的树中添加K,V
	private Node add(Node node, K key, V value) {
		if (node == null) {
			size++;
			return new Node(key, value); // 默认插入红色节点
		}
		if (node.key.compareTo(key) > 0) {
			node.left = add(node.left, key, value);
		} else if (node.key.compareTo(key) < 0) {
			node.right = add(node.right, key, value);
		} else {    //相等
			node.value = value;
			return node;
		}


		if (isRed(node.right) && !isRed(node.left))
			node = leftRotate(node);
		//上面这句很关键 左旋就是一个子过程 他保证了 整个添加过程 右孩子不会出现红节点的性质
		//如果是 对一个二节点 :左侧添加 不操作 右侧添加:直接左旋
		// 下面2个判断对二节点的添加无效 跳到上一层就是对三节点的判断
		// (三节点对左孩子为红 左孩子的右孩子为红的左旋处理是在上一层迭代里完成的,并不是本层迭代完成的)
		//对一个三节点才会经历下面2种判断

		if (isRed(node.left)&&isRed(node.left.left))
			node=rightRotate(node);

		if (isRed(node.left)&&isRed(node.right))
			flipColors(node);

			return node;
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


	public boolean contains(K key) {
		return getNode(root, key) != null;
	}


	public V get(K key) {
		Node res = getNode(root, key);
		return res == null ? null : res.value;
	}


	public void set(K key, V newValue) {
		Node node = getNode(root, key);
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

		}
		node.left = removeMin(node.left);
		return node;
	}


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
	public static void main(String[] args){

		System.out.println("Pride and Prejudice");

		ArrayList<String> words = new ArrayList<>();
		if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
			System.out.println("Total words: " + words.size());

			RBTree<String, Integer> map = new RBTree<>();
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
