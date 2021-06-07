package stack;

public class ArrayStack<E> implements Stack<E>{
	private Array<E> array;  //不加private 有风险
	public ArrayStack(){
		array=new Array<>();
	}
	public ArrayStack(int capacity){
		array=new Array<>(capacity);
	}

	@Override
	public int getSize() {
		return array.getSize();
	}

	public int getCapacity(){
		return array.getCapacity();
	}

	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}

	@Override
	public void push(E e) {
		array.addLast(e);
	}

	@Override
	public E pop() {
		return array.removeLast();
	}

	@Override
	public E peek() {
		return array.getLast();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Stack: ");
		res.append('[');
		for(int i = 0 ; i < array.getSize() ; i ++){
			res.append(array.get(i));
			if(i != array.getSize() - 1)
				res.append(", ");
		}
		res.append("] top");
		return res.toString();
	}
}
