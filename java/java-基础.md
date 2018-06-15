---
title: java 基础知识
date:
categories:
- java基础
tags:
- java 基础知识
---

### Arraylist 、LinkedList、 vector的区别
List接口下一共实现了三个类：ArrayList，Vector，LinkedList  

LinkedList ：  
主要用在保持数据的插入顺序  

ArrayList和Vector都是用数组实现的，主要有这么三个区别：
1. Vector是多线程安全的，而ArrayList不是，  
   这个可以从源码中看出，Vector类中的方法很多有synchronized进行修饰，  
   这样就导致了Vector在效率上无法与ArrayList相比；  
2. 两个都是采用的线性连续空间存储元素，但是当空间不足的时候，两个类的增加方式是不同的，  
   Vector增加原来空间的一倍，ArrayList增加原来空间的50%。  
3. Vector可以设置增长因子，而ArrayList不可以。 

### HashMap Hashtable 区别
HashMap put元素：  
 当我们往hashmap中put元素的时候，先根据key的hash值得到这个元素在数组中的位置（即下标），  
 然后就可以把这个元素放到对应的位置中了。如果这个元素所在的位子上已经存放有其他元素了，  
 那么在同一个位子上的元素将以链表的形式存放，新加入的放在链头，最先加入的放在链尾。  

***区别***  
第一，继承不同。  
public class Hashtable extends Dictionary implements Map
public class HashMap  extends AbstractMap implements Map

第二  
Hashtable 中的方法是同步的，而HashMap中的方法在缺省情况下是非同步的。  
在多线程并发的环境下，可以直接使用Hashtable，但是要使用HashMap的话就要自己增加同步处理了。  

第三  
Hashtable 中，key和value都不允许出现null值。  
在 HashMap 中，null可以作为键，这样的键只有一个；可以有一个或多个键所对应的值为null。当   
get()方法返回null值时，即可以表示 HashMap中没有该键，也可以表示该键所对应的值为null。因此，  
在HashMap中不能由get()方法来判断HashMap中是否存在某个键， 而应该用containsKey()方法来判断。  

第四  
两个遍历方式的内部实现上不同。  
Hashtable 、HashMap 都使用了 Iterator 。而由于历史原因，Hashtable还使用了Enumeration的方式 。  

第五  
哈希值的使用不同，HashTable直接使用对象的hashCode。而HashMap重新计算hash值。  

第六  
Hashtable 和HashMap 它们两个内部实现方式的数组的初始大小和扩容的方式。  
HashTable中hash数组默认大小是11，增加的方式是 old*2+1。HashMap中hash数组的默认大小是16，  
而且一定是2的指数。   

### String StringBuffer StringBuilder 区别
String 字符串常量  
StringBuffer 字符串变量（线程安全）  
StringBuilder 字符串变量（非线程安全）  

1. 三者在执行速度方面的比较： StringBuilder >  StringBuffer  >  String  
原因：  
	- 当用String操作字符串时，实际上是在不断的创建新的对象，而原来的对象就会变为垃圾被ＧＣ回收掉，  
	因此执行效率降低  

2. 区别：  
	- StringBuilder ：线程非安全的  
	- StringBuffer ：线程安全的  

### 对象克隆
Java中所有的对象都是保存在堆中，而堆是供全局共享的。也就是说，  
如果同一个Java程序的不同方法，只要能拿到某个对象的引用，  

引用者就可以随意的修改对象的内部数据（前提是这个对象的内部数据通过get/set方法曝露出来）。  
有的时候，我们编写的代码想让调用者只获得该对象的一个拷贝  
（也就是一个内容完全相同的对象，但是在内存中存在两个这样的对象）  

继承Cloneable  
```java  
public class ByteArrayEntity extends AbstractHttpEntity implements Cloneable {
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

//测试
User u1 = new User("Kent", "123456", new Date());
User u2 = u1;
User u3 = (User) u1.clone();

System.out.println(u1 == u2);		// true
System.out.println(u1.equals(u2));	// true

System.out.println(u1 == u3);		// false
System.out.println(u1.equals(u3));	// true
```

### error
```java
java.io.IOException: failed to decrypt safe contents entry:   javax.crypto.BadPaddingException:  
Given final block not properly padded  

Sometimes this error is symptomatic of using an incorrect   password for the p12 key.  
```

###  list java.util.List 

list使用  
```java
list<class>  
List<String> l = new ArrayList<String>();  
List<String> l2 = new LinkedList<String>();   
```

List接口的常用实现类有ArrayList和LinkedList，  
在使用List集合时，通常情况下声明为List类型，  
实例化时根据实际情况的需要，实例化为ArrayList或LinkedList，  
例如：  
```java
List<String> l = new ArrayList<String>();
// 利用ArrayList类实例化List集合
List<String> l2 = new LinkedList<String>();
// 利用LinkedList类实例化List集合
```

### java泛型  
http://www.w3cschool.cc/

泛型（Generic type 或者 generics）是对   
Java 语言的类型系统的一种扩展，以支持创建可以按类型进行参数化的类。  
  
example:
```java
public void write(Integer i, Integer[] ia);
public void write(Double  d, Double[] da);
//范型版本为
public <T> void write(T t, T[] ta);
```

### 实参个数可变

定义实参个数可变的方法:  
- 只要在一个形参的“类型”与“参数名”之间加上三个  
连续的“.”（即“...”，英文里的句中省略号），就可以让它和不确定个实参相匹配。而一个带有这样的形参的方法，就是一个实参个数可变的方法。  

一个实参个数可变的方法  
```java
private static int sumUp(int... values) {}

Object... params
new Object[]{para1, para2, para3 }等同于这样的形式。

private static int sumUp(int... values) {
    int sum = 0;
    for (int i = 0; i < values.length; i++) {
        sum += values[i];
    }
    return sum;
}
public static void generalUse(int []args){  
        for (int i = 0;i < args.length ; i++ ){  
                System.out.println(args[i]);  
        }  
    }   
public static void newBehaviour(int... args){  
        for (int i = 0;i < args.length ; i++ ){  
                System.out.println(args[i]);  
        }  
    } 
```
