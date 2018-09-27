---
title: jenkins
date: 2016/7/7
categories:
- jenkins
tags:
- jenkins
- 持续集成jenkins
- 持续集成
---

# 持续集成jenkins

---
## Linux


---
## Windows
### 安装
1. 安装：  
	1. windows 安装文件  
	  - 解压，运行
	2. 下载war包,不用安装

2. 运行：  
	- cd jenkins.war目录  
	- java -jar jenkins.war  

3. 访问jenkins:
	- http://localhost:8080   (默认端口可以更改)

### 配置
在系统管理中将jenkins添加到windows服务中才会有jenkins.xml文件
在/用户/user/.jenkins/下  

4. 配置端口：
jenkins.xml配置  

5. 配置系统变量

6. 整合tomcat    
	1. 添加插件deploy  
		- 可能在线添加不了,需要下载下来插件  
		- 在添加到jenkins管理插件下  
	2. 配置tomcat-user.xml
	```
		jenkins有权限操作tomcat
			   <role rolename="tomcat"/>
 			   <role rolename="role1"/>
  			 <role rolename="manager-script"/>
 			 	 <role rolename="manager-gui"/>
  			 <role rolename="manager-status"/>
  			 <role rolename="admin-gui"/>
  			 <role rolename="admin-script"/>
  			 <user username="tomcat" password="tomcat" roles="manager-gui,manager-script,tomcat,admin-gui,admin-script"/>
 			   <user username="both" password="tomcat" roles="tomcat,role1"/>
  			 <user username="role1" password="tomcat" roles="role1"/>
				 ```
	3. 配置context.xml  
		jenkins可以删除项目  
			<Context antiResourceLocking="true">

		*error* :  
		- 配置tomcat属性的时候  
				<Context antiResourceLocking="true"></Context>
			可能对tomcat 虚拟路径下的文件上传之后的访问造成影响  

		- tomcat 锁文件问题:

	4. 配置catalita.bat 配置缓存  
		- 报错 perGerm 问题
---
