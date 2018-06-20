---
title: kafka 使用中遇到的问题及解决方案,新旧客户端的区别
date:
categories:
- kafka
tags:
- kafka
- kafka 使用中遇到的问题及解决方案
- 新旧客户端的区别
---

### kafka-clients 与 kafka_2.10 的区别 / Client API vs Stream API区别  
http://zqhxuyuan.github.io/2016/02/20/Kafka-Consumer-New/  

推荐使用kafka-clients,scala实现的api被废弃掉了

#### kafka-clients  / Client API  
https://kafka.apache.org/documentation/#api  
  是使用纯java代码编写的kafka客户端   
  缺少Stream API调用部分   
  
#### kafka_2.10 / Stream API(scala实现)
https://kafka.apache.org/081/documentation.html#producerapi  
  传统的使用scala实现的客户端  
