package avl;

import setandmap.set.Set;

/**
 * @author: jackpoit
 * @date: 2021/8/2
 * @description:
 **/
public class AVLSet<K extends Comparable<K>> implements Set<K> {
	private AVLTree<K,Object> avlTree;

	public AVLSet() {
		avlTree=new AVLTree<>();
	}
	@Override
	public void add(K k) {
		avlTree.add(k,null);
	}

	@Override
	public void remove(K k) {
		avlTree.remove(k);
	}

	@Override
	public boolean contains(K k) {
		return avlTree.contains(k);
	}

	@Override
	public int getSize() {
		return avlTree.getSize();
	}

	@Override
	public boolean isEmpty() {
		return avlTree.isEmpty();
	}
}
