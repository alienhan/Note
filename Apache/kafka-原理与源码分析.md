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
- kafka 保证消息不被重复消费
- kafka 保证消费的可靠性传输
---

### 参考
http://orchome.com

### component 组件  
1. topic  
    topic 是在broker站点上新建的因此 无论生产端,消费端都没有配置的权利,  
    因此在项目中的关于topic配置,只是单纯的与broker站点上的配置相同,不起任何配置性作用.  
    项目中只能配置producer,customer的相关配置.  
2. partition：分区编号  
3. offset：表示该parition已经消费了多少条message  
4. logSize：表示该partition已经写了多少条message  
5. Lag：表示有多少条message没有被消费。  

### producer 参数配置
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

### Consumer 参数配置
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

### Consumer Kafka参数配置/两种提交方式/现行解决方案
Kafka消费端的offset主要由consumer来控制, Kafka将每个consumer所监听的tocpic的partition的offset保存在__consumer_offsets主题中. consumer需要将处理完成的消息的offset提交到服务端, 主要有ConsumerCoordinator(协调者)完成的.  

1. enable.auto.commit 默认为true  
2. auto.commit.interval.ms 默认为5000 ms (5s)(间隔)  
3. max.poll.records 默认为500  
4. fetch.max.bytes 默认为52428800 bytes (50Mib). (取来) 

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
        
**手动提交**  
  把enable.auto.commit设置为false,  
  并在每处理完一条数据后手动提交offset.  
  这里需要注意的是:  
      提交的offset是对当前消息的offset基础上进行加1.  
  ```java
   consumer.commitSync(Collections.singletonMap(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1)));
   ```
**现行解决方案**
  - 新建线程池接受消息
  - 自动提交
 
新建线程处理消息  

### kafka partition和consumer数目关系 
  kafka的设计是在一个partition上是不允许并发  
  partiton数目是consumer数目的整数倍  
  kafka只保证在一个partition上数据是有序  
  增减consumer，broker，partition会导致rebalance，所以rebalance后consumer对应的partition会发生变化  

### 保证消息不被重复消费
分析:  
  - 这个问题其实换一种问法就是，如何保证消息队列的幂等性  

原因:  
 - 消费者在消费消息时候，消费完毕后，会发送一个确认信息给消息队列，消息队列就知道该消息被消费了，就会将该消息从消息队列中删除。  
 - 因为网络传输等等故障，确认信息没有传送到消息队列，导致消息队列不知道自己已经消费过该消息了，再次将该消息分发给其他的消费者。  
    - 例如:手动提交失败

业务场景
 - 幂等性操作:
   - mysql insert插入唯一主键
   - redis set 
 - 其他  
  第三方介质,来做消费记录  
  - 以redis为例，给消息分配一个全局id，只要消费过该消息，将<id,message>以K-V形式写入redis。那消费者开始消费前，先去redis中查询有没消费记录即可。  
  - 类似分布式锁

### 保证消费的可靠性传输
    生产者弄丢数据、消息队列弄丢数据、消费者弄丢数据。

1. 生产者弄丢数据  
   在kafka生产中，基本都有一个leader和多个follwer。follwer会去同步leader的信息。因此，为了避免生产者丢数据，做如下两点配置
    - 第一个配置要在producer端设置acks=all。这个配置保证了，follwer同步完成后，才认为消息发送成功。
    - 在producer端设置retries=MAX，一旦写入失败，这无限重试
2. 消息队列丢数据  
  针对消息队列丢数据的情况，无外乎就是，数据还没同步，leader就挂了，这时zookpeer会将其他的follwer切换为leader,那数据就丢失了。针对这种情况，应该做两个配置。
    - replication.factor参数，这个值必须大于1，即要求每个partition必须有至少2个副本
    - min.insync.replicas参数，这个值必须大于1，这个是要求一个leader至少感知到有至少一个follower还跟自己保持联系
3. 消费者丢数据  
  自动提交了offset，然后你处理程序过程中挂了。kafka以为你处理好了.
  - 改成手动提交

---
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
