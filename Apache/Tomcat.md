---
title: tomcat
date:
categories:
- tomcat
tags:
- tomcat
---

### 默认访问
tomcat默认访问ROOT 应用程序

### tomcat单项配置ssl
1. 生成keystore文件  
```
keytool -v -genkey -alias tomcat -keyalg RSA -keystore
d:/tomcat.keystore
```
注意：
      cn=localhost

2. 从keystore中导出server.cer的文件  
```
   keytool -export -trustcacerts -alias tomcat -file
   d:/server.cer -keystore d:/tomcat.keystore -storepass 123456
```

3. 将server.cer文件导入到jdk中的储存证书的库中  
```
keytool -import -trustcacerts -alias tomcat -file d:/server.cer -keystore  
D:\Java\jdk1.6.0_19/jre/lib/security/cacerts -storepass changeit
```

4. 配置tomcat的conf文件下的sever.xml文件  
```
<Connector port="8443" protocol="HTTP/1.1" SSLEnabled="true"
               maxThreads="150" scheme="https" secure="true"
               clientAuth="false" keystoreFile="D:\tomcat.keystore" keystorePass="123456" sslProtocol="TLS" />
```
5. 安装server.cer证书  
  将server.cer安装到受信任的根目录下

### tomcat下配置https环境
example:  
  http://blog.csdn.net/supersky07/article/details/7407523

tomcat下配置https环境后证书错误（双向认证）
tomcat 配置证书的命令  
```
keytool -import -alias rootca.cer -keystore your_keystore 
 -storepass your_pass -trustcacerts -file c:\rootca.cer
``` 
导入根证书时，别名不要和服务器证书alias别名相同。   
导入服务器证书时，请注意自己的alias。  

如果是单向SSL，  
只需要在服务端部署SSL证书即可实际https://加密访问，  
如果双向认证（服务端SSL+客户端证书认证），  
也只需要Web Server上配置即可，完全不需要写代码。  
  
jdk自带的keytool与openssl  

1)、keytool 与 openssl都是工具。  
区别就是根据应用服务器或者web server不同，  
而使用对应的工具.tomcat需要使用keytool工具。  

2)、cer与crt都是证书格式，看你webserver   
或者应用服务器支持哪种就将证书改成哪个，很简单。  
JKS是一种格式。一般使用openssl的都是jks格式或者key格式，  
使用keytool的都是keystore格式。  

3)、至于导入根证书问题，一般都要导入根证书。  
客户端证书你所说的导入，我没明白。我觉得客户端不需要再导入了吧，
在服务器上做双向认证，也不需要导入，只需要从服务器上配置一下即可。
tomcat的证书导入，是需要导入根证书（如果有中级根证书，也是需要导入的）  

### eclipse tomcat servers 配置文件

server.xml
```
<!--浏览器访问目录,是否重新加载-->
<Context docBase="dev_pss" path="/dev_pss" 
reloadable="true" source="org.eclipse.jst.jee.server:dev_pss"/>
```
### context.xml
context.xml一个是Catalina下的文件  
用来配置环境的.  
我们开发大项目的时候  
一般都是配置这个文件的来添加一个项目的  
而不是把项目拷贝到webapp下去,  
  
项目底层位置  
```
<Context path="/pss-dev"
docBase="E:/worksapce/wondersWorkspace/dev_pss/WebRoot">
```
数据库
```
<Resource
            name="jdbc/pss"
            type="javax.sql.DataSource"
            driverClassName="oracle.jdbc.driver.OracleDriver"
            url="jdbc:oracle:thin:@10.1.8.217:1521:sck"
            username="PSS"
            password="xdrcft56"
            maxIdle="2"
            maxWait="5000"
            maxActive="4" />
```
### tomcat通过conf-Catalina-localhost目录发布项目  

注意：xml的文件名一定要和发布路径一致！ 

这样配置不经过eclipse直接将项目加载到tomcat中，因此不用再eclipse中启动项目
直接驱动tomcat就行。  

(配合)  
eclipse3.2中的tomcat插件就可以直接启动tomcat，不是在eclipse中虚拟启动

### tomcat 下访问localhost错误  
 可能是tomcat加载路径的问题，改变成127.0.0.1试试

### 发布项目
	tomcat6发布项目：
		1.配置外部文件
			配置%tomcat%/conf/Catalina/localhost/xx.xml
		2.第二次发布的时候，将保存原有配置
		3.正确重启tomcat

### tomcat 配置服务器监听
  用于eclipse的debug功能 || eclipse 调试远程tomcat || tomcat服务器配置远程调试    
服务器tomcat端:   
```
	bin->catalina.bat -->
	set JAVA_OPTS=%JAVA_OPTS% %LOGGING_MANAGER%  
	set CATALINA_OPTS=-server -Xdebug -Xnoagent
   -Djava.compiler=NONE
    -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000
	rem ----- Execute The Requested Command 
```  



本地eclipse端:  
	servers --> tomcat 6.x --> 项目 -->right click -> Remote Java Application --> Coonect -->  
	配置本地项目,远程服务器地址,端口  



## error

jetty 没有问题,tomcat上就有问题  
SAX2 driver class org.apache.xerces.parsers.SAXParser not found  
解决:   
https://stackoverflow.com/questions/11677572/dealing-with-xerces-hell-in-java-maven  
```
<dependency>
    <groupId>xerces</groupId>
    <artifactId>xercesImpl</artifactId>
    <version>2.0.2</version>
</dependency>
```
