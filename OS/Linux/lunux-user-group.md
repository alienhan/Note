---
title: linux 用户组
date:
categories:
- linux
tags:
- linux用户组
---


### 显示
groups 查看当前登录用户的组内成员
groups gliethttp 查看gliethttp用户所在的组,以及组内成员
whoami 查看当前登录用户名

/etc/group文件包含所有组
/etc/shadow和/etc/passwd系统存在的所有用户名

#### /etc/group 解说

/etc/group 文件是用户组的配置文件，内容包括用户和用户组，  
并且能显示出用户是归属哪个用户组或哪几个用户组，  
因为一个用户可以归属一个或多个不同的用户组；  
同一用 户组的用户之间具有相似的特征。比如我们把某一用户加入到root用户组，  
那么这个用户就可以浏览root用户家目录的文件，如果root用户把某个文件 的读写执行权限开放，  
root用户组的所有用户都可以修改此文件，如果是可执行的文件（比如脚本），root用户组的用户也是可以执行的；  
用户组的特性在系统管理中为系统管理员提供了极大的方便，但安全性也是值得关注的，   
如某个用户下有对系统管理有最重要的内容，最好让用户拥有独立的用户组，或者是把用户下的文件的权限设置为完全私有；  
另外root用户组一般不要轻易把普通用户加入进去，  

#### /etc/group 内容具体分析  

/etc/group 的内容包括用户组（Group）、用户组口令、GID及该用户组所包含的用户（User），每个用户组一条记录；格式如下：  
``group_name:passwd:GID:user_list  ``

在/etc/group 中的每条记录分四个字段：  
	第一字段：用户组名称；  
	第二字段：用户组密码；  
	第三字段：GID  
	第四字段：用户列表，每个用户之间用,号分割；本字段可以为空；如果字段为空表示用户组为GID的用户名；  


### 显示用户
id user

cat /etc/passwd 可以查看所有用户的列表  
w 可以查看当前活跃的用户列表  



### 操作过程
总结了Linux添加或者删除用户和用户组时常用的一些命令和参数。
	1. 建用户：  
	adduser phpq                              //新建phpq用户  
	passwd phpq                               //给phpq用户设置密码  

	2. 建工作组
	groupadd test                             //新建test工作组

	3. 新建用户同时增加工作组
	useradd -g test phpq                      //新建phpq用户并增加到test工作组
											  //新建用户时若不指定其组，系统会建一个与用户名相同的组。

	//注：：-g 所属组 -d 家目录 -s 所用的SHELL


	4. 给已有的用户增加工作组
	usermod -G groupname username

	或者：gpasswd -a user group

	5. 临时关闭：在/etc/shadow文件中属于该用户的行的第二个字段（密码）前面加上*就可以了。想恢复该用户，去掉*即可。

	或者使用如下命令关闭用户账号：
	passwd peter –l

	重新释放：
	passwd peter –u

	6. 永久性删除用户账号
	userdel peter

	groupdel peter

	usermod –G peter peter   （强制删除该用户的主目录和主目录下的所有文件和子目录）

	7. 从组中删除用户
	编辑/etc/group 找到GROUP1那一行，删除 A
	或者用命令
	gpasswd -d A GROUP

	8. 更改目录属主为Oracle用户所有，输入命令：
	chown -R oracle:oinstall /home/oracle/app  
	默认情况下绑定的位置为 /home/用户名


### 用户之前切换 运行java等命令:
root --> 普通用户  
vi .bashrc   

添加环境变量:

	PATH=$PATH:$HOME/bin
	export JAVA_HOME=/root/Downloads/jdk1.8.0_91
	export CATALINA_HOME=/root/Downloads/apache-tomcat-7.0.42
	export MAVEN_HOME=/root/Downloads/apache-maven-3.0.5
	export PATH=$MAVEN_HOME/bin:$CATALINA_HOME/bin:$JAVA_HOME/bin:$PATH
	LANG=zh_CN.GB18030
	LANGUAGE=zh_CN.GB18030:zh_CN.GB2312:zh_CN
	export LANG LANGUAGE
	export PATH

sudo 用户名  
运行命令就可以了  

### 用户权限
chmod命令是非常重要的，用于改变文件或目录的访问权限。用户用它控制文件或目录的访问权限。  
1. 文字设定法  
2. 数字设定法  
　　我们必须首先了解用数字表示的属性的含义：  
  0表示没有权限，1表示可执行权限，2表示可写权限，4表示可读权限，然后将其相加。 
所以数字属性的格式应为3个从0到7的八进制数，其顺序是（u）（g）（o）。  
　　例如，如果想让某个文件的属主有“读/写”二种权限，需要把4（可读）+2（可写）＝6（读/写）。  
　　数字设定法的一般形式为：  
　　chmod ［mode］ 文件名¼  

**chown 命令**  
　　功能：更改某个文件或目录的属主和属组。这个命令也很常用。例如root用户把自己的一个文件拷贝给用户xu，  
    为了让用户xu能够存取这个文件，root用户应该把这个文件的属主设为xu，否则，用户xu无法存取这个文件。  
　　语法：chown ［选项］ 用户或组 文件  
　　说明：chown将指定文件的拥有者改为指定的用户或组。  
    用户可以是用户名或用户ID。组可以是组名或组ID。文件是以空格分开的要改变权限的文件列表，支持通配符。  
　　该命令的各选项含义如下：  
　　	- R 递归式地改变指定目录及其下的所有子目录和文件的拥有者。  
	　　- v 显示chown命令所做的工作。  
　　eg：把文件shiyan.c的所有者改为wang。  
　　	``$ chown wang shiyan.c  ``
