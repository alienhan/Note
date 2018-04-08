#java 多线程#


#线程 dump
1. 执行jps命令，列出正在运行的java程序的进程ID
    ```
    D:\gitrepo\thread-test>jps
    5056 RemoteMavenServer (pid 对应线程名字)
    7264 Launcher
    10644 Jps
    7636
    ```
2. 执行jstack pid

---

#线程状态
1. new: 创建了,还没有启动
2. runable: 正在Java虚拟机下跑任务的线程的状态。在RUNNABLE状态下的线程可能
会处于等待状态， 因为它正在等待一些系统资源的释放，比如IO
3. BLOCKED: 阻塞状态，等待锁的释放，比如线程A进入了一个`synchronized`方法，线程B也想进入这个方法，但是这个方法的锁已经被线程A获取了，这个时候线程B就处于BLOCKED状态
    分析:线程处于BLOCKED状态，这个时候可以分析一下是不是lock加锁的时候忘记释放了，或者释放的时机不对。导致另外的线程一直处于BLOCKED状态。
4. WAITING: 等待状态，处于等待状态的线程是由于执行了3个方法中的任意方法。
     1. Object的wait方法，并且没有使用timeout参数; 
     2. Thread的join方法，没有使用timeout参数 
     3. LockSupport的park方法。 处于waiting状态的线程会等待另外一个线程处理特殊的行为。 再举个例子，如果一个线程调用了一个对象的wait方法，那么这个线程就会处于waiting状态直到另外一个线程调用这个对象的notify或者notifyAll方法后才会解除这个状态
5. TIMED_WAITING: 有等待时间的等待状态，比如调用了以下几个方法中的任意方法，
并且指定了等待时间，线程就会处于这个状态。 
    1. Thread.sleep方法 
    2. Object的wait方法，带有时间 
    3. Thread.join方法，带有时间 
    4. LockSupport的parkNanos方法，带有时间 
    5. LockSupport的parkUntil方法，带有时间
6. TERMINATED: 线程中止的状态，这个线程已经完整地执行了它的任务(jstack 看不到线程了)

---















