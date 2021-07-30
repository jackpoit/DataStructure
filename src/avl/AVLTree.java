package avl;

import util.FileOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: rua
 * @Date: 2021/7/29 12:46
 * @Description: AVLTree:任意节点 左右子树高度差不大于1
 * AVLTree并不一定是平衡二叉树,但遍历的时间复杂度也是log(n)
 * 避免了普通的二分搜索树因添加顺序退化成链表的问题
 */
public class AVLTree<K extends Comparable<K>, V> {
	private class Node {
		public K key;
		public V value;
		public Node left, right;
		public int height;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			height = 1;
		}
	}

	private Node root;
	private int size;

	public AVLTree() {
		root = null;
		size = 0;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	//判断该二叉树是否仍是二分搜索树
	public boolean isBST(){
		ArrayList<K> list = new ArrayList<>();
		inOrder(root,list);
		for (int i = 0; i < list.size()-1; i++) {
			if (list.get(i).compareTo(list.get(i+1))>0)
				return false;
		}
		return true;
	}
	//中序遍历添加每个元素至list
	private void inOrder(Node node, List<K> list){
		if (node==null)
			return;
		inOrder(node.left,list);
		list.add(node.key);
		inOrder(node.right,list);
	}

	public boolean isBalanced(){
		return isBalanced(root);
	}
	private boolean isBalanced(Node node){
		if (node==null)
			return true;

		int balanceFactor = getBalanceFactor(node);
		if (Math.abs(balanceFactor)>1)
			return false;
		//判断完当前节点还要看左右子节点(只有自己以及左右子树都满足才返回true)
		return isBalanced(node.left)&&isBalanced(node.right);
	}

	//获得当前节点的树高度
	private int getHeight(Node node) {
		if (node == null)
			return 0;
		return node.height;
	}

	//获得当前节点的平衡因子
	private int getBalanceFactor(Node node){
		if (node==null)
			return 0;
		return getHeight(node.left)-getHeight(node.right);

	}

// 对节点y进行向右旋转操作，返回旋转后新的根节点x
	//    //        y                              x
	//    //       / \                           /   \
	//    //      x   T4     向右旋转 (y)        z     y
	//    //     / \       - - - - - - - ->    / \   / \
	//    //    z   T3                       T1  T2 T3 T4
	//    //   / \
	//    // T1   T2
	private Node rightRotate(Node y){
		Node x=y.left;
		Node t3=x.right;
		y.left=t3;
//		y.left=x.right;
		x.right=y;

		//维护height
		y.height=Math.max(getHeight(y.right),getHeight(y.left))+1;
		x.height=Math.max(getHeight(x.right),getHeight(x.left))+1;

		return x;
	}

	// 对节点y进行向左旋转操作，返回旋转后新的根节点x
	//    y                             x
	//  /  \                          /   \
	// T1   x      向左旋转 (y)       y     z
	//     / \   - - - - - - - ->   / \   / \
	//   T2  z                     T1 T2 T3 T4
	//      / \
	//     T3 T4
	private Node leftRotate(Node y){
		Node x=y.right;
		Node t2=x.left;
		y.right=t2;
		x.left=y;

		//维护height
		y.height=Math.max(getHeight(y.right),getHeight(y.left))+1;
		x.height=Math.max(getHeight(x.right),getHeight(x.left))+1;

		return x;
	}

	public void add(K key, V value) {
		root = add(root, key, value);
	}

	//在以node为根的树中添加K,V,返回添加完之后的根节点
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
		//更新height
		node.height=Math.max(getHeight(node.left),getHeight(node.right))+1;
		int balanceFactor = getBalanceFactor(node);
//		if (Math.abs((balanceFactor))>1)
//			System.out.println("unbalanced:"+balanceFactor);

		//LL 左子树高度大于右子树且(导致这个问题的新节点在当前节点左子树的左子树上)
		if (balanceFactor>1&&getBalanceFactor(node.left)>=0)
			return rightRotate(node);

		//RR 右子树高度大于左子树且(导致这个问题的新节点在当前节点右子树的右子树上)
		if (balanceFactor<-1&&getBalanceFactor(node.right)<=0)
			return leftRotate(node);

		//LR
		if (balanceFactor>1&&getBalanceFactor(node.left)<0){
			node.left=leftRotate(node.left);
			return rightRotate(node);
		}
		//RL
		if (balanceFactor<-1&&getBalanceFactor(node.right)>0){
			node.right=rightRotate(node.right);
			return leftRotate(node);
		}

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

	public static void main(String[] args) {
		System.out.println("Pride and Prejudice");

		ArrayList<String> words = new ArrayList<>();
		if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
			System.out.println("Total words: " + words.size());

			AVLTree<String, Integer> map = new AVLTree<>();
			for (String word : words) {
				if (map.contains(word))
					map.set(word, map.get(word) + 1);
				else
					map.add(word, 1);
			}

			System.out.println("Total different words: " + map.getSize());
			System.out.println("Frequency of PRIDE: " + map.get("pride"));
			System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));


			System.out.println(map.isBST());
			System.out.println(map.isBalanced());
		}

		System.out.println();
	}
}
