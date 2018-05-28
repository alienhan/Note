二叉树倒置
servlet生命周期
rpc 结构,原理
kafka原理,保证消息不会丢..
mysql性能监控
多线程优化
高并发处理问题
jvm调优,线程监控命令
spring 事物的具体实现
rpc框架原理,协议,设计

#Servlet的生命周期
1. init(ServletConfig)方法：负责初始化Servlet对象，在Servlet的生命周期中，该方法执行一次；该方法执行在单线程的环境下，因此开发者不用考虑线程安全的问题；
2. service(ServletRequest req,ServletResponse res)方法：负责响应客户的请求；为了提高效率，Servlet规范要求一个Servlet实例必须能够同时服务于多个客户端请求，即service()方法运行在多线程的环境下，Servlet开发者必须保证该方法的线程安全性；
3. destroy()方法：当Servlet对象退出生命周期时，负责释放占用的资源；
    什么时候调用:服务器关闭
注意事项
    1. 当Server Thread线程执行Servlet实例的init()方法时，所有的Client Service Thread线程都不能执行该实例的service()方法，更没有线程能够执行该实例的destroy()方法，因此Servlet的init()方法是工作在单线程的环境下，开发者不必考虑任何线程安全的问题。
    2. 当服务器接收到来自客户端的多个请求时，服务器会在单独的Client Service Thread线程中执行Servlet实例的service()方法服务于每个客户端。此时会有多个线程同时执行同一个Servlet实例的service()方法，因此必须考虑线程安全的问题。

#Servlet容器装载Servlet时间:
1. Servlet容器启动时自动装载某些Servlet，实现它只需要在web.XML文件中的<Servlet></Servlet>之间添加如下代码：
    `<loadon-startup>1</loadon-startup> `
2. 在Servlet容器启动后，客户首次向Servlet发送请求
3. Servlet类文件被更新后，重新装载Servlet

#Servlet的单例
    可以理解为单例在多线程下的行为
1. servlet单实例，减少了创建servlet的开销。
2. servlet容器通过线程池管理调度线程，提高了请求的响应速度。
3. Servlet容器不关心到达的Servlet请求访问的是否是同一个Servlet还是其他Servlet，直接分配给他一个新的线程；如果是同一个Servlet的多个请求，那么Servlet的Service方法将在多线程中兵法执行。
4. 每一个请求由ServletRequest对象来接收请求，有ServletResponse对象来响应请求。

#Servlet接收和响应客户请求的过程
当容器收到一个Servlet请求，调度线程从线程池中选出一个工作者线程,将请求传递给该工作者线程，然后由该线程来执行Servlet的service方法。当这个线程正在执行的时候,容器收到另外一个请求,调度线程同样从线程池中选出另一个工作者线程来服务新的请求,容器并不关心这个请求是否访问的是同一个Servlet.当容器同时收到对同一个Servlet的多个请求的时候，那么这个Servlet的service()方法将在多线程中并发执行。 
Servlet容器默认采用单实例多线程的方式来处理请求，这样减少产生Servlet实例的开销，提升了对请求的响应时间，对于Tomcat可以在server.xml中通过<Connector>元素设置线程池中线程的数目。 

#实现:
每一个自定义的Servlet都必须实现Servlet的接口，Servlet接口中定义了五个方法，其中比较重要的三个方法涉及到Servlet的生命周期，分别是上文提到的init(),service(),destroy()方法。GenericServlet是一个通用的，不特定于任何协议的Servlet,它实现了Servlet接口。而HttpServlet继承于GenericServlet，因此HttpServlet也实现了Servlet接口。所以我们定义Servlet的时候只需要继承HttpServlet即可。