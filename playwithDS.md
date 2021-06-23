**2.Array**

1. size  capacity

   add   addLast   addFirst    find 

2. remove 时 最好返回删除的值    removeFirst  removeLast  removeElement

3. 泛型  不能放基本类型 只能放类对象       

   autoBoxing    Interge --int

   loitering objects!=memory leak

Q:泛型数组和object数组区别在哪

4. 描述一个算法的运行时间和输入数据规模之间的关系
   O(1)       运行时间与输入数据规模没关系 如 addLast 
               	不管数据什么样 之间在size位置放
   O(n) 
   O(n^2)
   渐进时间复杂度    描述n趋于无穷时的情况
   
   通常关注的是最坏的情况
   
   
   
   add()   O(n/2)-->O(n)
   
5. 均摊复杂度 

   如计算 addLast()   时resize()不会每次触发

   当我们计算一个相对比较耗时的操作 如果我们能保证它不会每次都触发，那么它的时间可以相对的分到其他操作中来的

   

   复杂度震荡  

    size=capacity时 addLast()  removeLast() 重复调用 一直触发resize

   解决方式 Lazy		

​	

**Stack**

1.线性结构  

​	入栈 出栈  栈顶：只能从这一端放入 取出

​	后进先出 Last in First Out(LIFO)

​	Undo

​	程序调用的系统栈    



2. 栈的实现

   通过之前编写的工具类 Array来实现  （全程通过泛型<E>）

   但是因为要实现 Stack（push   pop  peek  getsize   isEmpty)

   可以编写一个Stack 接口，然后定义一个实现类ArrayStack来实现Stack,

   ArrayStack 有个private Array<e> array 的属性，然后通过重写Stack来实现方法，不必担心 array的其他方法泄露，因为我们通过ArrayStack类只能调用接口li里定义的方法 （array是私有的），其他方法没有调用权限。

   这就是封装的好处，从用户角度看 ，支持这些操作就好，具体的底层实现，用户不关心




3.括号鉴别    

​	自己编写的工具类用leetcode验证



**Queue**

1.线性结构

​	先进先出  FIFO



​	LoopQueue

​	弥补 dequeue 复杂度O(N)-->O(1)  (均摊，因为可能会触发resize)

​	注意： **实现LoopQueue的代码时，涉及到tail front的判断和 重新赋值时**

​			    **一定要考虑%data.length的事情！！！**



**LinkedList**

1. 节点Node

   head      prev     dummyHead 

   
   
   LinkedListStack  从head进 从head出  保证入队出队操作 O(1)的复杂度
   
   LinkedListQueue	
   
   如果用之前的链表 ,只能一端进一端出 入队出队肯定有一个操作是O(n)的复杂度,
   
   因此引入一个 tail节点记录尾部 ，这样入队出队都变为O(1)复杂度了
   
   注意 入队 出队 如果链表变成空或从空开始要注意维护 head 和tail.
   
   
   
   **递归**
   
   1、2部分  求解最基本的问题 
   
   ​					把原问题转化成更小的问题 ：注意递归函数的宏观语义	
   
   ​																		把递归函数当成一个子函数 
   
   ​																		只是用来完成特定功能的
   
   
   
   链表：双向链表(head tail next prev)，
   
   ​			循环链表(尾部指向dummyHead，不再指向null)
   
   ​			数组链表  
   
   































