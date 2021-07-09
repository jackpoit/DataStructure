package heapandpriorityQueue;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
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
