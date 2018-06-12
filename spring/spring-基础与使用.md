---
title: Spring基础与使用案例与场景
date:
categories:
- spring
tags:
- spring 基础与使用
---

### 官方文档：
http://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/index.html


### Spring MVC annocation / spring mvc注解

@Controller  
- 注释类，成为请求分发器

@RequestMapping  
-	@RequestMapping("user/login56")  
	不能把控制器的注释名 + 方法的注释名与包含前台页面的的相同
		redirect:在user这个下面搜寻RequestMappering
		会报错，没有这个文件


### Spring MVC  前后台传参

1. form表单传参  
```
	前台传后台
		<form action="robot/getAnswer" method="get">
		  <input name="tagDesc" type="text">
		</form>
		<div>${qaCont}</div>
		<div>${pq.qaCont}</div>
		name与实体类属性名一样
		action与spring mvc controller @RequestMapping value相同 

		<form:form commandName="account">
		指定form默认自动绑定的是Model的command属性值，那么当我的form对象对应的属性名称不是command的时候，
		应该怎么办呢？对于这种情况，Spring给我们提供了一个commandName属性，
		我们可以通过该属性来指定我们将使用Model中的哪个属性作为form需要绑定的command对象。
		除了commandName属性外，指定modelAttribute属性也可以达到相同的效果。
		但是在spring controller中要new
			request.setAttribute("accout", new Account());	

	后台传前台
	   获取对象,对象属性
			model.addAttribute("qaCont", qaCont);
			model.addAttribute("pq",pq);
```

2. ajax 传参

### Spring 初始化启动方法
1. 初始化回调 InitializingBean  
	- InitializingBean接口为bean提供了初始化方法的方式，
	它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候会执行该方法。

2. 通常避免使用InitializingBean 接口，因为这样会将代码与spring 耦合起开，可以在Bean中指定一个普通的初始化方法，
然后在xml文件配置中，指定初始化：
	```xml
	<bean id="initBean" class="com.jh.InitBean" init-method="init">
	```

	```java
	public class InitBean {
	    public void init(){
			//do some initialization work
		}
	}

	//效果相同
	public class initBean implements InitializingBean{
		public void afterPropertiesSet(){
		
		}
	}
	```
	
### Spring task spring 定时任务
xml配置  
application.xml引入spring-tasks配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:util="http://www.springframework.org/schema/util" xmlns:task="http://www.springframework.org/schema/task"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
          http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.2.xsd
          http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.2.xsd
          http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-3.2.xsd
          http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-3.2.xsd
          http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task-3.2.xsd">
	<!-- default thread pool will only have a single thread. -->
	<!-- 调度线程池的大小，调度线程在被调度任务完成前不会空闲 -->
	<task:scheduler id="myScheduler" pool-size="1" />
	<task:scheduled-tasks scheduler="myScheduler">
		<!-- 刷新客服在线状态,通过任务管理器调用,时间间隔10分钟 -->
		<task:scheduled ref="scheduledTaskManager" method="checkCustomerServiceOnLineStatus"
			fixed-rate="600000" cron="0 * * * * ?" />

	</task:scheduled-tasks>
</beans>
```

ref ： bean   例子：

	@Component(value="scheduledTaskManager")
	public class ScheduledTaskManager 
		fixed-rate ：下一次的间隔时间
		initial-delay：初始延迟

方法：
```java
package com.mendian.component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.mendian.config.ServerUtil;
/**
 * 预约任务管理
 * 定时任务工具
 * 
 * @ClassName: JobManager
 * @author jh
 * @date 2016-5-26 下午2:52:54
 * 
 */
@Component(value="scheduledTaskManager")
public class ScheduledTaskManager  {
	private static Logger LOG = LoggerFactory.getLogger(ScheduledTaskManager.class);

	public void checkCustomerServiceOnLineStatus() {
		LOG.info("checkCustomerServiceOnLineStatus start");
		ServerUtil.refreshCustomerServiceOnLineStatus();
		LOG.info("checkCustomerServiceOnLineStatus end");
	}

}
```

### Spring ehcache





### Spring 拦截

preHandle( )
拦截器页面跳转
request.getRequestDispatcher("/WEB-INF/view/test.jsp").forward(request,response);
