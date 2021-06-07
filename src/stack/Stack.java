package stack;

public interface Stack<E> {
	int getSize();
	boolean isEmpty();
	void push(E e);
	E pop();	//取出栈顶的元素
	E peek();	//get栈顶的元素  也可以用topic
}
