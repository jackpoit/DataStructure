package bst;
//二分搜索树   此处的实现容器 要求元素不重复 而且要继承comparable(要可比才能存）
public class BST<E extends Comparable<E>> {
	private class Node{
		public E e;
		Node left,right;

		public Node(E e){
			this.e=e;
			this.left=null;
			this.right=null;
		}
	}
	private Node root;
	private int size;

	public BST(E e){
		root=new Node(e);
		size=0;
	}

	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size==0;
	}

	//向树中添加一个元素
	public void add(E e){
		root=add(root,e);
	}
	//私有的递归函数 向一个树添加一个元素 返回添加后的树
	private Node add(Node node,E e){
		if (node==null){
			return new Node(e);
		}
		if (node.e.compareTo(e)>0){
			node.right=add(node.right,e);
		}else if (node.e.compareTo(e)<0){
			node.left=add(node.left,e);
		}else {
			return node;//相等 不插入 直接返回  不允许重复元素
		}
		return node;
	}
	//遍历
	//前序 中序 后序

}
