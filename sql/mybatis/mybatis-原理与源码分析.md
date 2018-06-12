---
title: mybatis 原理与源码分析
date:
categories:
- mybatis
tags:
- mybatis 原理与源码分析
---

### mybatis执行过程\方法的调用过程
    
    SqlSession
    SqlSessionFactoryBuilder->SqlSessionFactory
    sql 查询->SqlSession->DeFaultSqlSession(implements)

    Executor
    ->CachingExecutor-->delegate(委派)
    ->SimpleExecutor(通过delegate机制完成) ,执行query方法的时候先调用BaseExecutor中的query函数,base中的query再调用simple中的doQuery函数
    
    ->doQuery通过StatementHandler组装jdbc的statement,connnection...

    StatementHandler
    ->RoutingStatementHandler-->delegate
    -->PreparedStatementHandler(负责对数据库执行statement,返回结果)


### 缓存

    一级缓存
        开启:mybaits-settings中设置<setting name="localCacheScope" value="SESSION"/>
        
        一级缓存只在数据库会话内部共享
    
    二级缓存
