---
title: redis 参数解析
date:
categories:
- redis
tags:
- redis 参数解析
---


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
