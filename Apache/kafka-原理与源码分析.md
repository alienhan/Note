---
title: kafka源码与原理分析/组成组件/应用场景
date:
categories:
- kafka
tags:
- kafka 源码与原理
- kafka producer
- kafka consumer
- kafka 消息持久性
---

### component 组件  
1. topic  
    topic 是在broker站点上新建的因此 无论生产端,消费端都没有配置的权利,  
    因此在项目中的关于topic配置,只是单纯的与broker站点上的配置相同,不起任何配置性作用.  
    项目中只能配置producer,customer的相关配置.  
2. partition：分区编号  
3. offset：表示该parition已经消费了多少条message  
4. logSize：表示该partition已经写了多少条message  
5. Lag：表示有多少条message没有被消费。  

### producer 配置
1. acks  
  数据 durability 的设置；  
  producer希望leader返回的用于确认请求完成的确认数量. 可选值   all, -1, 0 1. 默认值为1  

  acks=0 不需要等待服务器的确认. 这时retries设置无效. 响应里来自服务端的offset总是-1.producer只管发不管发送成功与否。延迟低，容易丢失数据。  
  acks=1 表示leader写入成功（但是并没有刷新到磁盘）后即向producer响应。延迟中等，一旦leader副本挂了，就会丢失数据。  
  
  acks=all 等待数据完成副本的复制, 等同于-1. 假如需要保证消息不丢失, 需要使用该设置. 同时需要置unclean.leader.election.enable为true, 保证当ISR列表为空时, 选择其他存活的副本作为新的leader.  
2. producer.type：  
  消息发送类型同步还是异步，默认为同步  
3. compression.codec：  
  消息的压缩格式，默认为none不压缩，也可以为gzip, snappy, lz4
4. max.in.flight.requests.per.connection=1  
  同一分区消息乱序：假设a,b两条消息，a先发送后由于发送失败重试，这时顺序就会在b的消息后面，可以设置  
      `  max.in.flight.requests.per.connection=1`  
  来避免  
  max.in.flight.requests.per.connection：  
    限制客户端在单个连接上能够发送的未响应请求的个数。设置此值是1表示kafka broker在响应请求之前client不能再向同一个broker发送请求，但吞吐量会下降  

**异步生产者配置**  
  1. queue.buffering.max.ms：生产者异步缓存数据的最大时间，单位毫秒  
  2. queue.buffering.max.messages：生产者异步缓存消息的最大容量
  3. queue.enqueue.timeout.ms：队列超时事件。0，如果队列满了就放弃；-ve，如果队列满了就会永远阻塞；+ve，如果队列满了会阻塞一段时间。
  4. batch.num.messages：生产者可以批量处理的消息数

### Consumer
1. auto.commit.enable = true  
  true时，Consumer会在消费消息后将offset同步到zookeeper，这样当Consumer失败后，新的consumer就能从zookeeper获取最新的offset
  如果设为true，consumer会定时向ZooKeeper发送已经获取到的消息的offset。当consumer进程挂掉时，已经提交的offset可以继续使用，让新的consumer继续工作。  
2. auto.commit.interval.ms = 60 * 1000  
  consumer向ZooKeeper发送offset的时间间隔。自动提交的时间间隔 interval(时间间隔)

3. rebalance.max.retries 默认值：4  
  当一个新的consumer加入一个consumer group时，会有一个rebalance的操作，导致每一个consumer和partition的关系重新分配。如果这个重分配失败的话，会进行重试，此配置就代表最大的重试次数。
4. rebalance.backoff.ms = 2000 默认值：2000  
  每次reblance的时间间隔
5. refresh.leader.backoff.ms  
  在consumer发现失去某个partition的leader后，在leader选出来前的等待的backoff时间。

### Consumer Kafka消息消费一致性(重复,丢失,提交失败)  
Kafka消费端的offset主要由consumer来控制, Kafka将每个consumer所监听的tocpic的partition的offset保存在__consumer_offsets主题中. consumer需要将处理完成的消息的offset提交到服务端, 主要有ConsumerCoordinator(协调者)完成的.  

**自动提交**:  
每次从kafka拉取数据之前, 假如是异步提交offset, 会先调用已经完成的offset commit的callBack, 然后检查ConsumerCoordinator的连接状态. 如果设置了自动提交offset, 会继续上次从服务端获取的数据的offset异步提交到服务端. 这里需要注意的是会有几种情况出现:
  1. 消息丢失  
    消息处理耗时较多, 假如处理单条消息的耗时为t, 拉取的消息个数为n. t * n > auto_commit_interval_ms, 会导致没有处理完的消息的offset被commit到服务端. 假如此时消费端挂掉, 没有处理完的数据将会丢失.
  2. 重复消费  
    假如消息处理完成, offset还未commit到服务端的时候``消费端挂掉``, 已经处理完的消息会被再次消费.  
    影响着数据一致性和性能  
    业务处理:  
      去重：将消息的唯一标识保存到外部介质中，每次消费处理时判断是否处理过
  3. 消息乱序:  
      retry造成的  
      max.in.flight.requests.per.connection = 1  
       限制客户端在单个连接上能够发送的未响应请求的个数。设置此值是1表示kafka broker在响应请求之前client不能再向同一个broker发送请求  
       (保证单个partition有序)    

  4. enable.auto.commit 默认为true  
  5. auto.commit.interval.ms 默认为5000 ms (5s)(间隔)  
  6. max.poll.records 默认为500  
  7. fetch.max.bytes 默认为52428800 bytes (50Mib). (取来)  

***解决方案. ***  
1. 手动提交:  
  把enable.auto.commit设置为false,  
  并在每处理完一条数据后手动提交offset.  
  这里需要注意的是:  
      提交的offset是对当前消息的offset基础上进行加1.  
```java
 consumer.commitSync(Collections.singletonMap(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1)));
 ```
  - 手动提交失败问题:
  - 
2.  
  新建线程处理消息  

### kafka partition和consumer数目关系 
  kafka的设计是在一个partition上是不允许并发  
  partiton数目是consumer数目的整数倍  
  kafka只保证在一个partition上数据是有序  
  增减consumer，broker，partition会导致rebalance，所以rebalance后consumer对应的partition会发生变化  


### kafka副本问题
1. 发消息过程  
  Producer在发布消息到某个Partition时，先通过ZooKeeper找到该Partition的Leader，然后无论该Topic的Replication Factor为多少（也即该Partition有多少个Replica），Producer只将该消息发送到该Partition的Leader。Leader会将该消息写入其本地Log。每个Follower都从Leader pull数据。这种方式上，Follower存储的数据顺序与Leader保持一致.  
2. Kafka分配Replica的算法  
    将所有Broker（假设共n个Broker）和待分配的Partition排序.  
    将第i个Partition分配到第（i mod n）个Broker上.  
    将第i个Partition的第j个Replica分配到第（(i + j) mode n）个Broker上.  

### zookeeper如何管理kafka  
  Producer端使用zookeeper用来”发现”broker列表,以及和Topic下每个partition leader建立socket连接并发送消息.  
  Broker端使用zookeeper用来注册broker信息,以及监测partition leader存活性.  
  Consumer端使用zookeeper用来注册consumer信息,其中包括consumer消费的partition列表等,同时也用来发现broker列表,并和partition leader建立socket连接,并获取消息.  
