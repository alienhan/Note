---
title: java 基本数据类型-类分析
date: 2016/10/9
categories:
- java源码分析
tags:
- java源码分析
- Integer源码分析
- equals和 == 的区别源码分析
- String StringBuilder StringBuffer源码分析
- final修饰符
---

### equals和 == 的区别 /源码分析
1. 基本数据类型(原始数据类型)              `byte,short,char,int,long,float,double,boolean`  
他们之间的比较，应用双等号 == ,比较的是他们的值。

2. 复合数据类型(类)
 1. ==  
  比较的是他们在内存中的存放地址，所以，除非是同一个new出来的对象，他们的比较后的结果为true，否则比较后结果为false
 2. equals  
    - JAVA当中所有的类都是继承于Object这个基类的，在Object中的基类中定义了一个equals的方法，这个方法的初始行为是比较对象的内存地 址，但在一些类库当中这个方法被覆盖掉了，如String,Integer,Date在这些类当中equals有其自身的实现，而不再是比较类在堆内存中的存放地址了。
    - 对于复合数据类型之间进行equals比较，在没有覆写equals方法的情况下，他们之间的比较还是基于他们在内存中的存放位置的地址值的，因为Object的equals方法也是用双等号（==）进行比较的，所以比较后的结果跟双等号（==）的结果相同。

---
### Integer源码分析

#### IntegerCache源码分析

封装一个私有的静态内部类  
```java
/**
     * Cache to support the object identity semantics of autoboxing for values between
     * -128 and 127 (inclusive) as required by JLS.
     *
     * The cache is initialized on first usage.  The size of the cache
     * may be controlled by the {@code -XX:AutoBoxCacheMax=<size>} option.
     * During VM initialization, java.lang.Integer.IntegerCache.high property
     * may be set and saved in the private system properties in the
     * sun.misc.VM class.
     */
private static class IntegerCache {
    static final int low = -128;
    static final int high;
    static final Integer cache[];

    static {
        // high value may be configured by property
        int h = 127;
        String integerCacheHighPropValue =
            sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
        if (integerCacheHighPropValue != null) {
            try {
                int i = parseInt(integerCacheHighPropValue);
                i = Math.max(i, 127);
                // Maximum array size is Integer.MAX_VALUE
                h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
            } catch( NumberFormatException nfe) {
                // If the property cannot be parsed into an int, ignore it.
            }
        }
        high = h;

        cache = new Integer[(high - low) + 1];
        int j = low;
        for(int k = 0; k < cache.length; k++)
            cache[k] = new Integer(j++);

        // range [-128, 127] must be interned (JLS7 5.1.7)
        assert IntegerCache.high >= 127;
    }

    private IntegerCache() {}
}
```
正如注释所说IntegerCache缓存的最大值,先从vm参数中获取,若没有则默认为127

#### Integer 重写equals分析
```java
public boolean equals(Object obj) {
    if (obj instanceof Integer) {
        return value == ((Integer)obj).intValue();
    }
    return false;
}
```
内部实现也是使用了==的操作.也就是说也是比较内存地址

#### highestOneBit
Integer.highestOneBit(i)  
- 这个函数的作用是取 i 这个数的二进制形式最左边的最高一位且高位后面全部补零，最后返回int型的结果。

---
### String 源码分析
  - 一旦 String 对象在内存(堆)中被创建出来，就无法被修改。
    - 特别要注意的是，String 类的所有方法都没有改变字符串本身的值，都是返回了一个新的对象。
  - 当需要一个可修改的字符串，应该使用 StringBuffer 或者
    StringBuilder。
    - 否则会有大量时间浪费在垃圾回收上，因为每次试图修改都有新的String 对象被创建出来。
  - 当需要创建一个字符串，你可以使用双引号的方式，如果你需要在堆中创建一个新的对象，你可以选择构造函数的方式。
    - 双引号方式在栈的常量池中新建字符串
    - 构造函数在堆中创建一个新的对象

#### final 修饰符： 
- 修饰类:当用final修饰一个类时，表明这个类不能被继承。也就是说，String类是不能被继承的，  
- 修饰方法：把方法锁定，以防任何继承类修改它的含义。  
- 修饰变量：
  - 修饰基本数据类型变量，则其数值一旦在初始化之后便不能更改；
  - 如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。

String类通过final修饰，不可被继承，同时String底层的字符数组也是被final修饰的，char属于基本数据类型，一旦被赋值之后也是不能被修改的，所以String是不可变的。

#### 成员变量及方法

成员变量
```java
private final char value[];//final字符数组，一旦赋值，不可更改
private int hash;  //缓存String的 hash Code，默认值为 0
private static final ObjectStreamField[] serialPersistentFields =new ObjectStreamField[0];//存储对象的序列化信息
```

#### String对“+”的重载
  "+"跟"+="是Java中仅有的两个重载操作符，除此之外，Java不支持其它的任何重载操作符  

查看编译文件理解 + 的原理  
```java
public class TestString {
   public static void main(String[] args) {
       String abc = "abc";
       String d = abc + "dd";
   }
}
```

对应编译.class文件
```
Code:
     stack=2, locals=3, args_size=1
        0: ldc           #2                  // String abc
        2: astore_1
        3: new           #3                  // class java/lang/StringBuilder
        6: dup
        7: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
       10: aload_1
       11: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
       14: ldc           #6                  // String dd
       16: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
       19: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
       22: astore_2
       23: return
     LineNumberTable:
       line 6: 0
       line 7: 3
       line 8: 23
```
看编译文件可以看出 + 其实 就是使用StringBuilder的append方法
