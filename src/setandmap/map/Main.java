package setandmap.map;


import util.FileOperation;

import java.util.ArrayList;

public class Main {
	private static double testMap(Map<String, Integer> map, String fileName) {
		long startTime = System.nanoTime();
		System.out.println(fileName);
		ArrayList<String> words = new ArrayList<>();

		if (FileOperation.readFile(fileName + ".txt", words)) {
			System.out.println("Total words: " + words.size());
			for (String word : words) {
				if (map.contains(word)) {
					map.set(word, map.get(word) + 1);
				} else {
					map.add(word, 1);
				}
			}
			System.out.println("Total different words: " + map.getSize());
			System.out.println("Frequency of PRIDE: " + map.get("pride"));
			System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
		}
		long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;

	}

	public static void main(String[] args) {
		String fileName = "pride-and-prejudice";
		LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
		double time1 = testMap(linkedListMap, fileName);
		System.out.println("Linked List Map: " + time1 + " s");
		System.out.println();
		BSTMap<String, Integer> bstMap = new BSTMap<>();
		double time2 = testMap(bstMap, fileName);
		System.out.println("BST Map: " + time2 + " s");
	}
}
