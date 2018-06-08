#java 多线程#
    http://penghb.com

#线程 dump
1. 执行`jps`命令，列出正在运行的java程序的进程ID
    D:\gitrepo\thread-test>jps
    5056 RemoteMavenServer (pid 对应线程名字)
    7264 Launcher
    10644 Jps
    7636
2. 执行`jstack pid`

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

在Thread.java 下的 enum State下

---
#Dump文件中的线程状态
1. Deadlock
2. Runnable
3. Waiting on condition
4. Blocked
5. Waiting for monitor entry 和 in Object.wait()


---
#Thread.java分析
>exit()
    exit( )是由系统调用的，用于线程在真正的退出前进行一些清理的操作。看看里面进行的操作是什么吧，可以看出，里面执行的是group 赋值为null了， 将target引用进行释放，同时释放了threadLocals所占用的资源，等等属性都赋值为null。
sleep()
    这个方法的作用使得当前线程休眠一定的时间，但是这个期间是不释放持有的锁的。这个方法里面首先进行的是休眠时间的判断，然后又是调用本地方法。
join()方法
    join方法是等待该线程执行，直到超时或者终止，可以作为线程通信的一种方式，A线程调用B线程的join（阻塞），等待B完成后再往下执行。 join（）方法中重载了多个方法，但是主要的方法是下面的方法。
interrupt()方法
    中断当前的线程
    阻塞状态:
        阻塞函数,如Thread.sleep、Thread.join、Object.wait等在检查到线程的中断状态的时候，
        会抛出InteruptedExeption, 同时会清除线程的中断状态。
            对于InterruptedException的处理，可以有两种情况：
                外层代码处理异常，直接抛出这个异常即可
                不能抛出这个异常，比如在run()方法内，因为在得到这个异常的同时，线程的中断状态已经被清除了，需要保留线程的中断状态，则需要调用Thread.currentThread().interrupt()
    运行状态:
        将该线程的中断标志设置为 true，仅此而已。被设置中断标志的线程将继续正常运行，不受影响。


---
#终止线程
    首先，一个线程不应该由其他线程来强制中断或停止，而是应该由线程自己自行停止。所以，Thread.stop, Thread.suspend, Thread.resume 都已经被废弃了。而 Thread.interrupt 的作用其实也不是中断线程，而是「通知线程应该中断了」，具体到底中断还是继续运行，应该由被通知的线程自己处理。

```java
@Override
public void run() {
    //“中断”方式终止处于“阻塞状态”的线程。
    try {
        // 执行任务...
    } catch (InterruptedException ie) {  
        // 由于产生InterruptedException异常，退出while(true)循环，线程终止！
    }

    //终止处于“运行状态”的线程
    if (!isInterrupted()) {
        // 执行任务...
    }
}
```

#合理的配置线程池
要想合理的配置线程池，就必须首先分析任务特性，可以从以下几个角度来进行分析：
    1. 任务的性质：CPU密集型任务，IO密集型任务和混合型任务。
    2. 任务的优先级：高，中和低。
    3. 任务的执行时间：长，中和短。
    4. 任务的依赖性：是否依赖其他系统资源，如数据库连接。
任务性质不同的任务可以用不同规模的线程池分开处理。CPU密集型任务配置尽可能少的线程数量，如配置Ncpu+1个线程的线程池。IO密集型任务则由于需要等待IO操作，线程并不是一直在执行任务，则配置尽可能多的线程，如2*Ncpu。混合型的任务，如果可以拆分，则将其拆分成一个CPU密集型任务和一个IO密集型任务，只要这两个任务执行的时间相差不是太大，那么分解后执行的吞吐率要高于串行执行的吞吐率，如果这两个任务执行时间相差太大，则没必要进行分解。
我们可以通过
    `Runtime.getRuntime().availableProcessors()`方法获得当前设备的CPU个数。

优先级不同的任务可以使用优先级队列PriorityBlockingQueue来处理。它可以让优先级高的任务先得到执行，需要注意的是如果一直有优先级高的任务提交到队列里，那么优先级低的任务可能永远不能执行。

执行时间不同的任务可以交给不同规模的线程池来处理，或者也可以使用优先级队列，让执行时间短的任务先执行。

依赖数据库连接池的任务，因为线程提交SQL后需要等待数据库返回结果，如果等待的时间越长CPU空闲时间就越长，那么线程数应该设置越大，这样才能更好的利用CPU。

建议使用有界队列
    有界队列能增加系统的稳定性和预警能力，可以根据需要设大一点，比如几千。有一次我们组使用的后台任务线程池的队列和线程池全满了，不断的抛出抛弃任务的异常，通过排查发现是数据库出现了问题，导致执行SQL变得非常缓慢，因为后台任务线程池里的任务全是需要向数据库查询和插入数据的，所以导致线程池里的工作线程全部阻塞住，任务积压在线程池里。如果当时我们设置成无界队列，线程池的队列就会越来越多，有可能会撑满内存，导致整个系统不可用，而不只是后台任务出现问题。当然我们的系统所有的任务是用的单独的服务器部署的，而我们使用不同规模的线程池跑不同类型的任务，但是出现这样问题时也会影响到其他任务。

#线程池的监控
通过线程池提供的参数进行监控。线程池里有一些属性在监控线程池的时候可以使用
1. taskCount：线程池需要执行的任务数量。
2. completedTaskCount：线程池在运行过程中已完成的任务数量。小于或等于taskCount。
3. largestPoolSize：线程池曾经创建过的最大线程数量。通过这个数据可以知道线程池是否满过。
    如等于线程池的最大大小，则表示线程池曾经满了。
4. getPoolSize:线程池的线程数量。如果线程池不销毁的话，池里的线程不会自动销毁，所以这个大小只增不减。
5. getActiveCount：获取活动的线程数。
通过扩展线程池进行监控。通过继承线程池并重写线程池的beforeExecute，afterExecute和terminated方法，我们可以在任务执行前，执行后和线程池关闭前干一些事情。如监控任务的平均执行时间，最大执行时间和最小执行时间等。这几个方法在线程池里是空方法。

#org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor