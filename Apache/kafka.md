## kafka ##

官网：http://kafka.apache.org

##############################################################################################
---------------------------------------------------------------------------------------------
1. 前提条件：
    >jdk
    >zookeeper(详细在zookeeper.txt中)




------------------------------------------------------------------------------------------------
2. 安装kafka
- 下载地址：https://www.apache.org/dyn/closer.cgi?path=/kafka/0.10.2.0/kafka_2.10-0.10.2.0.tgz
   >注意：
   >kafka 2.10-0.10,kafka_2.10-0.9.0.0   对应java8
   >    

**windows 安装问题**
- 编辑config/serer.properties   
-  找到并编辑“log.dirs=/tmp/kafka-logs” to “log.dir= D:\java\kafka\kafka\kafka-logs” 
- 如果Zookeeper在某些其他的机器或集群上运行，可以将“zookeeper.connect:2181”修改为自定义IP与端口。
  在这个演示中我们使用了同一个机器，因此没必要做修改。文件中的Kafka端口和broker.id也是可以配置的。其他设置不变。 

- Kafka会按照默认，在9092端口上运行，并连接zookeeper的默认端口：2181。

**linux 安装问题**
Unrecognized VM option '+UseCompressedOops' when running kafka from my ubuntu in vmware
解决:
vi bin/kafka-run-class.sh

before removing :
KAFKA_JVM_PERFORMANCE_OPTS="-server -XX:+UseCompressedOops -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -XX:+CMSScavengeBeforeRemark -XX:+DisableExplicitGC -Djava.awt.headless=true"

after removing :
KAFKA_JVM_PERFORMANCE_OPTS="-server -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSClassUnloadingEnabled -XX:+CMSScavengeBeforeRemark -XX:+DisableExplicitGC -Djava.awt.headless=true"


----------------------------------------------------------------------------------------------
3. 运行kafka
    重要：确保在启动Kafka服务器前，Zookeeper实例已经准备好并开始运行。

    window启动:
    .\bin\windows\kafka-server-start.bat .\config\server.properties

    linux 启动:
    sh bin/kafka-server-start.sh  config/server.properties 



-----------------------------------------------------------------------------------------------
4. topic

topic 是在broker站点上新建的因此 无论生产端,消费端都没有配置的权利,
因此在项目中的关于topic配置,只是单纯的与broker站点上的配置相同,不起任何配置性作用.

项目中只能配置producer,customer的相关配置.




------------------------------------------------------------------------------------------------
5. 监控
partition：分区编号
offset：表示该parition已经消费了多少条message
logSize：表示该partition已经写了多少条message
Lag：表示有多少条message没有被消费。


------------------------------------------------------------------------------------------------























