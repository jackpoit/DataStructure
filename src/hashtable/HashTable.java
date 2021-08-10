package hashtable;

import java.security.Key;
import java.util.TreeMap;

/**
 * @Author: rua
 * @Date: 2021/8/10 9:30
 * @Description:
 */
public class HashTable<K, V> {
	private TreeMap<K, V>[] hashtable;
	private int size;
	private int M; //数组长度

	public HashTable(int M) {
		this.M = M;
		hashtable = new TreeMap[M];
		size = 0;
		for (int i = 0; i < M; i++) {
			hashtable[i] = new TreeMap<>();
		}
	}

	public HashTable() {
		this(97);
	}

	public int getSize() {
		return size;
	}

	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public void add(K key, V value) {

		TreeMap<K, V> map = hashtable[hash(key)];
		if (!map.containsKey(key)) {
			size++;
		}
		map.put(key, value);

	}

	public V remove(K key) {
		TreeMap<K, V> map = hashtable[hash(key)];
		V ret = null;
		if (map.containsKey(key)) {
			ret = map.remove(key);
			size--;
		}
		return ret;
	}

	public void set(K key, V value) {
		TreeMap<K, V> map = hashtable[hash(key)];
		if (!map.containsKey(key))
			throw new IllegalArgumentException(key + " doesn't exist!");

		map.put(key,value);

	}

	public boolean contains(K key){
		return hashtable[hash(key)].containsKey(key);
	}

	public V get(K key){
		return  hashtable[hash(key)].get(key);
	}

}
