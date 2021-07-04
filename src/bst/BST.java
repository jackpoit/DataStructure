package bst;

import java.util.*;

//二分搜索树   此处的实现容器 要求元素不重复 而且要继承comparable(要可比才能存）
public class BST<E extends Comparable<E>> {
	private class Node {
		public E e;
		public Node left, right;

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
	}

	//私有的递归函数 向一个树添加一个元素 返回添加后的树
	private Node add(Node node, E e) {
		if (node == null) {
			size++; 			 //维护size
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

	public void postOrderNR2() {
		if (root == null)
			return;

		Stack<Node> stack = new Stack<>();
		Node cur = root;    //cur表示当前元素(用需要压入栈的元素 来解释更好一点）
		Node pre = null;    //pre表示刚输出的元素
		while (cur != null || !stack.isEmpty()) { //cur不为空 就要生产  stack不为空 就要消费
			while (cur != null) {                //生产 沿左子树一撸到底
				stack.push(cur);
				cur = cur.left;						//生产完 cur变为null
			}
			//消费
			if (stack.peek().right == null || stack.peek().right == pre) { //只有栈顶元素的右孩子为空或者等于pre时 才可以输出
				pre = stack.pop();
				System.out.println(pre.e);
			} else {
				cur = stack.peek().right;	 //需要生产才指定 cur
			}
		}
	}

	//层序遍历
	public void levelOrder() {
		if (root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		//add:linkedLast  offer=add
		//remove:removeFirst  poll:unlinkedFirst  peek：first element:first
		queue.add(root);
		while (!queue.isEmpty()) {
			Node cur = queue.remove();
			System.out.println(cur.e);
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
	}

	// 寻找二分搜索树的最小元素(非递归）
	public E minimumNR() {
		if (size == 0)
			throw new IllegalArgumentException("BST is empty");
		Node cur = root;
		while (cur.left != null) {
			cur = cur.left;
		}
		return cur.e;
	}

	// 寻找二分搜索树的最小元素
	public E minimum() {
		if (size == 0)
			throw new IllegalArgumentException("BST is empty");
		return minimum(root).e;
	}

	// 返回以node为根的二分搜索树的最小值所在的节点
	private Node minimum(Node node) {
		if (node.left == null) {
			return node;
		}
		return minimum(node.left);
	}

	// 寻找二分搜索树的最大元素
	public E maximum() {
		if (size == 0)
			throw new IllegalArgumentException("BST is empty");
		return maximum(root).e;
	}

	// 返回以node为根的二分搜索树的最小值所在的节点
	private Node maximum(Node node) {
		if (node.right == null) {
			return node;
		}
		return maximum(node.right);
	}

	// 从二分搜索树中删除最小值所在节点, 返回最小值
	public E removeMin() {
		E ret = minimum();
		root = removeMin(root);
		return ret;
	}

	// 删除掉以node为根的二分搜索树中的最小节点
	// 返回删除节点后新的二分搜索树的根
	private Node removeMin(Node node) {
		if (node.left == null) {
			size--;
			return node.right;
		}
		node.left = removeMin(node.left);
		return node;
	}

	// 从二分搜索树中删除最大值所在节点
	public E removeMax() {
		E ret = maximum();
		root = removeMax(root);
		return ret;
	}

	// 删除掉以node为根的二分搜索树中的最大节点
	// 返回删除节点后新的二分搜索树的根
	private Node removeMax(Node node) {
		if (node.right == null) {
			size--;
			return node.left;
		}
		node.right = removeMax(node.right);
		return node;
	}

	public void remove(E e) {
		if (size == 0)
			throw new IllegalArgumentException("BST is empty");
		root = remove(root, e);
	}

	//以node为根删除一个元素
	//返回一个以node为根删除一个元素后的新树
	private Node remove(Node node, E e) {
		if (node == null) {
			return null;
		}
		if (e.compareTo(node.e) < 0) {
			node.left = remove(node.left, e);
			return node;
		} else if (e.compareTo(node.e) > 0) {
			node.right = remove(node.right, e);
			return node;
		} else { //e.compareTo node.e
			if (node.left == null) {
				size--;
				return node.right;
			} else if (node.right == null) {
				size--;
				return node.left;
			}
			Node successor = minimum(node.right);
			successor.left = node.left;
			successor.right = removeMin(node.right);
			return successor;
		}

	}

	//floor
	public E floor(E e) {
		if (size() == 0) {
			throw new IllegalArgumentException("BST is empty");
		}
		Node ret = floor(root, e);
		return ret == null ? null : ret.e;

	}

	//在以node为根的树中找到小于e的最大值
	private Node floor(Node node, E e) {
		if (node == null) {    //节点为空直接返回
			return null;
		}
		if (e.compareTo(node.e) <= 0) {    //e小于等于节点e 就在左子树中找ceil 因为当前节点和右子树中的元素都比e大
			return floor(node.left, e);    //不需要判断 如果返回null就说明这个左子树没有比e小的
		} else {            //e比节点值大， 就在右子树找 但是要判断
			Node temp = floor(node.right, e);            //如果返回了非空node说明在右子树找到比e小的最大值了
			return temp == null ? node : temp;            //如果为空说明没找到 就要返回当前节点即比e小的最大值
			//存在这个节点就返回 左子树的最大值
		}
//		else {
//			return node.left==null?null:maximum(node.left);
//			//要判断一下 因为maxium 默认当前节点不为空
//		}

	}

	//ceil
	public E ceil(E e) {
		if (size() == 0) {
			throw new IllegalArgumentException("BST is empty");
		}
		Node ret = ceil(root, e);
		return ret == null ? null : ret.e;
	}

	//在以node为根的树中找到大于e的最小值
	private Node ceil(Node node, E e) {
		if (node == null) {
			return null;
		}
		if (e.compareTo(node.e) >= 0) {   //e大于等于当前节点e 直接在其右子树找 因为当前节点和左子树都比e小或相等
			return ceil(node.right, e);
		} else {    //e小于当前节点e
			Node res = ceil(node.left, e);        //在node的左子树中找
			return res == null ? node : res;    //找的到就返回 找不到就返回当前node即为最小的大于e的值
		}
//		else {				//相等不用判断了 直接归到上面
//			return node.right==null?null:minimum(node.right);
//
//		}
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
		int[] nums = {30, 12, 43, 55, 2, 5, 7, 36, 43, 76, 1, 3, 8, 17, 19, 20};
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

		System.out.println(bst.ceil(10));

	}

}
