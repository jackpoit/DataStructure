package bst;
//二分搜索树   此处的实现容器 要求元素不重复 而且要继承comparable(要可比才能存）
public class BST<E extends Comparable<E>> {
	private class Node{
		public E e;
		Node left,right;
		int depth;

		public Node(E e){
			this.e=e;
			this.left=null;
			this.right=null;
		}

	}
	private Node root;
	private int size;
	public BST(){
		root=null;
		size=0;
	}
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
		size++;
	}
	//私有的递归函数 向一个树添加一个元素 返回添加后的树
	private Node add(Node node,E e){
		if (node==null){
			return new Node(e);
		}
		if (node.e.compareTo(e)<0){
			node.right=add(node.right,e);
		}else if (node.e.compareTo(e)>0){
			node.left=add(node.left,e);
		}else {
			return node;//相等 不插入 直接返回  不允许重复元素
		}
		return node;
	}
	//查询
	public boolean contain(E e){
		return contain(root,e);
	}
	private boolean contain(Node node,E e){
		if (node==null) return false;
		if (node.e.equals(e)){
			return true;
		}else if (node.e.compareTo(e)>0){
			return contain(node.left,e);
		}else {
			return contain(node.right,e);
		}

	}
	//遍历
	//前序
	public void preOrder(){
		preOrder(root);
	}
	private void preOrder(Node node){
		if (node==null){
			return;
		}
		System.out.println(node.e);
		preOrder(node.left);
		preOrder(node.right);
	}
	//中序
	public void inOrder(){
		inOrder(root);
	}
	private void inOrder(Node node){
		if (node==null){
			return;
		}
		preOrder(node.left);
		System.out.println(node.e);
		preOrder(node.right);
	}

	//后序
	public void postOrder(){
		inOrder(root);
	}
	private void postOrder(Node node){
		if (node==null){
			return;
		}
		postOrder(node.left);
		System.out.println(node.e);
		postOrder(node.right);
	}


	private String generateDepthLine(Node node,int depth){
		StringBuilder res=new StringBuilder();
		for (int i=0;i<depth;i++){
			res.append("--");
		}
		if (node==null){
//			StringBuilder res=new StringBuilder();
//			for (int i=0;i<depth;i++){
//				res.append("--");
//			}
//			return res.append(null+"\n").toString();
			return res.toString()+"null\n";
		}

		res.append(generateDepthLine(node.left,depth+1));
		res.append(node.e+"\n");
		res.append(generateDepthLine(node.right,depth+1));
		return res.toString();
	}

	@Override
	public String toString() {
		return generateDepthLine(root,0);
	}

	public static void main(String[] args) {
		BST<Integer> bst=new BST<>();
		int[] nums={5,3,2,4,6,8};
		for (int i=0;i<nums.length;i++){
			bst.add(nums[i]);
		}
//		bst.preOrder();
//		System.out.println("\n\n");
//		bst.inOrder();
//		System.out.println("\n\n");
//		bst.postOrder();
//		System.out.println(bst.contain(3));

		System.out.println(bst);
	}

}
