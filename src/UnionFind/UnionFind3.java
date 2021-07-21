package UnionFind;

/**
 * Created with IntelliJ IDEA.
 * 第三版 Union Find
 *
 *
 * @Author: jackpoit
 * @Date: 2021/07/21/20:18
 * @Description:
 */
public class UnionFind3 implements UF{
	private int[] parent;  // parent[i]表示第一个元素所指向的父节点
	private int[] sz;   // sz[i]表示以i为根的集合中元素个数


	//parent数组 索引表示元素 值表示元素的父节点
	public UnionFind3(int size) {
		parent = new int[size];
		sz=new int[size];
		//初始化每个元素父节点指向自己 每一个元素都是一个根元素
		for (int i = 0; i < size; i++) {
			parent[i] = i;
			sz[i]=1;
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


		// 根据两个元素所在树的元素个数不同判断合并方向
		// 将元素个数少的集合合并到元素个数多的集合上
		//这样可以适当的减少树的深度
		if (sz[pRoot]>sz[qRoot]){
			parent[qRoot]=pRoot;
			sz[pRoot]+=sz[qRoot];
			//同时要维护sz
		}else {  //sz[pRoot]<=sz[qRoot]
			parent[pRoot]=qRoot;
			sz[qRoot]+=sz[pRoot];
		}
	}
}
