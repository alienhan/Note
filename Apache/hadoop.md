---
title: hadoop
date:
categories:
- hadoop
tags:
- hadoop 启动
- hadoop shell
- mapreduce
---

### 安装
- 参考:  
    http://www.cnblogs.com/wuxun1997/p/6847950.html  
- windows 需要文件  
    https://github.com/steveloughran/winutils  
    替换bin下的文件,system32下放入hadoop.dll

### hadoop 启动

1. 先启动zookeeper

2. 启动Hadoop集群需要启动HDFS集群和Map/Reduce集群。
    1. 格式化一个新的分布式文件系统：(只能格式化一次,多次会造成put时sid错误.)  
      ```  hadoop namenode -format ```
    2. 在分配的NameNode上，运行下面的命令启动HDFS：  
      ```  sbin>start-all.cmd ```
    3. 检验
    ```
       * sbin>jps
            4944 DataNode
            5860 NodeManager
            3532 Jps
            7852 NameNode
            7932 ResourceManager
    ```
    
3. 监听端口  
  -  `与外部程序交互端口`  
    http://localhost:9000/ 
  -   web 页面访问端口欧  
    http://localhost:50070/ `看hdfs文件`  
    http://localhost:8088 `mapreduce任务`  

### hadoop shell

#### put
使用方法：hadoop fs -put <localsrc> ... <dst>  
- 从本地文件系统中复制单个或多个源路径到目标文件系统。也支持从标准输入中读取输入写入目标文件系统。  
```
    * hadoop fs -put localfile /user/hadoop/hadoopfile  
    * hadoop fs -put localfile1 localfile2  
     /user/hadoop/hadoopdir  
    * hadoop fs -put localfile  
     hdfs://host:port/hadoop/hadoopfile
    * hadoop fs -put - hdfs://host:port/hadoop/hadoopfile  
```
从标准输入中读取输入。

返回值：  
成功返回0，失败返回-1。(我put的时候没有返回值?)    

put 的hadoop 的位置的路径下没有其他文件或目录时,直接替换这个路径,而不是在这个路径下新建文集   

#### mkdir  
使用方法：hadoop fs -mkdir <paths>  
-  接受路径指定的uri作为参数，创建这些目录。其行为类似于Unix  mkdir -p，它会创建路径中的各级父目录。  
  示例：  
  ```
      * hadoop fs -mkdir /user/hadoop/dir1 /user/hadoop/dir2
      * hadoop fs -mkdir hdfs://host1:port1/user/hadoop/dir hdfs://host2:port2/user/hadoop/dir
  ```
  返回值：  
  成功返回0，失败返回-1。

mkdir多路径的时候(/user/hadoop/dir1) 如果没有父路径则不会新建子目录,会报错


### mapreduce demo

#### 运行hadoop官网给的demo (失败)  
    http://hadoop.apache.org/docs/r1.0.4/cn/mapred_tutorial.html  
运行应用程序：  
```
    > $ bin/hadoop jar /usr/joe/wordcount.jar org.myorg.WordCount /usr/joe/wordcount/input /usr/joe/wordcount/output
```
应该为:
```
    > hadoop jar D:\java\hadoop-2.8.1\sbin\hh\hadoop-test-1.0.0.jar hadoop.WordCount /usr/joe/wordcount/input /usr/joe/wordcount/output
```
不用加jar包名称

运行失败 原因:
```
    8/01/25 18:41:16 INFO mapreduce.Job: Job job_1516876525498_0002 failed with state FAILED due to: Application application_1516876525498_0002 failed 2 times due to AM Container for appattempt_1516876525498_0002_000
    002 exited with  exitCode: 5
    Failing this attempt.Diagnostics: Exception from container-launch.
    Container id: container_1516876525498_0002_02_000001
    Exit code: 5
    Exception message: AssignProcessToJobObject error (5): ?????
```
未解决文档  
    https://wiki.apache.org/hadoop/Hadoop2OnWindows


#### 运行成功 通过idea运行(`成功`) demo
https://www.jianshu.com/p/3e1b398ebfea

error:  
  版本问题(版本低于hadoop环境)  
  ```
  Server IPC version 9 cannot communicate with client version 4
  ```

8088 没有监控到运行?  
  -  idea 运行的程序都监测不到  

### map reduce 计算模型
- 执行任务角色:
    jobTracker(跟踪器) 调度工作 `一个hadoop集群中只有一个`
    taskTracker 执行工作



---
### lang comparable 可比较的  
Comparable 是排序接口。  
若一个类实现了Comparable接口，就意味着“该类支持排序”。   
 即然实现Comparable接口的类支持排序，假设现在存在“实现Comparable接口的类的对象的List列表(或数组)”，则该List列表(或数组)可以通过   Collections.sort（或 Arrays.sort）进行排序。  
