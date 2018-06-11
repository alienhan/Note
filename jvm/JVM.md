---
title: jvm
date:
categories:
- jvm
tags:
- jvm 加载
- jvm 执行
- jvm GC
- jvm 内存分配
- jvm 命令
---

### 加载
1. 编译成class文件(.class 文件结构)
    查看字节码: 
        `javac 编译`
        `javap -verbose Class`
2. 类(Class对象)加载到虚拟机内存过程(
    所谓的类加载就是把.class文件加载到内存成为Class对象) 
     
    loading->linking(verification->preparation->resolution)  
    ->initialization->using->unloading  

    preparation(准备): 类变量分配内存,设置初始值  
    resolution(解析):虚拟机将`常量池内`的符号引用替换成`直接引用`的过程.  
    initialization:执行<clinit>()方法的过程
      ``<clinit>``()方法是编译器自动收集类变量,静态语句{}合成的.  

3. 类加载器(双亲委派机制,树状结构):  
      1. 启动类加载器(bootstrap ClassLoader c++实现)  
      2. 扩展类加载器(Extension ClassLoader)  
      3. 应用程序类加载器(Application ClassLoader)  
      4. 自定义类加载器(User ClassLoader)  
      5. 过程 : 类加载器收到一个类加载请求,先请求委派给父加载器完成加载  
      6. OSGi 非树状结构   
4. 初始化前提:
      1. 遇到 `new getstatic putstatic invokestatic`这4条字节码(java 代码场景 使用new 实例化对象,读取或设置一个类的静态字段(final 除外))  
      2. 反射调用时  
      3. 初始化类的时候要先初始化父类  
      4. main  
      5. 动态语言支持  

### 执行
main->启动线程->调用方法->线程结束  

运行时栈帧结构(一个栈帧对应一个方法)  
  -  局部变量表  
  -  操作数栈  
  -  动态链接  
  -  方法返回地址  
  -  附加信息  

### 垃圾收集 Garbage Collection
#### GC目的:  
  + 那些内存需要回收  
  + 什么时候回收  
  + 如何回收 
     
#### 判断对象死亡  
  1. 引用计数算法(java中不用)  
  2. 可达性分析算法  
      GC Roots:  
        - 虚拟机栈中本地变量表中的引用对象(reference)  
        - 方法区中类静态属性引用的对象  
        - 方法区中常量引用的对象  
        - 本地方法栈(JNI)引用对象(native)  
  3. 引用种类  
      强 strong 1,弱 weak 3,软 soft 2,虚 phantom 4  

#### 垃圾收集算法  
  + 种类
      标记-清除算法 Mark-Sweep  
      标记-整理算法 Mark-Compact  
      复制算法  
      分代收集算法  
      - 新生代 young generation-->复制算法 Minor GC  
      - 老生代 tenured generation-->标记 清理\整理 算法
      - Major GC  
  + 算法实现  
      - 枚举根节点->停顿  
      - 安全点/区域  
        - 发生GC时,要求所有线程处在安全域内  

#### 垃圾收集器  
  + 并行:多条GC线程同时工作,用户线程处于等待状态  
  + 并发:用户线程与GC线程同时执行(但不一定是并行的,可能会交替执行)
  + serial(串行) 暂停其他所有工作线程  
    -  serial new 复制算法  `新生代`  
    -  serial old 标记-整理算法 `老年代`  
  + parnew  `新生代`  
      多线程版serial  
  + parallel(并行)  
      - 目标:达到一个可控的吞吐量  
        -  吞吐量= 用户代码运行时间/(用户代码运行时间 + GC时间)  
      - parallel scavenge(打扫) `新生代`  
      - parallel old `老年代`  
  + CMS concunrrent mark sweep(打扫) `老年代`  
      目标:获取最短回收停顿时间  
  + G1 garbage first  
      - 其他收集器区别: 将整个java堆划分成多个大小相等的区 域(Region)  
      - region包含有 新/老代  

### 内存分配
+ young generation
    - Eden space 8(比例)  
        当Eden区内存不够的时候就会触发MinorGC，对新生代区进行一次垃圾回收。
    - survivor space 2  
      - form space 1  
          上一次GC的幸存者，作为这一次GC的被扫描者。  
      - to space 1  
          保留了一次MinorGC过程中的幸存者。  
    - gc:MinorGC
        MinorGC的过程：  
        MinorGC采用复制算法。首先，把Eden和ServivorFrom区域中存活的对象复制到ServicorTo区域（如果有对象的年龄以及达到了老年的标准，则赋值到老年代区），同时把这些对象的年龄+1（如果ServicorTo不够位置了就放到老年区）；  
        然后，清空Eden和ServicorFrom中的对象；最后，ServicorTo和ServicorFrom互换，原ServicorTo成为下一次GC时的ServicorFrom区。
+ tenured generation
    - gc:MajorGC
    - 进入条件  
        survivor对象每经历一次GC,年龄+1,默认年龄15时,对象进入老年代  
        大对象(很长的字符串/数组)直接进入老年代  
+ permanent generation
    PermGen PermGen space的全称是Permanent Generation space,是指内存的永久保存区域  
    主要存放Class和Meta（元数据）的信息,Class在被加载的时候被放入永久区域.它和存放实例的区域不同,GC不会在主程序运行期对永久区域进行清理。所这也导致了永久代的区域会随着加载的Class的增多而胀满，最终抛出OOM异常。  
    在Java8中，永久代已经被移除，被一个称为“元数据区”（元空间）的区域所取代。  
    元空间的本质和永久代类似，都是对JVM规范中方法区的实现。不过元空间与永久代之间最大的区别在于：元空间并不在虚拟机中，而是使用本地内存。因此，默认情况下，元空间的大小仅受本地内存限制。类的元数据放入 native memory, 字符串池和类的静态变量放入java堆中. 这样可以加载多少类的元数据就不再由MaxPermSize控制, 而由系统的实际可用空间来控制.

### 命令 调试
#### jps
+ jps jvm process status  
    - 列出正在运行的虚拟机进程
