
### HashMap source read
参考:
http://penghb.com/2017/10/15/java/hashMap/

hashtable区别
    不同步
    允许使用null

1.8改进
    数据存储结构引进了`红黑树`，使得查询更加的快捷.

---
### ConcurrentHashMap source read
参考:
http://penghb.com/2017/10/27/java/concurrentHashMap/

JDK1.8改进
    1. `取消segments字段`，（Segment虽保留，但已经简化属性，仅仅是为了兼容旧版本。）
    直接采用transient volatile HashEntry table保存数据，采用table数组元素作为锁，从而实现了对每一行数据进行加锁，进一步减少并发冲突的概率。
    2. 将原先table数组＋单向链表的数据结构，变更为table数组＋单向链表＋红黑树的结构。（这点和HashMap是一样的）



---
### Threadlocal source read
    该类提供线程本地变量。这些变量与一般的变量不同，每个线程访问一个线程（通过get或set方法）有自己独立的变量初始化副本。
参考:
http://penghb.com/2017/10/18/java/threadLocal/

sadf
