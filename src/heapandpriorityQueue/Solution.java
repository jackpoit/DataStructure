package heapandpriorityQueue;


import java.util.*;
import java.util.PriorityQueue;


public class Solution {
	public int[] topKFrequent(int[] nums, int k) {
		HashMap<Integer,Integer> map=new HashMap<>();
		for (int num:nums){
			if (map.containsKey(num)){
				map.put(num,map.get(num)+1);
			}else {
				map.put(num,1);
			}
		}
		PriorityQueue<Integer> queue=new PriorityQueue<>(
				Comparator.comparing(map::get)
		);

		//这里有3种写法  1.直接lambda表达式  (o1,o2)->map.get(o1)-map.get(o1)   省略了参数类型、方法体 和return
		//2. 用Comparator.comparing() 里面传一个键提取器  就是对要进行比较的o1 o2先执行括号中的操作 再compareTo
		// 键提取器有2种写法  1. 用lambda表达式  a->map.get(a)  就是 o1 o2都先应用map.get(o1/o2) 再compareTo
		// 	这种写法可以用方法引用简写 成 map::get  就是 对象::方法  lambda参数(这里就是o1或者o2)作为显示参数传入

		//如果是需要o1.getName() 因为getName不需要参数
		// 但是lambda参数(o1,o2)作为隐式参数(就是调用者) 就要用类名::非静态方法(这样参数就可以作为调用者了)
		//如  Employee类的比较   emp->emp.getName()  就可以简写成Employee::getName

		for (int key:map.keySet()){
			if (queue.size()<k){
				queue.add(key);
			}else if (map.get(key)>map.get(queue.peek())){
				queue.remove();
				queue.add(key);
			}
		}

		int[] res=new int[k];
		for (int i=0;i<k;i++){
			res[i]=queue.remove();
		}
		return res;
	}

	public static void main(String[] args) {

		int[] nums = {1, 1, 1, 2, 2, 3};
		int k = 2;
		System.out.println((Arrays.toString((new Solution()).topKFrequent(nums, k))));
	}
}

//优先队列与TreeSet的区别
//优先队列允许重复元素(父元素大于等于子元素)   内部无序  应用 需要求最大或最小值的场景(就是本题 在入队时把优先级最小的要剔除）
//(内部是最大/最小堆基于数组实现的） (头和尾很好找)
//O(1) for getting min/max heap    O(logn) for heap to offer;      O(logn) for remove


//TreeSet去重(基于二叉树）   内部有序   应用需要找出集合中大于等于某个数值的最小值(ceiling/floor)
// 但找最大最小元素不好找(内部是树结构 头和尾不太好找)

//The reason why we want to use tree set/map instead of heap is
//because the update index(which is not min/max) function in heap is difficult,
// and we should keep track of a hashmap to record the value and index.
//
//优先队列的优势：get max/min, peek() is O(1)
//
//TreeMap/Set的优势: update or delete 任意number是O(logn)
//
//If some problems can be solved by using priorityQueue, it must be solved by using treeset/map
//