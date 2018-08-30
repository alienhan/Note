---
title: linux 中间件
date:
categories:
- linux
tags:
- linux中间件
---

### weblogic简介  
	WebLogic是美国Oracle公司出品的一个application server，  
	确切的说是一个基于JAVAEE架构的中间件，  
	WebLogic是用于开发、集成、部署和管理大型分布式Web应用、网络应用和数据库应用的Java应用服务器。   
	将Java的动态功能和Java Enterprise标准的安全性引入大型网络应用的开发、集成、部署和管理之中。  

#### 下载   
	http://www.oracle.com/technetwork/cn/middleware/weblogic/overview/index.html


#### 安装:
1. java -jar 压缩文件  
	- error:  
		启动程序日志文件为/tmp/OraInstall2016-07-20_09-32-08AM/launcher2016-07-20_09-32-08AM.log。
		/tmp/orcl2461075793919813074.tmp中的空闲空间不足以提取安装程序。实际为 1 MB。所需空间为 890 MB。
		- do: 
			空间不足  
			删除/tmp/下面的几个文件,让出安装临时文件的空间就可以了.  
	
	- error:
		正在提取文件.........  
		当前用户为 root 用户或者具有超级用户权限。  
		Oracle Universal Installer 无法继续安装。  
		- do:  
			改变/root的读写权限
	
