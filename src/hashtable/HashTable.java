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

	private final int[] capacity
			= {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
			49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
			12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
	private static final int upperTol = 10;
	private static final int lowerTol = 2;
	private int capacityIndex = 0;

	public HashTable() {
		this.M = capacity[capacityIndex];
		hashtable = new TreeMap[M];
		size = 0;
		for (int i = 0; i < M; i++) {
			hashtable[i] = new TreeMap<>();
		}
	}


	public int getSize() {
		return size;
	}

	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public void add(K key, V value) {
		TreeMap<K, V> map = hashtable[hash(key)];
		if (map.containsKey(key)) {
			map.put(key, value);
		} else {
			map.put(key, value);
			size++;
			if (size > upperTol * M && capacityIndex + 1 < capacity.length) {
				capacityIndex++;
				resize(capacity[capacityIndex]);
			}
		}
	}


	public V remove(K key) {
		TreeMap<K, V> map = hashtable[hash(key)];
		V ret = null;
		if (map.containsKey(key)) {
			ret = map.remove(key);
			size--;
			if (size < lowerTol * M && capacityIndex > 0) {
				capacityIndex--;
				resize(capacity[capacityIndex]);
			}
		}
		return ret;
	}

	public void set(K key, V value) {
		TreeMap<K, V> map = hashtable[hash(key)];
		if (!map.containsKey(key))
			throw new IllegalArgumentException(key + " doesn't exist!");

		map.put(key, value);

	}

	public boolean contains(K key) {
		return hashtable[hash(key)].containsKey(key);
	}

	public V get(K key) {
		return hashtable[hash(key)].get(key);
	}

	private void resize(int newM) {
		TreeMap<K, V>[] newHashtable = new TreeMap[newM];
		for (int i = 0; i < newM; i++) {
			newHashtable[i] = new TreeMap<>();
		}

		this.M = newM;
		for (TreeMap<K, V> map : hashtable) {
			for (K key : map.keySet()) {
				newHashtable[hash(key)].put(key, map.get(key));
			}
		}

		this.hashtable = newHashtable;

	}
}
