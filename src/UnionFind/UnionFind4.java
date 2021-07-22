package UnionFind;

/**
 * @Author: rua
 * @Date: 2021/7/22
 * @Description: 第4版 Union Find
 */
public class UnionFind4 implements UF {

	private int[] parent;    // parent[i]表示第i个元素指向的父节点
	private int[] rank;    // rank[i]表示以i为根的集合所表示的树的层数


	public UnionFind4(int size) {
		parent = new int[size];
		rank = new int[size];
		//初始化每个元素父节点指向自己 每一个元素都是一个根元素
		for (int i = 0; i < size; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
	}

	@Override
	public int getSize() {
		return parent.length;
	}

	// 查看元素p和元素q是否所属一个集合
	// O(h)复杂度, h为树的高度
	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}

	//找到p元素的根节点 即p元素对应的集合编号
	//时间复杂度O(h) h为树的高度
	private int find(int p) {
		if (p < 0 || p >= parent.length) {
			throw new IllegalArgumentException("p is out of bound");
		}
		//找到p元素的根节点
		//根节点就是指向自己 即parent[p]=p 索引与值相同
		while (parent[p] != p) {
			p = parent[p];
		}
		return p;
	}

	//合并元素p,q的所属集合
	//时间复杂度O(h) h为树的高度
	@Override
	public void unionElements(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		//找到p q的根节点
		if (pRoot == qRoot) {
			return;
		}

		//根据2个元素根元素的rank值判断合并方向
		//将rank值低的合并到rank值高的上面
		if (rank[pRoot] > rank[qRoot]) {
			parent[qRoot] = pRoot;
		} else if (rank[pRoot] < rank[qRoot]) {
			parent[pRoot] = qRoot;
		} else { //rank[pRoot] = rank[qRoot]
			parent[qRoot]=pRoot;
			rank[pRoot]+=1;
		}
	}
}
