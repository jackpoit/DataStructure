package Queue;

import java.util.Arrays;

public class LoopQueue<E> implements Queue<E>{
    private E[] data;
    private int front,tail;
    private int size;
    public LoopQueue(){
        this(10);
    }
    public LoopQueue(int capacity){
        this.data=(E[]) new Object[capacity+1];
        front=0;
        tail=0;
        size=0;
    }

    @Override
    public int getSize() {
        return size;
    }
    public int getCapacity(){
        return data.length-1;
    }
    @Override
    public boolean isEmpty(){
        return front==tail;
    }

    @Override
    public void enqueue(E e) {
        if ((tail+1)%data.length==front){
            resize(getCapacity()*2);
        }
        //tail+1一定要余一个data.length  不然会一直在data的11个数组里循环录入
        //因为如果front=0，data.length=11  随着数据填入10个到tail=10时，本来数组已经满了 需要扩容
        //但tail+1=11!=front(0) 就不会resize,接着第11个数据就会放在data[10]这个位置 而data10本来就是空出来的（循环队列设计的空的空间）
        //然后tail变成0， 上面的tail+1=1!=front(0) 就会一直在这11个位置重复录入 size++ 但capacity不变
        data[tail]=e;
        tail=(tail+1)%data.length;
        //tail++; 不行，要防止越界
        size++;
    }

    public void resize(int capacity){
        E[] newArr=(E[]) new Object[capacity+1];
//        for (int i=front;i!=tail;i=(i+1)%data.length){
//            newArr[i>=front?i-front:i+data.length-front]=data[i];
//        }
        for (int i=0;i<size;i++){
            newArr[i]=data[(front+i)%data.length];
        }
        data=newArr;
        front=0;
        tail=size;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("cannot dequeue from empty queue.");
        }
        E ret=data[front];
        data[front]=null;
        front=(front+1)%data.length;
        size--;
        if (size==getCapacity()/4&&getCapacity()/2!=0){
            resize(getCapacity()/2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        res.append(String.format("Queue:size=%d,capacity=%d\n",size,getCapacity()));
        res.append("front[");
//        for (int i=front;i!=tail;i=(i+1)%data.length){
//            res.append(data[i]);
//            if (i!=tail-1){
//                res.append(",");
//            }
//        }
        for (int i=0;i<size;i++){
            res.append(data[(front+i)%data.length]);
            if (i!=size-1){
                res.append(",");
            }
        }
        res.append("]tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> loopQueue=new LoopQueue<Integer>();
        for (int i=0;i<20;i++){
            loopQueue.enqueue(i);
            System.out.println(loopQueue);
            if (i%3==2){
                loopQueue.dequeue();
                System.out.println(loopQueue);
            }

        }
    }
}
