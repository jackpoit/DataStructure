package ds01;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
//		int[] arrs=new int[]{23,45,17,11,13,89,72,26,3,17,11,13};
		int[] arrs = new int[1000];
		for (int i = 0; i < 1000; i++) {
			arrs[i] = (int) (Math.random() * 1000);
		}
//		int[] arrs=new int[]{7, 1, 3, 5, 13, 9, 3, 6, 11};
		quickSort(arrs);
		System.out.println(Arrays.toString(arrs));
		for (int i = 0; i < arrs.length - 1; i++) {
			if (arrs[i] > arrs[i + 1]) {
				System.out.println(false);
				break;
			}
		}

	}

	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	private static int partition(int[] arr, int left, int right) {
		int i = left;
		int j = right;
		int pivot = arr[left];
		while (i < j) {
			while (arr[j] > pivot && j > i) {
				j--;
			}
			if (j > i) {
				arr[i] = arr[j];
				i++;
			}
			while (arr[i] < pivot && i < j) {
				i++;
			}
			if (i < j) {
				arr[j] = arr[i];
				j--;
			}
		}
		arr[i] = pivot;
		return i;

	}


	//递归 根据传入的左右下标 把数组中元素重排序
	private static void quickSort(int[] arr, int left, int right) {
		if (left < right) {
			int index = partition(arr, left, right);
			quickSort(arr, left, index - 1);
			quickSort(arr, index + 1, right);
		}

	}
}
