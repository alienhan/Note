redis 问题
    缓存淘汰策略
    分布式事物锁
    事务

#事务
    http://redisbook.readthedocs.io/en/latest/feature/transaction.html
Redis 通过 MULTI 、 DISCARD 、 EXEC 和 WATCH 四个命令来实现事务功能
WATCH 命令用于在事务开始之前监视任意数量的键： 当调用 EXEC 命令执行事务时， 如果任意一个被监视的键已经被其他客户端修改了， 那么整个事务不再执行， 直接返回失败。

事务提供了一种将多个命令打包，然后一次性、有序地执行的机制。
事务在执行过程中不会被中断，所有事务命令执行完之后，事务才能结束。
多个命令会被入队到事务队列中，然后按先进先出（FIFO）的顺序执行。
带 WATCH 命令的事务会将客户端和被监视的键在数据库的 `watched_keys`字典中进行关联，当键被修改时，程序会将所有监视被修改键的客户端的 `REDIS_DIRTY_CAS` 选项打开。
只有在客户端的 `REDIS_DIRTY_CAS` 选项未被打开时，才能执行事务，否则事务直接返回失败。
Redis 的事务保证了 ACID 中的一致性（C）和隔离性（I），但并不保证原子性（A）和持久性（D）。
讨论 