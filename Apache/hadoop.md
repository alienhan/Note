#hadoop

---
#安装
参考:
    http://www.cnblogs.com/wuxun1997/p/6847950.html
windows 需要文件
    https://github.com/steveloughran/winutils
    替换bin下的文件,system32下放入hadoop.dll


---
#hadoop 启动
启动Hadoop集群需要启动HDFS集群和Map/Reduce集群。
    1.格式化一个新的分布式文件系统：(只能格式化一次,多次会造成put时sid错误.)
        hadoop namenode -format
    2. 在分配的NameNode上，运行下面的命令启动HDFS：
        sbin>start-all.cmd
    3. 
        sbin>jps
            4944 DataNode
            5860 NodeManager
            3532 Jps
            7852 NameNode
            7932 ResourceManager

监听端口
    http://localhost:9000/
web 页面访问端口欧
    http://localhost:50070/
    http://localhost:8088

---
#hadoop shell

#put 
使用方法：hadoop fs -put <localsrc> ... <dst>
从本地文件系统中复制单个或多个源路径到目标文件系统。也支持从标准输入中读取输入写入目标文件系统。

    * hadoop fs -put localfile /user/hadoop/hadoopfile
    * hadoop fs -put localfile1 localfile2 /user/hadoop/hadoopdir
    * hadoop fs -put localfile hdfs://host:port/hadoop/hadoopfile
    * hadoop fs -put - hdfs://host:port/hadoop/hadoopfile 
从标准输入中读取输入。

返回值：
成功返回0，失败返回-1。(我put的时候没有返回值?)

put 的hadoop 的位置的路径下没有其他文件或目录时,直接替换这个路径,而不是在这个路径下新建文集

#mkdir
使用方法：hadoop fs -mkdir <paths> 
接受路径指定的uri作为参数，创建这些目录。其行为类似于Unix的mkdir -p，它会创建路径中的各级父目录。
示例：

    * hadoop fs -mkdir /user/hadoop/dir1 /user/hadoop/dir2
    * hadoop fs -mkdir hdfs://host1:port1/user/hadoop/dir hdfs://host2:port2/user/hadoop/dir

返回值：
成功返回0，失败返回-1。

mkdir多路径的时候(/user/hadoop/dir1) 如果没有父路径则不会新建子目录,会报错








