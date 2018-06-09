---
title: redis 事物,参数解释,应用场景
date:
categories:
- redis
tags:
- redis 事物
- redis 参数解释
- redis 应用场景
---

## redis 问题  
    缓存淘汰策略  
    分布式事物锁  
    事务  
    redis错误处理,网络断了,出问题应该怎么处理  
    redis性能监控  
    redis 缓存击穿,失效,热点key  
## 事务
http://redisbook.readthedocs.io/en/latest/feature/transaction.html  

Redis 通过 MULTI 、 DISCARD 、 EXEC 和 WATCH 四个命令来实现事务功能  
1. WATCH 命令用于在事务开始之前监视任意数量的键：  
 当调用 EXEC 命令执行事务时， 如果任意一个被监视的键已经被其他客户端修改了， 那么整个事务不再执行， 直接返回失败。

2. 事务提供了一种将多个命令打包，然后一次性、有序地执行的机制。  
事务在执行过程中不会被中断，所有事务命令执行完之后，事务才能结束。
多个命令会被入队到事务队列中，然后按先进先出（FIFO）的顺序执行。  
3. 带 WATCH 命令的事务会将客户端和被监视的键在数据库的   `watched_keys`字典中进行关联，当键被修改时，程序会将所有监视被修改键的客户端的 `REDIS_DIRTY_CAS` 选项打开。  
只有在客户端的 `REDIS_DIRTY_CAS` 选项未被打开时，才能执行事务，否则事务直接返回失败。  
4. Redis 的事务保证了 ACID 中的一致性（C）和隔离性（I），但并不保证原子性（A）和持久性（D）。  

## Redis的单线程属性
由于Redis是单线程机制，所以不会出现并发问题，所以可以将其作为：计数器，产生唯一ID，做秒杀系统。  

