---
title: sql server 存储过程
date:
categories:
- 存储过程
tags:
- 存储过程
---


### sql server 存储过程
#### 调用系统存储过程
  exec sp_databases  --调用系统存储过程，查询数据库  
  exec sp_tables;    --调用系统存储过程，查询当前数据库下的表  
  exec sp_helpdb     --调用系统存储过程，查询当前数据库的基本信息  

1. 不带参数的存储过程   
  - 在sqlserver下创建存储过程   
  ```sql
  //navicat，sqlserver客户端都可以使用   
  create PROCEDURE proc_meetinginfo    
  AS  
  select * from MEETING_INFO  
  GO  
  ```
  执行存储过程  
  ```EXEC proc_meetinginfo```

2. 带参数的存储过程  
  - 创建
  ```sql
  create PROCEDURE proc_meetinginfo2(
  	@name varchar(255)
  )
  AS
  	select * from MEETING_INFO WHERE MEETING_NAME=@name
  GO
  ```
  如果不赋值参数类型大小，大小默认为1  
  - 执行1    
  ```sql
    sql server 可以用，navicate不能用
    DECLARE @name varchar(255);
    set @name='vb';
    EXEC proc_meetinginfo2 @name
  ```
  - 执行2  
    ``` EXEC proc_meetinginfo2 'vb' ```  
    
  error: name为 关键字  

3. 带输出参数存储过程  
  ```sql
  create proc proc_meetinginfo3(
    @name varchar(20) out, --输出参数
    @no varchar(20) output--输入输出参数
  )
  as
    select @name = meeting_name, @no = MEETING_NO from MEETING_INFO where meeting_no = @no;
  go
  执行
  declare 
    @name varchar(20),
    @no varchar(20);
  set @no = '2015-01';
  exec proc_meetinginfo3 @name out, @no output;
  select @name as name, @no as no; 查询返回的参数
  print @name + '#' + @no  消息中显示返回的参数
  ```

### mysql 存储过程

关键字:  
  procedure begin end   
  call  
创建执行:  
```sql
  drop procedure if EXISTS hh;
  create procedure hh()
  BEGIN
    select * from operate_log;
  END
  ;
  call hh;
```
