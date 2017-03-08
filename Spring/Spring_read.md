## Spring 源码学习##


== Spring 感知 ===========================================================================================
Aware (感知)

容器管理的Bean一般不需要了解容器的状态和直接使用容器，但是在某些情况下，是需要在Bean中直接对IOC容器进行操作的，
这时候，就需要在Bean中设定对容器的感知。
spring IOC容器也提供了该功能，它是通过特定的Aware接口来完成的。aware接口有以下这些：

BeanNameAware，可以在Bean中得到它在IOC容器中的Bean的实例的名字。

BeanFactoryAware，可以在Bean中得到Bean所在的IOC容器，从而直接在Bean中使用IOC容器的服务。

ApplicationContextAware，可以在Bean中得到Bean所在的应用上下文，从而直接在Bean中使用上下文的服务。

MessageSourceAware，在Bean中可以得到消息源。

ApplicationEventPublisherAware，在bean中可以得到应用上下文的事件发布器，从而可以在Bean中发布应用上下文的事件。

ResourceLoaderAware，在Bean中可以得到ResourceLoader，从而在bean中使用ResourceLoader加载外部对应的Resource资源。

在设置Bean的属性之后，调用初始化回调方法之前，Spring会调用aware接口中的setter方法。

==