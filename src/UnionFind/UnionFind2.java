package UnionFind;

/**
 * Created with IntelliJ IDEA.
 *	Quick Union 第二版Union-Find
 *	使用一个数组构建一棵指向父节点的树
 * @Author: jackpoit
 * @Date: 2021/07/21/10:32
 * @Description:
 */
public class UnionFind2 implements UF {
	private int[] parent;

	//parent数组 索引表示元素 值表示元素的父节点
	public UnionFind2(int size) {
		parent = new int[size];
		//初始化每个元素父节点指向自己 每一个元素都是一个根元素
		for (int i = 0; i < size; i++) {
			parent[i] = i;
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
	public void unionElement(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		//找到p q的根节点
		if (pRoot == qRoot) {
			return;
		}
		parent[pRoot]=qRoot;
	}
}
