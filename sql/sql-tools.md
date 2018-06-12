---
title: sql工具
date:
categories:
- sql工具
tags:
- PL/SQL
- Navcat
- PowerDesigner
---

### PL/SQL
用于连接Oricle数据库  
连接Oricle数据库配置  

1. 安装Oricle客户端  

2. 配置Oricle客户端  
	将instantclient_11_2解压到D:\java\app\jhon\product下

3. 配置环境变量（instantclient需要）  
	TNS_ADMIN  
	D:\java\app\jhon\product\instantclient_11_2  

	ORACLE_HOME  
	D:\java\app\jhon\product\instantclient_11_2  

	path  
	当安装client时，在path最前面自动配置为  
	D:\java\app\jhon\product\11.2.0\client_1  
	
4. PL/SQL -> 工具(tools) ->首选项(persistense)  
	-> Oricle -> 连接(contents)  

5. 中文乱码  
	配置环境变量  
	NLS_LANG  
	SIMPLIFIED CHINESE_CHINA.ZHS16GBK  
  

快捷键：  
	f8:执行  
	f10：提交  
	
### Navicat

连接数据库  
- Navicat连接Oricle database 配置同PL/SQL相同  
	在 tools-> options->oci中加入client下的oci.dll就行  
	
- Navicat连接SQL Server  
	先下载sqlncli 软件并安装  
	在创建连接的时候没有端口号选项，添加端口号，（例）10.1.41.215,3011  


快捷键：  
	Ctrl + R 运行所有  
	Ctrl + Shift + R 运行选中  

#### 导出表结构，视图结构，数据  
	导出表：  
		表名->右击->duplicate->structure only  
	导出视图：  
		视图名->右击->object information->第二个 ddl   
	导出一行数据：  
		行->右击->Copy as -> Insert statement/Update statement  

#### navicat 结合快捷键
1. ctrl+q 打开查询窗口  
2. ctrl+/ 注释sql语句  
3. ctrl+shift +/ 解除注释  
4. ctrl+r 运行查询窗口的sql语句  
5. ctrl+shift+r 只运行选中的sql语句  
6. F6 打开一个mysql命令行窗口  
7. ctrl+l 删除一行  
8. ctrl+n 打开一个新的查询窗口  
9. ctrl+w 关闭一个查询窗口  
10. ctrl+d 在查询表数据界面打开一个该表结构的窗口  
ctrl+q就会弹出一个sql输入窗口，就可以在里面写sql了。写完sql后，直接ctrl+r就执行sql了。  
还有一点，写sql语句时，navicat会提示的，根代码补全差不多  

自动完成代码（只限于完整版本）：  
要激活自动完成代码，只需按「.」显示当前范围的数据库对象可用的属性。  

### PowerDesigner

***改变table字体：***  
Tools -----> Display Preference....--> Table --> Format --> Modify --> Font  

***新建数据库工程***  
file -> new model -> model types -> physical data model
->physical diagram

保存文件 pdb,pdm  

***新建表***
- 在设计属性(字段)的时候，三个字母(M、P、D)分别表示：  
	M：是否为空；(√表示不允许为空)  
	P：是否为主键；  
	D：是否在该软件的视图中显示  
- 设置主键自增：  
	Columns选项卡,选中整列,查看列属性,  
	打开另外一个窗口,右下角有个Identity,勾上即可.  

新建外键：    

生成数据库：    
- Database -> Generate Database    
- 不生成外键：  
   Database -> Generate Database ->options   
