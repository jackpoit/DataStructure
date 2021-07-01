package bst;

public class BSTDup<E extends Comparable<E>> {
	private Node root;
	public BSTDup(){
		root=null;
	}

	public int size(){
		return root==null?0:root.getSize();
	}
	public boolean isEmpty(){
		return size()==0;
	}

	//向树中添加一个元素
	public void add(E e) {
		root = add(root, e);
	}

	//私有的递归函数 向一个树添加一个元素 返回添加后的树
	private Node add(Node node, E e) {
		if (node == null) {
			return new Node(e);
		}
		if (node.e.compareTo(e) < 0) {
			node.right = add(node.right, e);
		} else if (node.e.compareTo(e) > 0) {
			node.left = add(node.left, e);
		}
		return node;//相等 不插入 直接返回  不允许重复元素
	}

	private class Node {
		public E e;
		public Node left, right;
		public int depth;
		public int size;//
		public int count;

		public Node(E e) {
			this.e = e;
			this.left = null;
			this.right = null;
			this.size=1;
			this.depth=1;
			this.count=1;
		}

		public E getE() {
			return e;
		}

		public void setE(E e) {
			this.e = e;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public int getDepth() {
			return depth;
		}

		public void setDepth(int depth) {
			this.depth = depth;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}
}
