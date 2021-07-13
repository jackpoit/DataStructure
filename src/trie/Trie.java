package trie;

import setandmap.map.Map;

import java.util.TreeMap;

public class Trie {
	private class Node {
		public boolean isWord;
		public TreeMap<Character, Node> next;

		public Node(boolean isWord) {
			this.isWord = isWord;
			next = new TreeMap<>();
		}

		public Node() {
			this(false);
		}
	}

	private Node root;
	private int size;

	public Trie() {
		root = new Node();
		size = 0;
	}

	public int getSize() {
		return size;
	}

	// 向Trie中添加一个新的单词word
	public void add(String word) {
		Node cur = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (cur.next.get(c) == null)
				cur.next.put(c, new Node());
			cur = cur.next.get(c);  //无论有无都会指向下个字符的节点
		}
		if (!cur.isWord) {
			cur.isWord = true;
			size++;
		}
	}
	public void addR(String word){
		addR(root,word);
	}
	//在node节点中添加word
	private void addR(Node node,String word){
		if (word.length()==0){   	//当word="" 说明添加完了在判断 当前的node是最后一个字母的节点
			 if (!node.isWord){		//不要想错了
			 	node.isWord=true;
			 	size++;
			 }
			 return;
		}
		char c=word.charAt(0);
		if (node.next.get(c)==null)
			node.next.put(c,new Node());

		addR(node.next.get(c),word.substring(1));	//"a".substring==""
	}





	// 查询单词word是否在Trie中
	public boolean contains(String word) {
		Node cur = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (cur.next.get(c) == null)
				return false;
			cur = cur.next.get(c);
		}
		return cur.isWord;
	}
	public boolean containsR(String word){
		return containsR(root,word);
	}
	//在node节点查找word
	private boolean containsR(Node node,String word){
		if (word.length()==0)
			return node.isWord;
		char c=word.charAt(0);
		if (node.next.get(c)==null)
			return false;
		return containsR(node.next.get(c),word.substring(1));
	}


	public static void main(String[] args) {
		Trie trie=new Trie();
		trie.addR("aaa");
		trie.addR("bbb");
		trie.addR("ccc");
		trie.addR("ddd");
		trie.add("herry");
		trie.add("panda");
		trie.add("asaweq");
		System.out.println(trie.getSize());
		String word="herry";
		System.out.println(trie.containsR(word));
		System.out.println(trie.contains(word));

	}
}
