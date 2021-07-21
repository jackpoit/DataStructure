package UnionFind;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: jackpoit
 * @Date: 2021/07/21/8:58
 * @Description: 并查集接口 (connected问题)
 */
public interface UF {
	/**
	 * getSize
	 *
	 * @return size
	 */
	int getSize();

	/**
	 * @param p 元素索引
	 * @param q 元素索引
	 * @return 是否在一个集合中
	 */
	boolean isConnected(int p, int q);

	/**
	 * 合并2个元素到集合
	 * @param p 元素索引
	 * @param q 元素索引
	 */
	void unionElements(int p, int q);
}
