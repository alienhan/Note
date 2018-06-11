---
title: h2数据库
date:
categories:
- h2
tags:
- h2配置
- h2
- h2数据库
---

### h2配置：maven 引入配置

pom.xml
```xml
<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<version>1.3.146</version>
</dependency>
```
spring.xml
```xml
<!-- h2 database -->
<!-- 初始化数据表结构 -->
<bean id="dataSource" class="org.h2.jdbcx.JdbcConnectionPool"
	destroy-method="dispose">
	<constructor-arg>
		<bean class="org.h2.jdbcx.JdbcDataSource">
			<!-- 内存模式 -->
			<property name="URL"
				value="jdbc:h2:mem:csiqcssit1;MODE=MYSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;INIT=RUNSCRIPT FROM 'target/test-classes/database/h2/csiqcssit1.sql'\;RUNSCRIPT FROM 'target/test-classes/database/h2/csiqcssit1-data.sql'" />
			<!-- 文件模式 -->
			<!-- <property name="URL" value="jdbc:h2:testRestDB" /> -->
			<property name="user" value="sa" />
			<property name="password" value="sa" />
		</bean>
	</constructor-arg>
</bean>
```

#### jenkins打包错误：  
	jenkins打包时jdk版本1.7，使用h2-1.4时候：  
	报版本冲突，使用h2-1.3就好了  

#### h2-1.3 init sql使用classpath报错：   
	h2-1.4才能使用classpath   
	h2-1.3使用sql文件，项目根路径的相对路径   
