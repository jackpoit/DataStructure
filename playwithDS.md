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

