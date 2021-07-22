package UnionFind;

/**
 * @Author: rua
 * @Date: 2021/7/22
 * @Description: 第五版 Union Find  路径压缩
 *
 */
public class UnionFind5 implements UF{

	private int[] parent;    // parent[i]表示第i个元素指向的父节点
	private int[] rank;
	// rank[i]表示以i为根的集合所表示的树的层数
	// 在后续的代码中, 我们并不会维护rank的语意, 也就是rank的值在路径压缩的过程中, 有可能不在是树的层数值
	// 这也是我们的rank不叫height或者depth的原因, 他只是作为比较的一个标准


	public UnionFind5(int size) {
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

		while (parent[p] != p) {
			parent[p]=parent[parent[p]];
			//只要p不是根节点  就让它的父节点指向它父节点的父节点
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
