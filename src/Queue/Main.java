package Queue;

import java.util.Random;

public class Main {
	public static double testQueue(Queue<Integer> e, int opCount) {
		long startTime = System.nanoTime();
		Random random = new Random();
		for (int i = 0; i < opCount; i++) {
			e.enqueue(random.nextInt(Integer.MAX_VALUE));
		}
		for (int i = 0; i < opCount; i++) {
			e.dequeue();
		}
		long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}

	public static void main(String[] args) {
		int opCount = 100000;
		ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>();
		System.out.println(testQueue(arrayQueue, opCount) + "s");

		LoopQueue<Integer> loopQueue = new LoopQueue<Integer>();
		System.out.println(testQueue(loopQueue, opCount) + "s");

	}
}