## redis 性能指标  
```
info
    # Server  
    redis_version:3.2.4 #Redis版本                                          
    redis_git_sha1:00000000 #Git SHA1  
    redis_git_dirty:0 #Git dirty flag  
    redis_build_id:4a0a5391316325c5  
    redis_mode:cluster  
    os:Linux 2.6.32-358.el6.x86_64 x86_64 #Redis 服务器的宿主操作系统  
    arch_bits:64 #架构（32 或 64 位）  
    multiplexing_api:epoll #Redis 所使用的事件处理机制  
    gcc_version:4.4.7 #编译 Redis 时所使用的 GCC 版本  
    process_id:42341 #服务器进程的 PID  
    run_id:961f96e23843202ae98dbeffb655b4f9c4d4c57e #Redis 服务器的随机标识符（用于 Sentinel 和集群）  
    tcp_port:28001  #TCP/IP 监听端口  
    uptime_in_seconds:60872 #运行时间（秒）  
    uptime_in_days:0 #运行天数  
    hz:10  
    lru_clock:12112874 #以分钟为单位进行自增的时钟，用于 LRU 管理  
    executable:/home/app/redis3/redis-server  
    config_file:/home/app/redis3/./cluster/28001/redis-28001.conf  #cluster配置文件     
    # Clients  
    connected_clients:68  #连接的客户端数量  
    client_longest_output_list:0 #当前连接的客户端当中，最长的输出列表  
    client_biggest_input_buf:0 #当前连接的客户端当中，最大输入缓存  
    blocked_clients:0 #正在等待阻塞命令（BLPOP、BRPOP、BRPOPLPUSH）的客户端的数量        
    # Memory  
    used_memory:831391096 #由 Redis 分配器分配的内存总量（以字节为单位）  
    used_memory_human:792.88M  
    used_memory_rss:888795136 #Redis分配的内存总量(该值与top命令输出一致)  
    used_memory_rss_human:847.62M  
    used_memory_peak:833008000 #Redis 的内存消耗峰值（以字节为单位）  
    used_memory_peak_human:794.42M   
    total_system_memory:33661575168  
    total_system_memory_human:31.35G  
    used_memory_lua:37888  
    used_memory_lua_human:37.00K  
    maxmemory:0  
    maxmemory_human:0B  
    maxmemory_policy:noeviction  
    mem_fragmentation_ratio:1.07 #内存碎片比率  
    mem_allocator:jemalloc-4.0.3        
    # Persistence  
    loading:0 #一个标志值，记录了服务器是否正在载入持久化文件  
    rdb_changes_since_last_save:7358 #距离最近一次成功创建持久化文件之后，经过了多少秒  
    rdb_bgsave_in_progress:0 #一个标志值，记录了服务器是否正在创建 RDB 文件  
    rdb_last_save_time:1505285008 #最近一次成功创建 RDB 文件的 UNIX 时间戳  
    rdb_last_bgsave_status:ok #一个标志值，记录了最近一次创建 RDB 文件的结果是成功还是失败  
    rdb_last_bgsave_time_sec:10 #记录了最近一次创建 RDB 文件耗费的秒数  
    rdb_current_bgsave_time_sec:-1 #如果服务器正在创建 RDB 文件，那么这个值记录的就是当前的创建操作已经耗费的秒数  
    aof_enabled:1 #redis是否开启了aof  
    aof_rewrite_in_progress:0 #一个标志值，记录了服务器是否正在创建 AOF 文件  
    aof_rewrite_scheduled:0 #一个标志值，记录了在 RDB 文件创建完毕之后，是否需要执行预约的 AOF 重写操作  
    aof_last_rewrite_time_sec:4 #最近一次创建 AOF 文件耗费的时长  
    aof_current_rewrite_time_sec:-1 #如果服务器正在创建 AOF 文件，那么这个域记录的就是当前的创建操作已经耗费的秒数  
    aof_last_bgrewrite_status:ok #一个标志值，记录了最近一次创建 AOF 文件的结果是成功还是失败  
    aof_last_write_status:ok  
    aof_current_size:633883114  
    aof_base_size:428945179 #服务器启动时或者 AOF 重写最近一次执行之后，AOF 文件的大小  
    aof_pending_rewrite:0 # 一个标志值，记录了是否有 AOF 重写操作在等待 RDB 文件创建完毕之后执行  
    aof_buffer_length:0 #AOF 缓冲区的大小  
    aof_rewrite_buffer_length:0 #AOF 重写缓冲区的大小  
    aof_pending_bio_fsync:0 # 后台 I/O 队列里面，等待执行的 fsync 调用数量  
    aof_delayed_fsync:12 #被延迟的 fsync 调用数量       
    # Stats  
    total_connections_received:619104 #服务器已接受的连接请求数量  
    total_commands_processed:26830012 #服务器已执行的命令数量  
    instantaneous_ops_per_sec:1159 #服务器每秒钟执行的命令数量  
    total_net_input_bytes:1304952619 #redis网络入口流量字节数  
    total_net_output_bytes:1009790961 #redis网络出口流量字节数  
    instantaneous_input_kbps:55.85 #redis网络入口kps  
    instantaneous_output_kbps:3553.89 #redis网络出口kps  
    rejected_connections:0 #因为最大客户端数量限制而被拒绝的连接请求数量  
    sync_full:2  
    sync_partial_ok:0  
    sync_partial_err:0  
    expired_keys:0 #因为过期而被自动删除的数据库键数量  
    evicted_keys:0 #因为最大内存容量限制而被驱逐（evict）的键数量  
    keyspace_hits:0 #查找数据库键成功的次数  
    keyspace_misses:0 #查找数据库键失败的次数  
    pubsub_channels:0 #当前使用中的频道数量  
    pubsub_patterns:0 #当前使用中的模式数量  
    latest_fork_usec:2639  
    migrate_cached_sockets:0        
    # Replication  
    role:master #当前实例的角色master还是slave  
    connected_slaves:1  
    slave0:ip=172.18.1.162,port=28004,state=online,offset=674295829,lag=0  
    master_repl_offset:674298545  
    repl_backlog_active:1  
    repl_backlog_size:1048576  
    repl_backlog_first_byte_offset:673249970  
    repl_backlog_histlen:1048576       
    # CPU  
    used_cpu_sys:718.26 #服务器耗费的累计系统CPU  
    used_cpu_user:468.60 #服务器耗费的累计用户CPU  
    used_cpu_sys_children:122.46 #后台进程耗费的系统CPU   
    used_cpu_user_children:835.28 #后台进程耗费的用户CPU        
    # Cluster  
    cluster_enabled:1 #是否开启集群模式 1代表是       
    # Keyspace  
    db0:keys=3322,expires=0,avg_ttl=0 #各个数据库的 key 的数量，以及带有生存期的 key 的数量
```
## MONITOR

