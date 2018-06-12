---
title: t-sql 简单sql语句整理,数据类型,约束
date:
categories:
- h2
tags:
- t-sql
- 简单sql
- sql
---


1. 使用数据库,创建数据库,删除数据库
2. 创建一个表，删除表
3. 增删改查
4. 从一个表中copy数据打另一个表中
5. 引号的使用
6. where
7. order by
8. 分号
9. sql语句拼接到string字符串传产问题
10. exists

-----------------------------------------------------------
1. 使用数据库,创建数据库,删除数据库  
   use kyc2  
   create database animail  
   drop database animail  

-----------------------------------------------------------
2. 创建一个表，删除表  
CREATE TABLE 表名称  
(  
列名称1 数据类型,  
列名称2 数据类型,  
列名称3 数据类型,  
....  
)  

例：  
```sql
create table dog(
      id int primary key,
      name text 
   )

   drop table dog
```

``数据类型（data_type）  ``  
规定了列可容纳何种数据类型。  
下面的表格包含了SQL中最常用的数据类型  
数据类型  

仅容纳整数。在括号内规定数字的最大位数。  
integer(size)  
int(size)  
smallint(size)  
tinyint(size)  

容纳带有小数的数字。  
"size" 规定数字的最大位数。  
"d" 规定小数点右侧的最大位数。  
decimal(size,d)  
numeric(size,d)  

容纳固定长度的字符串（可容纳字母、数字以及特殊字符）。  
在括号中规定字符串的长度。  
char(size)  

容纳可变长度的字符串（可容纳字母、数字以及特殊的字符）。  
在括号中规定字符串的最大长度。  
varchar(size)	  

容纳日期。 
date(yyyymmdd)    

```SQL 约束 (Constraints)```  
  约束用于限制加入表的数据的类型    
可以在创建表时规定约束（通过 CREATE TABLE 语句），    
或者在表创建之后也可以（通过 ALTER TABLE 语句）。    

1）SQL NOT NULL 约束   
NOT NULL 约束强制列不接受 NULL 值。  
NOT NULL 约束强制字段始终包含值。  
这意味着，如果不向字段添加值，  
就无法插入新记录或者更新记录。  
下面的 SQL 语句强制 "Id_P" 列和 "LastName" 列不接受 NULL值：  
```sql
CREATE TABLE Persons  
(
Id_P int NOT NULL,
LastName varchar(255) NOT NULL,
FirstName varchar(255),
Address varchar(255),
City varchar(255)
)
```
2）UNIQUE 约束唯一标识数据库表中的每条记录。  
UNIQUE 和 PRIMARY KEY 约束均为列或列集合  
提供了唯一性的保证。  
PRIMARY KEY 拥有自动定义的 UNIQUE 约束。  
请注意，每个表可以有多个 UNIQUE 约束，  
但是每个表只能有一个 PRIMARY KEY 约束。  

MySQL:
```sql
CREATE TABLE Persons
(
  UNIQUE (Id_P)
)
SQL Server / Oracle / MS Access:
CREATE TABLE Persons
(
Id_P int NOT NULL UNIQUE
)
```

如果需要命名 UNIQUE 约束，以及为多个列定义 UNIQUE 约束：
MySQL / SQL Server / Oracle / MS Access:
```sql
CREATE TABLE Persons
(
Id_P int NOT NULL,
LastName varchar(255) NOT NULL,
FirstName varchar(255),
Address varchar(255),
City varchar(255),
CONSTRAINT uc_PersonID UNIQUE (Id_P,LastName)
)
```

撤销 UNIQUE 约束  
MySQL:
ALTER TABLE Persons  
DROP INDEX uc_PersonID  

SQL Server / Oracle / MS Access:  
ALTER TABLE Persons  
DROP CONSTRAINT uc_PersonID  

`3）primary key`  
PRIMARY KEY 约束唯一标识数据库表中的每条记录。  
主键必须包含唯一的值。  
主键列不能包含 NULL 值。  
每个表都应该有一个主键，并且每个表只能有一个主键。  

MySQL:
```sql
CREATE TABLE Persons
(
PRIMARY KEY (Id_P)
)
SQL Server / Oracle / MS Access:
CREATE TABLE Persons
(
Id_P int NOT NULL PRIMARY KEY,
)
```
-----------------------------------------------------------
3. 增删改查  
 
   /*增加一行记录 */  
   insert into dog values(12,'helloworld')  
   可以指定所要插入数据的列：  
   INSERT INTO table_name (列1, 列2,...) VALUES (值1,   值2,....)  

   更改表中的现有数据  
   /* 改变一行记录*/  
   UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值  
   update dog set name = 'jjjjj' where id = '12'  

   /*查询一列记录*/  
   SELECT 列名称,列名称 FROM 表名称  
   select id from dog   

   /*删除一行记录*/  
   delete id=1 from dog  
-----------------------------------------------------------
4. 从一个表中copy数据打另一个表中  
  
   insert into cat(id) select id from dog;  

-----------------------------------------------------------
5. 引号的使用  

条件值周围使用的是单引号。  
SQL 使用单引号来环绕文本值（大部分数据库系统也接受双引号)  
如果是数值，请不要使用引号。  

-----------------------------------------------------------
6. where  

where指定查询返回行的条件。  
SELECT 列名称 FROM 表名称 WHERE 列 运算符 值  
select id from dog where id=1  

  AND 和 OR 运算符用于基于一个以上的条件对记录进行过滤。   
    1AND 和 OR 可在 WHERE 子语句中把两个或多个条件结合起来。  
    2如果第一个条件和第二个条件都成立，  
    则 AND 运算符显示一条记录。  
    3如果第一个条件和第二个条件中只要有一个成立，  
    则 OR 运算符显示一条记录。   
    
    SELECT * FROM Persons WHERE  
    (FirstName='Thomas' OR FirstName='William')  
    AND LastName='Carter'  
-----------------------------------------------------------
7. order by  

ORDER BY 语句用于对结果集进行排序。  
ORDER BY 语句用于根据指定的列对结果集进行排序。  
ORDER BY 语句默认按照升序对记录进行排序。  
按照降序对记录进行排序，可以使用 DESC 关键字。   

以字母顺序显示公司名称（Company），     
并以数字顺序显示顺序号（OrderNumber）    
SELECT Company, OrderNumber FROM   
Orders ORDER BY Company, OrderNumber  
先看公司，公司相同，再看字母  

-----------------------------------------------------------
8. 分号  
SQL 语句后面的分号？  
某些数据库系统要求在每条 SQL 命令的末端使用分号。  
在此不使用分号。  
分号是在数据库系统中分隔每条 SQL 语句的标准方法，  
这样就可以在对服务器的相同请求中执行一条以上的语句。  
如果您使用的是 MS Access 和 SQL Server 2000，则不必在每条  
SQL 语句之后使用分号，  
不过某些数据库软件要求必须使用分号。   

-----------------------------------------------------------
9. sql语句拼接到string字符串传产问题  
	1.sql注入  
	2.内存浪费（多次sql）  
-----------------------------------------------------------
11. exists  
指定一个子查询，检测行的存在。返回 true or false  

-----------------------------------------------------------
