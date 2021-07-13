package leecode;

import java.util.List;

public class Solution {
	public interface Merger<E> {
		E merge(E a,E b);
	}
	public class SegmentTree<E> {
		private E[] data; //存放原数据的
		private E[] tree;    //线段树的完全二叉树形式 对于n个数需要(4n-5)个空间 近似于4n个
		private Merger<E> merger;

		public SegmentTree(E[] arr, Merger<E> merger) {
			this.merger = merger;
			this.data = (E[]) new Object[arr.length];
			for (int i = 0; i < arr.length; i++) {
				data[i] = arr[i];
			}
			this.tree = (E[]) new Object[arr.length * 4];
			buildSegmentTree(0, 0, data.length - 1);
		}

		public int getSize() {
			return data.length;
		}

		public E get(int index) {
			if (index < 0 || index >= data.length)
				throw new IllegalArgumentException("index is illegal");
			return data[index];
		}

		// 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
		private int leftChild(int index) {
			return index * 2 + 1;
		}

		// 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
		private int rightChild(int index) {
			return index * 2 + 2;
		}

		// 在treeIndex的位置创建表示区间[l...r]的线段树
		private void buildSegmentTree(int treeIndex, int l, int r) {
			if (l == r) {
				tree[treeIndex] = data[l];
				return;  //
			}
			int leftTreeIndex = leftChild(treeIndex);
			int rightTreeIndex = rightChild(treeIndex);
			int mid = l + (r - l) / 2;
			buildSegmentTree(leftTreeIndex, l, mid);
			buildSegmentTree(rightTreeIndex, mid + 1, r);
			tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
		}

		// 返回区间[queryL, queryR]的值
		public E query(int queryL, int queryR) {
			if (queryL < 0 || queryL >= data.length ||
					queryR < 0 || queryR >= data.length || queryL > queryR)
				throw new IllegalArgumentException("Index is illegal.");

			return query(0, 0, data.length - 1, queryL, queryR);
		}

		// 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
		private E query(int treeIndex, int l, int r, int queryL, int queryR) {

			if (l == queryL && r == queryR)    //l-r的区间刚好是queryl queryR的区间 直接返回
				return tree[treeIndex];
			int mid = l + (r - l) / 2;
			int leftTreeIndex = leftChild(treeIndex);
			int rightTreeIndex = rightChild(treeIndex);
			if (mid >= queryR)
				return query(leftTreeIndex, l, mid, queryL, queryR);
			if (queryL >= mid + 1)
				return query(rightTreeIndex, mid + 1, r, queryL, queryR);

			return merger.merge(query(leftTreeIndex, l, mid, queryL, mid),
					query(rightTreeIndex, mid + 1, r, mid + 1, queryR));

		}

		// 将index位置的值，更新为e
		public void set(int index, E e) {
			if (index < 0 || index >= data.length)
				throw new IllegalArgumentException("Index is illegal");

			data[index] = e;
			//维护树
			set(0, 0, data.length - 1, index, e);
		}

		// 在以treeIndex为根区间为[l...r]的线段树中更新index的值为e
		private void set(int treeIndex, int l, int r, int index, E e) {
			if (l == r){
				tree[treeIndex] = e;
				return;
			}


			int mid = l + (r - l) / 2;
			int leftTreeIndex = leftChild(treeIndex);
			int rightTreeIndex = rightChild(treeIndex);
			if (index <= mid)
				set(leftTreeIndex, l, mid, index, e);
			else    //index>=mid+1
				set(rightTreeIndex, mid + 1, r, index, e);

			tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
		}


		@Override
		public String toString() {
			StringBuilder res = new StringBuilder();
			res.append("segTree:[");
			for (int i = 0; i < tree.length; i++) {
				if (tree[i] != null)
					res.append(tree[i]);
				else
					res.append("null");

				if (i != tree.length - 1)
					res.append(", ");
			}
			res.append(']');
			return res.toString();
		}
	}

	 private SegmentTree<Integer> segmentTree;

	public List<List<Integer>> getSkyline(int[][] buildings) {


		return null;
	}
}
