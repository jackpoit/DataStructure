package bst;

import java.util.Arrays;

public class Test02 {
	public static void main(String[] args) {
		/*
		 * 第一段代码
		 */
//		A<Integer> a = new A<>();
//		a.test();

		/*
		 * 第二段代码
		 */
		Integer[] tt = (Integer[]) new Object[5];
		System.out.println("&&&&&&&&&&");
	}
}

class A<T> {
	public void test() {
		T[] tt = (T[]) new Object[5];
		System.out.println("*********");
	}
}
