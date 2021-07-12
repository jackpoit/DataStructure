package segmenttree;

public class SegmentTree<E> {
	private E[] data; //存放原数据的
	private E[] tree;	//线段树的完全二叉树形式 对于n个数需要(4n-5)个空间 近似于4n个
	public SegmentTree(E[] arr){
		this.data= (E[]) new Object[arr.length];
		for (int i = 0; i < arr.length; i++) {
			data[i]=arr[i];
		}
		this.tree= (E[]) new Object[arr.length*4];
	}

	public int getSize(){
		return data.length;
	}
	public E get(int index){
		if (index<0||index>=data.length)
			throw new IllegalArgumentException("index is illegal");
		return data[index];
	}

	// 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
	private int leftChild(int index){
		return index*2+1;
	}
	// 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
	private int rightChild(int index){
		return index*2+2;
	}

	private void buildSegmentTree(){

	}

}
