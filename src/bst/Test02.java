package bst;

import java.nio.channels.FileChannel;
import java.util.Arrays;

public class Test02 {
	public static void main(String[] args) {
		/*
		 * 第一段代码
		 */

			A<Integer> a = new A<>();
			a.test();



		/*
		 * 第二段代码
		 */
		Integer[] tt= (Integer[]) new Object[2];
		//这一步实际上是下面的2步操作  先new一个object数组再把mm转成tt
		//不是继承关系当然不能强转(都是数组类)
		//只能一个一个强转 进数组
		Object[]mm =new Object[2];
		Integer[] tt1 = (Integer[]) mm;

		System.out.println("&&&&&&&&&&");
	}
}

class A<T> {
	public void test() {
		T[] tt = (T[]) new Object[5];
		System.out.println("*********");
		//这个可以转是因为 在编译期 先是new了一个object数组
		//然后放入的数都是Integer类型的 就会自动把其中的类型转成Integer型
		//然后放到Integer数组中  (相当于帮我们做了上面一个个强转进数组的操作)

		// 泛型只会在编译器有效来约束类型的一致，一但到了程序的运行期，泛型自动会提升为Object，又因为你容器放的类型相同，所以运行期的强转就不会存在Class cast exception了

	}
}
	class B<E extends Comparable<E>>{
		public E[] findFloorAndCeilNR(E e) {

			E[] res=(E[]) new Object[2];
			res[0]=e;
			res[1]=e;
			return res;
			//这里不能转 因为有向上泛型通配符的限制
			//本来是自动转为E类型的 但是E类型是Comparable 编译期会默认建Comparable的数组
			//comparaale是个父类接口 里面可以放各种类型
			//底层无法判断内部放入的到底是否是相同类型的(只能知道都是Comparable类型的)
			//所以Object在强转时就可能会出现类型转化或不安全的问题，
			//官方为了避免这种问题产生，所以就直接抛出异常了。


			//这个问题就是由泛型通配符上限引起的类型安全问题，没有上限时可以强转，
			//有了上限通配符后因为T类型会默认提升为上限类型，所以Object在强转时就可能会出现类型转化或不安全的问题，
			// 官方为了避免这种问题产生，所以就直接抛出异常了。


//			public <T extends Comparable> T[] maxMin(T...a){		Comparable不加<> 泛型 兼容性
//
//			}
		}
	}

