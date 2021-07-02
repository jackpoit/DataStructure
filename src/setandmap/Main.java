package setandmap;

import java.util.ArrayList;

public class Main {
	private static double testSet(Set<String> set,String fileName){
		long startTime=System.nanoTime();
		System.out.println(fileName);
		ArrayList<String> words=new ArrayList<>();
		if (FileOperation.readFile(fileName,words)){
			System.out.println("total words:"+words.size());
			for (String word:words){
				set.add(word);
			}
			System.out.println("total different words:"+set.getSize());
		}
		long endTime=System.nanoTime();
		return (endTime-startTime)/1000000000.0;

	}

	public static void main(String[] args) {
		BSTSet<String> set1=new BSTSet<>();
		LinkedListSet<String> set2=new LinkedListSet<>();
		System.out.println(testSet(set1,"pride-and-prejudice.txt"));
		System.out.println();
		System.out.println(testSet(set2,"pride-and-prejudice.txt"));
	}
}