## SLOWLOG
通过SLOWLOG可以读取慢查询日志  
```
   127.0.0.1:6320> slowlog get 1    
        1) (integer) 394689     #slowlog的唯一编号    
        2) (integer) 1480851711     #此次slowlog事件的发生时间    
        3) (integer) 10639     #耗时    
        4) 1) "HGET"     #slowlog事件所对应的redis 命令    
           2) "hash:submit_sent_150004"    
           3) "15000429648122734363745165312"  
```  
分析:
  1. redis使用单线程处理客户端的请求，结果是，当请求缓慢服务时，所有其他客户端将等待这个请求被服务。如果需要执行很多的slow commands，建议把slow queries放到redis slave上去执行。  
  2. 关于keys命令：执行慢速命令所产生的非常常见的延迟源是在生产环境中使用KEYS命令。   如Redis文档中所述，KEYS应该只用于调试目的。  
SCAN:  
    SCAN是基于游标的迭代器。 这意味着在每次调用命令时，服务器返回一个更新的游标，用户需要在下一次调用中用作游标参数。  
    当游标设置为0时，迭代开始，并且当服务器返回的游标为0时终止迭代。  

## redis延迟时间排查  
定义:  
    延迟指的是客户端从发送命令到接收命令回复这段时间的最大值  
测试一下redis的响应速度：  
    ```redis-cli --latency -h xxx -p xxxx  ```  
这条命令会向Redis插入示例数据来检查平均延时。 Ctrl+C可以随时结束测试；  
出现延时的可能:  
硬件  
网络  
Redis命令：  
1. 一些时间复杂度比较高的命令，如 lrem，sort，sunion等命令会花比较长时间；另外，大量的重复连接也会造成延时，重用连接是一种很好的品质；如果有大量写操作，可以使用 pipeline管道的方式（类似mysql事务），一次性提交，这样数据量也少了，连接次数也少了，不用每次都返回数据，  
2. 速度自然会快很多；  
3. 一个非常常见的造成Redis响应延迟的情况是在生产环境中使用KEYS命令  
使用单线程的一个结果是，当一个请求的处理很慢时，所有其他客户端将等待该请求被处理完毕。  

## Redis持久化：
1. Redis持久化需要fork出一个进程来进行持久化操作，这本身就会引发延时，如果数据变化大，RDB配置时间短，  
那这个代价还是挺大的；再加上，硬盘这东西真有点不靠谱，如果还是虚拟机上的虚拟硬盘，如果还是NFS共享目录，那这延时会让你崩溃。所以，如果系统不需要持久化，关了吧。  

2. Redis有两种持久化的方式：快照（RDB文件）和追加式文件（AOF文件）：   
  1) RDB持久化方式会在一个特定的间隔保存那个时间点的一个数据快照。  
  2) AOF持久化方式则会记录每一个服务器收到的写操作。在服务启动时，这些记录的操作会逐条执行从而重建出原来的数据。写操作命令记录的格式跟Redis协议一致，以追加的方式进行保存。

3. Redis的持久化是可以禁用的，就是说你可以让数据的生命周期只存在于服务器的运行时间里。
两种方式的持久化是可以同时存在的，但是当Redis重启时，AOF文件会被优先用于重建数据。

