---
title: java监听Servlet对象
date:
categories:
- servlet
tags:
- servlet
---

### java servlet监听
在JavaWeb被监听的事件源为： 
- ServletContext、
  - ServletContext 对象是一个为整个 web 应用提供共享的内存，任何请求都可以访问里面的内容  
  - ServletContext,是一个全局的储存信息的空间，服务器开始，其就存在，服务器关闭，其才释放。  
    - request，一个用户可有多个；
    - session，一个用户一个；
  - 而servletContext，所有用户共用一个。所以，为了节省空间，提高效率，ServletContext中，要放必须的、重要的、所有用户需要共享的线程又是安全的一些信息。
- HttpSession、
- ServletRequest，  

即三大域对象。

类型:
- 监听域对象“创建”与“销毁”的监听器；
- 监听域对象“操作域属性”的监听器；
- 监听HttpSession的监听器。

创建与销毁监听器一共有三个：
- ServletContextListener：  
  Tomcat启动和关闭时调用下面两个方法
	public void contextInitialized(ServletContextEvent evt)：ServletContext对象被创建后调用；  
	public void contextDestroyed(ServletContextEvent evt)：ServletContext对象被销毁前调用；  
	
- HttpSessionListener：  
  开始会话和结束会话时调用下面两个方法
	public void sessionCreated(HttpSessionEvent evt)：HttpSession对象被创建后调用；  
	public void sessionDestroyed(HttpSessionEvent evt)：HttpSession对象被销毁前调用；  
	
- ServletRequestListener：  
	开始请求和结束请求时调用下面两个方法  
	public void requestInitiallized(ServletRequestEvent evt)：ServletRequest对象被创建后调用；  
	public void requestDestroyed(ServletRequestEvent evt)：ServletRequest对象被销毁前调用。


事件对象:  
- ServletContextEvent：ServletContextgetServletContext()；
- HttpSeessionEvent：HttpSessiongetSession()；
- ServletRequestEvent：


操作域属性的监听器  
	- ServletContextAttributeEvent  
	- HttpSessionBindingEvent  
	- ServletRequestAttributeEvent   


```java
//ServletContextAttributeEvent
import javax.servlet.ServletContextAttributeEvent;  
import javax.servlet.ServletContextAttributeListener;  
public class MyServletContextAttributeListener implements   ServletContextAttributeListener{
 
    @Override
    public void attributeAdded(ServletContextAttributeEvent arg0) {
        System.out.println(attributeAdded:+arg0.getName()+,+arg0.getValue());
    }
 
    @Override
    public void attributeRemoved(ServletContextAttributeEvent arg0) {
        System.out.println(attributeRemoved:+arg0.getName()+,+arg0.getValue());
    }
 
    @Override
    public void attributeReplaced(ServletContextAttributeEvent arg0) {
        //arg0.getValue()为替代之前的值
        //arg0.getServletContext().getAttribute(arg0.getName())为替代之后的值
        System.out.println(attributeReplaced:+arg0.getName()+,+arg0.getValue()
                +,+arg0.getServletContext().getAttribute(arg0.getName()));
    }
 
}
```

### ServletContext对象   
ServletContext对象代表了一个应用，他是一个Context域对象，它里面的方法主要是一些全局性的方法，可以用来存储各个Servlet之间的共享数据。  
ServletContext对象的应用场景，主要用于Servlet之间数据共享(可以用来做聊天室)，获取web应用的初始化参数，实现Servlet转发，读取资源文件。    

### 监听tomcat
ServletContextListener使用（监听Tomcat启动、关闭）  
自定义一个监听器类，并实现ServletContextListener，  
重写里面的contextDestroyed和contextInitialized，将需要进行的操作写在这两个方  

```java
public class TomcatListener implements ServletContextListener{  
  
    /* (non-Javadoc) 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent) 
     */  
    @Override  
    public void contextDestroyed(ServletContextEvent arg0) {  
          
        System.out.println("tomcat关闭了..........");  
    }  
  
    /* (non-Javadoc) 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent) 
     */  
    @Override  
    public void contextInitialized(ServletContextEvent arg0) {  
          
        System.out.println("tomcate启动了..............");  
    }  
  
}  
```
```xml
 <listener>  
	<listener-class>cn.caculate.web.action.TomcatListener</listener-class>  
</listener> 
```
