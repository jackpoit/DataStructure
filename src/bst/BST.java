package bst;

import java.util.Stack;

//二分搜索树   此处的实现容器 要求元素不重复 而且要继承comparable(要可比才能存）
public class BST<E extends Comparable<E>> {
	private class Node {
		public E e;
		Node left, right;
		int depth;

		public Node(E e) {
			this.e = e;
			this.left = null;
			this.right = null;
		}

	}

	private Node root;
	private int size;

	public BST() {
		root = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	//向树中添加一个元素
	public void add(E e) {
		root = add(root, e);
		size++;
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

	//查询
	public boolean contain(E e) {
		return contain(root, e);
	}

	private boolean contain(Node node, E e) {
		if (node == null)
			return false;
		if (e.compareTo(node.e) == 0) {
			return true;
		} else if (node.e.compareTo(e) > 0) {
			return contain(node.left, e);
		} else {
			return contain(node.right, e);
		}

	}

	//遍历
	//递归前序遍历
	public void preOrder() {
		preOrder(root);
	}

	//私有函数 用做递归 对以node为根的树进行遍历
	private void preOrder(Node node) {
		if (node == null) {
			return;
		}
		System.out.println(node.e);
		preOrder(node.left);    //对以node.left为根的树 遍历
		preOrder(node.right);    //对以node.right为根的树 遍历
	}

	//二分搜索树的非递归前序遍历
	public void preOrderNR() {
		if (root == null)
			return;

		Stack<Node> stack = new Stack<>();
		stack.push(root);                //先把根入栈
		while (!stack.isEmpty()) {
			Node cur = stack.pop();        //每次出栈时
			System.out.println(cur.e);    //进行相应的遍历操作
			if (cur.right != null) {        //每次出栈 先把右节点压进去 再压左节点
				stack.push(cur.right);
			}
			if (cur.left != null) {
				stack.push(cur.left);
			}
		}
	}


	//中序 递归
	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(Node node) {
		if (node == null) {
			return;
		}
		inOrder(node.left);
		System.out.println(node.e);
		inOrder(node.right);
	}

	//二分搜索树的非递归中序遍历
	public void inOrderNR() {
		if (root == null)
			return;

		Stack<Node> stack = new Stack<>();
		Node cur = root;
		while (cur != null) {
			stack.push(cur);    //先将左列依次压入栈
			cur = cur.left;
		}
		while (!stack.isEmpty()) {
			cur = stack.pop();    //从最左边的开始依次出栈
			System.out.println(cur.e);
			if (cur.right != null) {        //如果当前的cur的右子树不为空,就要开始遍历右子树的部分 想办法把他们按照顺序全压入栈
				cur = cur.right;            //因为要在遍历下一个（位于当前节点上面的最左边节点）节点之前 把这个节点下的所有元素都遍历
				while (cur != null) {        //同样 对这一个子树 要先依次把左节点压进去 然后进行相同的出栈操作
					stack.push(cur);
					cur = cur.left;
				}
			}
		}
	}

	public void inOrderNR2() {
		if (root == null)
			return;

		Stack<Node> stack = new Stack<>();
		Node cur = root;
		while (cur != null || !stack.isEmpty()) { //cur不为空 就要生产  stack不为空 就要消费
			while (cur != null) {        //生产 沿左子树一撸到底
				stack.push(cur);
				cur = cur.left;
			}
			//消费
			cur = stack.pop();
			System.out.println(cur.e);
			cur = cur.right;        // 消费完 把节点移到右子树
			// 这句话很关键	每次消费都把cur移到右孩子 不管是不是null
			//这样就可以避免上面 cur!=null时的重复入栈了
			//因为有孩子不为null就会入栈 为null--cui就会跳到上一个节点去了

		}
	}

	//后序
	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(Node node) {
		if (node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.e);
	}

	//二分搜索树的非递归后序遍历
	public void postOrderNR() {
		if (root == null)
			return;

		Stack<Node> stack = new Stack<>();
		Node cur = root;    //cur表示当前元素
		Node pre = null;    //pre表示刚输出的元素
		while (cur != null || !stack.isEmpty()) { //cur不为空 就要生产  stack不为空 就要消费
			while (cur != null) {                //生产 沿左子树一撸到底
				stack.push(cur);
				cur = cur.left;
			}
			//消费
			cur = stack.peek();
			if (cur.right == null || cur.right == pre) { //只有栈顶元素的右孩子为空或者等于pre时 才可以输出
				pre = stack.pop();
				System.out.println(pre.e);
				cur = null;
				//因为输出的元素都是左子树上的 ,输出这个元素之后 以此元素为跟节点的树就遍历完了 就希望从这个节点上一个节点开始
				//将cur置为null，如果栈中还有元素就继续 ,无元素就结束循环
				//前面cur指向的栈顶元素  不置为空会一直循环这个元素
			} else {
				cur = cur.right;
			}
		}

	}


	//生出以node 为根节点，深度为depth的描述二叉树的字符串
	private void generateString(Node node, int depth, StringBuilder res) {
		if (node == null) {
			res.append(generateDepthString(depth) + "null\n");
			return;
		}
		res.append(generateDepthString(depth) + node.e + "\n");
		generateString(node.left, depth + 1, res);
		generateString(node.right, depth + 1, res);

	}

	private String generateDepthString(int depth) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			res.append("--");
		}
		return res.toString();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		generateString(root, 0, res);
		return res.toString();
	}

	public static void main(String[] args) {
		BST<Integer> bst = new BST<>();
//		int[] nums = {5, 3, 6, 8, 4, 2};
		int[] nums={30,12,43,55,2,5,7,36,43,76,1,3,8,17,19,20};
		/////////////////
		//      5      //
		//    /   \    //
		//   3    6    //
		//  / \    \   //
		// 2  4     8  //
		/////////////////
		for (int i = 0; i < nums.length; i++) {
			bst.add(nums[i]);
		}
//		bst.inOrder();
//		System.out.println();
//		bst.inOrderNR();
//		System.out.println();
//		bst.inOrderNR2();

		bst.postOrder();
		System.out.println();
		bst.postOrderNR();


	}

}