4. RDB  
    1. 优点  
      RDB的性能很好，需要进行持久化时，主进程会fork一个子进程出来，  
      然后把持久化的工作交给子进程，自己不会有相关的I/O操作。  
      比起AOF，在数据量比较大的情况下，RDB的启动速度更快。  
    2. 缺点  
        RDB容易造成数据的丢失。假设每5分钟保存一次快照，如果Redis因为某些原因不能正常工作，  
        那么从上次产生快照到Redis出现问题这段时间的数据就会丢失了。  
        RDB使用fork()产生子进程进行数据的持久化，如果数据比较大的话可能就会花费点时间，造成Redis停止服务几毫秒。如果数据量很大且CPU性能不是很好的时候，停止服务的时间甚至会到1秒。  
    3. 保存点（RDB的启用和禁用）  
        配置保存点，使Redis如果在每N秒后数据发生了M次改变就保存快照文件。  
        例如下面这个保存点配置表示每60秒，如果数据发生了1000次以上的变动，  
        Redis就会自动保存快照文件：   
            save 60 1000  
        禁用快照保存  
            save ""  
        保存点可以设置多个，Redis的配置文件就默认设置了3个保存点：

## 持久化和延迟/性能的权衡  
为了在后台生成RDB文件，或者当AOF持久化开启时重写AOF文件，Redis需要执行fork。  
fork操作（在主线程中执行）会引发响应延迟。  
1. AOF + fsync always: 非常慢，只有当你确实需要时才使用该配置。  
2. AOF + fsync every second: 一个比较均衡的选择。  
3. AOF + fsync every second +no-appendfsync-on-rewrite选项为yes:也是一个比较均衡的选择，但是要避免重写期间执行fsync，  
这可以降低磁盘压力。  
4. AOF + fsync never: 将fsync操作交给内核，减少了对磁盘的压力和延迟。  
5. RDB: 这里你可以配置触发生成RDB文件的条件。  

## 关闭主服务器持久化时，复制功能的数据安全  
禁止主服务器关闭持久化的同时自动拉起。  
在关闭主服务器上的持久化，并同时开启自动拉起进程的情况下，即便使用Sentinel来实现Redis的高可用性，也是非常危险的。  
因为主服务器可能拉起得非常快，以至于Sentinel在配置的心跳时间间隔内没有检测到主服务器已被重启，然后还是会执行上面的数据丢失的流程。  

## redis 使用场景       
1. string  
    String是二进制安全的。它可以包含任何数据，图片或者其他序列化后的对象  

2. hash  
    hash特别适合用于存储对象,替代对象序列化/反序列化  

3. list   
    Lists的另一个应用就是消息队列，可以利用Lists的PUSH操作，将任务存在Lists中，然后工作线程再用POP操作将任务取出进行执行。  
    List即可以作为栈，也可以作为队列。       
    最新列表  
4. set  
    无序,不重复(自动去除重复)  
    集合提供了求交集、并集、差集  

    求共同关注,相同关注等  

5. Sorted Set zset  
      增加了一个权重参数score，使得集合中的元素能够按score进行有序排列  
    可以用Sorted Sets来做带权重的队列，比如普通消息的score为1，重要消息的score为2，然后工作线程可以选择按score的倒序来获取工作任务。让重要的任务优先执行。  

    + 自动排序  
    + 用户排名等  
        比如按照用户投票时间来排序等等场景。  
    + 排行榜/top n  
    + 构建有优先级的队列系统  

6. 计数器  
    全局增量ID生成，类似“秒杀”  
7. 验证重复访问  
    验证前端的重复请求（可以自由扩展类似情况），可以通过redis进行过滤：每次请求将request Ip、参数、接口等hash作为key存储redis（幂等性请求），设置多长时间有效期，  
    然后下次请求过来的时候先在redis中检索有没有这个key，进而验证是不是一定时间内过来的重复提交  
8. 秒杀  
    list  
    由于 Redis 是单线程模型，lua 可以保证多个命令的原子性。  
