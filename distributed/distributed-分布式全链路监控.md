---
title: 分布式全链路监控
date: 2018/9/27
categories:
- 分布式
tags:
- 分布式
---

### 体系
现代APM体系，基本都是参考Google的Dapper（大规模分布式系统的跟踪系统）的体系来做的。  
通过跟踪请求的处理过程，来对应用系统在前后端处理、服务端调用的性能消耗进行跟踪  

Dapper  
http://bigbully.github.io/Dapper-translation/

### APM系统

#### Pinpoint  

#### SkyWalking  

#### Zipkin    
  官网：https://zipkin.io/  
  GitHub：https://github.com/openzipkin/zipkin

这个是twitter开源出来的，也是参考Dapper的体系来做的。  
Zipkin的java应用端是通过一个叫Brave的组件来实现对应用内部的性能分析数据采集。  
Brave的github地址：https://github.com/openzipkin/brave  
这个组件通过实现一系列的java拦截器，来做到对http/servlet请求、数据库访问的调用过程跟踪。  
然后通过在spring之类的配置文件里加入这些拦截器，完成对java应用的性能数据采集。  

#### CAT
GitHub：https://github.com/dianping/cat  
大众点评开源出来的，实现的功能丰富  
不过他实现跟踪的手段，是要在代码里硬编码写一些“埋点”，也就是侵入式的。  
`利:`  
  好处是可以在自己需要的地方加埋点，比较有针对性；  
`弊:`  
  坏处是必须改动现有系统  


### APM 与 传统的监控软件（Zabbix等）区别
APM:  
  关注在对于系统内部执行、系统间调用的性能瓶颈分析
