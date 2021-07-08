package heapandpriorityQueue;

import array.Array;

public class MaxHeap<E extends Comparable<E>> {

	private Array<E> data;

	public MaxHeap(int capacity) {
		data = new Array<>(capacity);
	}

	public MaxHeap() {
		data = new Array<E>();
	}

	public int size() {
		return data.getSize();
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	// 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
	private int parent(int index) {
		if (index == 0) {
			throw new IllegalArgumentException("index-0 does not have parent");
		}
		return (index - 1) / 2;
	}

	// 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
	private int leftChild(int index) {
		return index * 2 + 1;
	}

	// 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
	private int rightChild(int index) {
		return index * 2 + 2;
	}


	// 向堆中添加元素
	public void add(E e) {
		data.addLast(e);
		siftUp(data.getSize() - 1);
	}

	//上浮
	private void siftUp(int k) {
		while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0) {
			data.swap(k, parent(k));
			k = parent(k);
		}
	}

	// 看堆中的最大元素
	public E findMax() {
		if (data.isEmpty()) {
			throw new IllegalArgumentException("Can not findMax when heap is empty");
		}
		return data.get(0);
	}

	// 取出堆中最大元素
	public E extractMax() {
		E ret = findMax();
		data.swap(data.getSize() - 1, 0);
		data.removeLast();
		siftDown(0);
		return ret;

//		if (data.isEmpty()){
//			throw new IllegalArgumentException("Can not extractMax when heap is empty.");
//		}
//		E ret=data.getFirst();
//		data.set(0,data.removeLast());
//		siftDown(0);
//		return ret;

	}

	//下沉
	private void siftDown(int k) {
		while (leftChild(k) < data.getSize()) {
			int i = leftChild(k);
			if (i + 1 < data.getSize() && data.get(i + 1).compareTo(data.get(i)) > 0) {
				i++;                    //判断是否有右孩子并找出左右孩子的最大值
			}
			if (data.get(i).compareTo(data.get(k)) > 0) {    //如果节点小于最大节点--就换位置
				data.swap(i, k);
				k = i;
			} else {
				break;   ///大于最大的节点--满足堆的条件就不换  之后也不需要换了 直接break!!别忘了
			}

		}


	}


}
