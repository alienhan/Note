## kafka ##

官网：http://kafka.apache.org

--- ------------------------------------------------------------------------------------------
**windows安装运行kafka**

1. 前提条件：
    >jdk
    >zookeeper(详细在storm.md中)

2. 安装kafka
- 下载地址：https://www.apache.org/dyn/closer.cgi?path=/kafka/0.10.2.0/kafka_2.10-0.10.2.0.tgz
   >注意：
   >kafka 2.10-0.10,kafka_2.10-0.9.0.0   对应java8
   >    

- 编辑config/serer.properties   
-  找到并编辑“log.dirs=/tmp/kafka-logs” to “log.dir= D:\java\kafka\kafka\kafka-logs” 
- 如果Zookeeper在某些其他的机器或集群上运行，可以将“zookeeper.connect:2181”修改为自定义IP与端口。在这个演示中我们使用了同一个机器，因此没必要做修改。文件中的Kafka端口和broker.id也是可以配置的。其他设置不变。 

- Kafka会按照默认，在9092端口上运行，并连接zookeeper的默认端口：2181。

3. 运行kafka
    重要：请确保在启动Kafka服务器前，Zookeeper实例已经准备好并开始运行。

    .\bin\windows\kafka-server-start.bat .\config\server.properties

--- ------------------------------------------------------------------------------------------























