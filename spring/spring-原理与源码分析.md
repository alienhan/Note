---
title: Spring基础与使用案例与场景
date:
categories:
- spring
tags:
- spring 基础与使用
---   

### BeanFactory
  - 构建ioc基本功能


### Aware (感知)    bean操作ioc容器
容器管理的Bean一般不需要了解容器的状态和直接使用容器，但是在某些情况下，是需要在Bean中直接对IOC容器进行操作的，  
  - 这时候，就需要在Bean中设定对容器的感知。  
  spring IOC容器也提供了该功能，它是通过特定的Aware接口来完成的。aware接口有以下这些：   
      - BeanNameAware，可以在Bean中得到它在IOC容器中的Bean的实例的名字。    
      - BeanFactoryAware，可以在Bean中得到Bean所在的IOC容器，从而直接在Bean中使用IOC容器的服务。  
      - ApplicationContextAware    
        可以在Bean中得到Bean所在的应用上下文，从而直接在Bean中使用上下文的服务。    
      - MessageSourceAware，在Bean中可以得到消息源。  
      - ApplicationEventPublisherAware:  
        在bean中可以得到应用上下文的事件发布器，从而可以在Bean中发布应用上下文的事件。  
      - ResourceLoaderAware:  
        在Bean中可以得到ResourceLoader，从而在bean中使用ResourceLoader加载外部对应的Resource资源。   
         
在设置Bean的属性之后，调用初始化回调方法之前，Spring会调用aware接口中的setter方法。


### Transaction /Spring 事物
https://www.ibm.com/developerworks/cn/education/opensource/os-cn-spring-trans/

1. 本地事务和全局事务
    - 全局事务让你可以和多个事务资源工作在一起，比如，关系型数据库，消息队列
    
    - 本地事务则是与某个指定的事务资源联系在一起，比如，与JDBC连接相关的事务。本地事务相对于全局事务更容易使用，但不能跨多个事务资源。管理JDBC连接所写的事务代码不能够在全局事务中使用。

2. settings /事物对应配置
    - 隔离级别
    - 传播行为
        1. required  
             如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
        2. requires_new  
            创建一个新的事务，如果当前存在事务，则把当前事务挂起。
        3. supports  
            如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
        4. not_supported  
            非事务方式运行，如果当前存在事务，则把当前事务挂起。
        5. never
            以非事务方式运行，如果当前存在事务，则抛出异常。
        6. mandatory  
            如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。 
        7. nested  
            如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于required
        
        当前有事物  
            - 加入  
            - 挂起,新建  
            - 抛异常  
            
        当前没有事物  
            - 新建  
            - 非事物运行  
            - 抛异常  
3. 事务超时
4. 事务的只读属性
5. 事务的回滚规则        

### spring结构/ bean生成工厂的组成结构  
1. 具体的ioc实现
  - DefaultListableBeanFactory  
    - 在spring中,实际上是把DefaultListableBeanFactory作为一个默认的功能完整的Ioc容器来使用的.  
    - XmlBeanFactory,ApplicationContect都是在DefaultListableBeanFactory基础上扩展的.  
    - AbstractRefreshableApplicationContext中包含DefaultListableBeanFactory的实例  

2. BeanFacory  
    BeanFactory的实现是Ioc的基本形式  

3. ApplicationContext  
    ApplicationContext的各种实现是IoC容器的高级表现形式  


### spring 容器启动过程
启动时执行  
1. Resource定位  
    - Resource是spring对i/o的封装类.  
2. BeanDefinition载入  

3. 注册BeanDefinition
    - 在IoC容器内部将BeanDefinition注入到HashMap中  

4. Bean依赖注入
    - 时间:发生在应用第一次调用AbstractBeanFactory中的getBean-->doGetBean向容器索取bean的时候
    
    - AbstractBeanFactory中的doGetBean具体实现
    DefaultSingletonBeanRegistry含有bean的三级缓存
    ```java
    /** Cache of singleton objects: bean name --> bean instance */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);
    /** Cache of singleton factories: bean name --> ObjectFactory */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<String, ObjectFactory<?>>(16);
    /** Cache of early singleton objects: bean name --> bean instance */
    private final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>(16);
    ```
       
    - getSingleton是调用
#### Spring-bean的循环依赖以及解决方式
