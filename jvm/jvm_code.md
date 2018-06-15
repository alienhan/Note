---
title: jvm 编程
date:
categories:
- jvm
tags:
- jvm 
- jvm 编程
- jvm code
---

### JAVA虚拟机关闭钩子(Shutdown Hook)
JDK提供了Java.Runtime.addShutdownHook(Thread hook)方法，可以注册一个JVM关闭的钩子，这个钩子可以在一下几种场景中被调用： 
1. 程序正常退出
2. 使用System.exit()
3. 终端使用Ctrl+C触发的中断
4. 系统关闭
5. OutOfMemory宕机
6. 使用Kill pid命令干掉进程（注：在使用kill -9 pid时，是不会被调用的）


### Runtime
Runtime 封装着java程序的运行时环境。通过Runtime实例，java应用能够与其运行的环境连接。Runtime在jvm中保持一个单例，  
所以不能通过Runtime类的构造函数。只能通过Runtime.getRuntime()来获的当前Runtime的一个实例。  
获得Runtime实例后，就可以通过Runtime的exec()方法在当前jvm进程外启动其他进程了。  
很常见的一个应用就是，启动浏览器进程来显示一个程序的帮助页面。   
```java
	Runtime rt = Runtime.getRuntime();
	Process child = rt.exec();
	writer = new OutputStreamWriter(child.getOutputStream(), "utf8");// 控制台的输入信息作为输出流
	writer.write(sb.toString());
```

#### 在Runtime类中存在四个exec()重载方法.   
```java
	public Process exec(String command);  
	public Process exec(String [] cmdArray);  
	public Process exec(String command, String [] envp);  
	public Process exec(String [] cmdArray, String [] envp);  
```
