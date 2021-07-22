package UnionFind;

/**
 * Created with IntelliJ IDEA.
 * QuickFind 第一版Union-Find
 * 本质就是一个数组
 *
 * @Author: jackpoit
 * @Date: 2021/07/21/9:04
 * @Description:
 */
public class UnionFind1 implements UF {
	private int[] id;
	// id数组 索引表示每个存入的元素 值表示元素所在的集合

	public UnionFind1(int size) {
		id = new int[size];

		//初始化传入size 每个元素都指向自己 没有合并的元素
		for (int i = 0; i < size; i++) {
			id[i] = i;
		}
	}

	@Override
	public int getSize() {
		return id.length;
	}

	// 查看元素p和元素q是否所属一个集合
	// O(1)复杂度
	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}

	//查找元素p所在的集合编号
	//O(1)的复杂度
	private int find(int p) {
		if (p < 0 || p >= id.length) {
			throw new IllegalArgumentException("p is out of bonud");
		}
		return id[p];
	}

	//合并p和q元素的所属集合
	//O(n)的复杂度
	@Override
	public void unionElements(int p, int q) {
		int pId = find(p);
		int qId = find(q);
		//pId qId表示p q元素所属的集合
		if (pId == qId) {
			return;
		}
		//遍历一遍 如果元素的value(所属集合)是p的集合 就改成q的
		for (int i = 0; i < id.length; i++) {
			if (id[i] == pId)
				id[i] = qId;
		}
//		for (int value : id) {
//			if (value == pId) {
//				value = qId;
//			}
//		}
		//增强for循环不能给数组赋值
		//增强for循环中的循环变量相当于一个临时变量，系统会把数组元素依次赋给这个临时变量，
		//而这个临时变量并不是数组元素，它只是保存了数组元素的值。
		//所以当你赋值时，只是改变了临时变量的值，并不能改变原数组的值。

		//jvm对 增强for循环和 for each有底层的优化
		//在Main中的测试 快一倍!! (这个结论是错误 因为是改的是临时变量的值,并没有真正赋值给数组元素
	}
}
