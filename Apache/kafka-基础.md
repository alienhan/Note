---
title: kafka 基础
date:
categories:
- kafka
tags:
- kafka 基础
---


kafka
  官网：http://kafka.apache.org

---
### 安装启动
1. 前提条件：
    >jdk  
    >zookeeper(详细在zookeeper.txt中)  

#### 安装kafka  
下载地址

https://www.apache.org/dyn/closer.cgi?path=/kafka/0.10.2.0/kafka_2.10-0.10.2.0.tgz
   >注意：  
   >kafka 2.10-0.10,kafka_2.10-0.9.0.0   对应java8  
   > 

**windows 安装问题**  
  1. 编辑config/serer.properties  
  2. 找到并编辑“log.dirs=/tmp/kafka-logs” to   “log.dir=D:\java\kafka\kafka\kafka-logs”  
  3. 如果Zookeeper在某些其他的机器或集群上运行，可以将“zookeeper.connect:2181”修改为自定义IP与端口。  
    在这个演示中我们使用了同一个机器，因此没必要做修改。文件中的Kafka端口和broker.id也是可以配置的。其他设置不变。  

  4. Kafka会按照默认，在9092端口上运行，并连接zookeeper的默认端口：2181。  

**linux 安装问题**  
Unrecognized VM option '+UseCompressedOops' when running   kafka from my ubuntu in vmware  
解决:  
vi bin/kafka-run-class.sh  

before removing :  
```
KAFKA_JVM_PERFORMANCE_OPTS="-server -XX:+UseCompressedOops 
  -XX:+UseParNewGC -XX:+UseConcMarkSweepGC   
  -XX:+CMSClassUnloadingEnabled 
  -XX:+CMSScavengeBeforeRemark
  -XX:+DisableExplicitGC 
  -Djava.awt.headless=true"
```
after removing :
```
KAFKA_JVM_PERFORMANCE_OPTS="
 -server 
 -XX:+UseParNewGC
 -XX:+UseConcMarkSweepGC 
 -XX:+CMSClassUnloadingEnabled
 -XX:+CMSScavengeBeforeRemark 
 -XX:+DisableExplicitGC
 -Djava.awt.headless=true"
```

### 运行kafka  
重要：确保在启动Kafka服务器前，Zookeeper实例已经准备好并开始运行。  

window启动:  

```
.\bin\windows\kafka-server-start.bat  
.\config\server.properties
```

linux 启动:  
```
sh bin/kafka-server-start.sh  
config/server.properties
```
