---
title: java配置\安装
date: 2015/8/8
categories:
- java基础
tags:
- jdk配置\安装
---

1.jdk配置
2. keytools
3. list
5. java泛型
7. 实参个数可变
8. windows多版本jdk

java 安装，配置相关

### jdk配置
->系统-》高级环境变量  
系统变量配置  
classpath， 最后一个为tomcat配置  

    C:\Program Files(x86)\Java\jdk1.6.0_19\lib\rt.jar;
    C:\Program Files (x86)\Java\jdk1.6.0_19\lib\tools.jar;
    D:\tomcat\apache-tomcat-7.0.32;D:\tomcat\apache-tomcat-6.0.3

java_home   

    C:\Program Files (x86)\Java\jdk1.6.0_19


path  

    %M2%;%JAVA_HOME%\bin;
    C:\Program Files (x86)\Microsoft Visual Studio\Common\Tools\WinNT;
    C:\Program Files (x86)\Microsoft Visual Studio\Common\MSDev98\Bin;
    C:\Program Files (x86)\Microsoft Visual Studio\Common\Tools;
    c:\Program Files (x86)\Microsoft SQL Server\90\Tools\binn\;
    C:\Program Files (x86)\Microsoft SQL Server\100\Tools\Binn\;
    C:\Program Files\Microsoft SQL Server\100\Tools\Binn\;
    C:\Program Files\Microsoft SQL Server\100\DTS\Binn\;
    C:\Program Files (x86)\Microsoft SQL Server\100\Tools\Binn\VSShell\Common7\IDE\;
    C:\Program Files (x86)\Microsoft SQL Server\100\DTS\Binn\;C:\Program Files (x86)\Microsoft Visual Studio 9.0\Common7\IDE\PrivateAssemblies\;D:\mysqlserver32\mysql-5.1.66-winx64\bin

### keytools
jdk密钥生成工具  

Keytool是一个Java数据证书的管理工具。  

#### keystore   
Keytool将密钥（key）和证书（certificates）  
存在一个称为keystore的文件中   
在keystore里，包含两种数据：   
密钥实体（Key entity）——密钥（secret key）   
又或者是私钥和配对公钥（采用非对称加密）   
可信任的证书实体（trusted certificate entries）——只包含公钥   

Alias（别名）   
每个keystore都关联这一个独一无二的alias，这个alias通常不区分大小写     

#### cacerts证书文件(The cacerts Certificates File)   
改证书文件存在于java.home\lib\security目录下，是Java系统的CA证书仓库   

    %Java_home%\bin\keytool -genkey -alias tomcat -keyalg RSA  

### windows多版本jdk
	将jdk8，jdk6安装，复制安装文件后
	卸载两个jdk，这样就只有java_home影响系统的jdk版本
