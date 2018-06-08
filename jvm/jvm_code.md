

#JAVA虚拟机关闭钩子(Shutdown Hook)
JDK提供了Java.Runtime.addShutdownHook(Thread hook)方法，可以注册一个JVM关闭的钩子，这个钩子可以在一下几种场景中被调用：
    1. 程序正常退出
    2. 使用System.exit()
    3. 终端使用Ctrl+C触发的中断
    4. 系统关闭
    5. OutOfMemory宕机
    6. 使用Kill pid命令干掉进程（注：在使用kill -9 pid时，是不会被调用的）