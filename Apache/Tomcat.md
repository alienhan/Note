1. Ĭ�Ϸ���
2. tomcat��������ssl
3. tomcat������https����
4. eclipse tomcat servers �����ļ�
5. context.xml
6. tomcat-user.xml�������������tomcat���û�
7. tomcatͨ��conf-Catalina-localhostĿ¼������Ŀ
8. tomcat �·���localhost����
9. ������Ŀ
10. tomcat ���÷���������,����eclipse��debug���� || eclipse ����Զ��tomcat || tomcat����������Զ�̵���

----------------------------------------------------------------------------------------------------------------------------------------------------------
1. Ĭ�Ϸ���
tomcatĬ�Ϸ���ROOT Ӧ�ó���

----------------------------------------------------------------------------------------------------------------------------------------------------------
2. tomcat��������ssl


1):����keystore�ļ�
keytool -v -genkey -alias tomcat -keyalg RSA -keystore d:/tomcat.keystore
ע�⣺
      cn=localhost

2):��keystore�е���server.cer���ļ�
   keytool -export -trustcacerts -alias tomcat -file d:/server.cer -keystore d:/tomcat.keystore -storepass 123456


3):��server.cer�ļ����뵽jdk�еĴ���֤��Ŀ���
keytool -import -trustcacerts -alias tomcat -file d:/server.cer -keystore D:\Java\jdk1.6.0_19/jre/lib/security/cacerts -storepass changeit


4):����tomcat��conf�ļ��µ�sever.xml�ļ�
<Connector port="8443" protocol="HTTP/1.1" SSLEnabled="true"
               maxThreads="150" scheme="https" secure="true"
               clientAuth="false" keystoreFile="D:\tomcat.keystore" keystorePass="123456" sslProtocol="TLS" />

5)��װserver.cer֤��
  ��server.cer��װ�������εĸ�Ŀ¼��
-------------------------------------------------------------------------------------------------------------------------------------------------------------
3. tomcat������https����

example:
http://blog.csdn.net/supersky07/article/details/7407523


[tomcat������https������֤�����˫����֤��]
tomcat ����֤�������
keytool -import -alias rootca.cer -keystore your_keystore 
 -storepass your_pass -trustcacerts -file c:\rootca.cer
�����֤��ʱ��������Ҫ�ͷ�����֤��alias������ͬ��

���������֤��ʱ����ע���Լ���alias��

����ǵ���SSL��
ֻ��Ҫ�ڷ���˲���SSL֤�鼴��ʵ��https://���ܷ��ʣ�
���˫����֤�������SSL+�ͻ���֤����֤����
Ҳֻ��ҪWeb Server�����ü��ɣ���ȫ����Ҫд���롣

jdk�Դ���keytool��openssl

1)��keytool �� openssl���ǹ��ߡ�
������Ǹ���Ӧ�÷���������web server��ͬ��
��ʹ�ö�Ӧ�Ĺ���.tomcat��Ҫʹ��keytool���ߡ�

2)��cer��crt����֤���ʽ������webserver 
����Ӧ�÷�����֧�����־ͽ�֤��ĳ��ĸ����ܼ򵥡�
JKS��һ�ָ�ʽ��һ��ʹ��openssl�Ķ���jks��ʽ����key��ʽ��
ʹ��keytool�Ķ���keystore��ʽ��

3)�����ڵ����֤�����⣬һ�㶼Ҫ�����֤�顣
�ͻ���֤������˵�ĵ��룬��û���ס��Ҿ��ÿͻ��˲���Ҫ�ٵ����˰ɣ�
�ڷ���������˫����֤��Ҳ����Ҫ���룬ֻ��Ҫ�ӷ�����������һ�¼��ɡ�
tomcat��֤�鵼�룬����Ҫ�����֤�飨������м���֤�飬Ҳ����Ҫ����ģ�

-------------------------------------------------------------------------------------------------------------------------------------------------------------
4. eclipse tomcat servers �����ļ�

server.xml

<!--���������Ŀ¼,�Ƿ����¼���-->
<Context docBase="dev_pss" path="/dev_pss" 
reloadable="true" source="org.eclipse.jst.jee.server:dev_pss"/>

-------------------------------------------------------------------------------------------------------------------------------------------------------------
5. context.xml
context.xmlһ����Catalina�µ��ļ�
�������û�����.
���ǿ�������Ŀ��ʱ��
һ�㶼����������ļ���������һ����Ŀ��
�����ǰ���Ŀ������webapp��ȥ,

��Ŀ�ײ�λ��
<Context path="/pss-dev"
docBase="E:/worksapce/wondersWorkspace/dev_pss/WebRoot">

���ݿ�
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

------------------------------------------------------------------------------------------------------------------------------------------------------------------
7. tomcatͨ��conf-Catalina-localhostĿ¼������Ŀ

ע�⣺xml���ļ���һ��Ҫ�ͷ���·��һ�£�

�������ò�����eclipseֱ�ӽ���Ŀ���ص�tomcat�У���˲�����eclipse��������Ŀ
ֱ������tomcat���С�

(���)
eclipse3.2�е�tomcat����Ϳ���ֱ������tomcat��������eclipse����������



-----------------------------------------------------------------------------------------------------------------------
8. tomcat �·���localhost����
 ������tomcat����·�������⣬�ı��127.0.0.1����

-----------------------------------------------------------------------------------------------------------------------
9. ������Ŀ
	tomcat6������Ŀ��
		1.�����ⲿ�ļ�
			����%tomcat%/conf/Catalina/localhost/xx.xml
		2.�ڶ��η�����ʱ�򣬽�����ԭ������
		3.��ȷ����tomcat

-----------------------------------------------------------------------------------------------------------------------
10. tomcat ���÷���������,����eclipse��debug���� || eclipse ����Զ��tomcat || tomcat����������Զ�̵���
������tomcat��:
	bin->catalina.bat -->

	set JAVA_OPTS=%JAVA_OPTS% %LOGGING_MANAGER%
	set CATALINA_OPTS=-server -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000
	rem ----- Execute The Requested Command ---------------------------------------



����eclipse��:
	servers --> tomcat 6.x --> ��Ŀ -->right click -> Remote Java Application --> Coonect -->
	���ñ�����Ŀ,Զ�̷�������ַ,�˿�

----------------------------------------------------------------------------------------------------------------------



=========================================================================================================================================
=========================================================================================================================================

error

jetty û������,tomcat�Ͼ�������
SAX2 driver class org.apache.xerces.parsers.SAXParser not found
���:
https://stackoverflow.com/questions/11677572/dealing-with-xerces-hell-in-java-maven

<dependency>
    <groupId>xerces</groupId>
    <artifactId>xercesImpl</artifactId>
    <version>2.0.2</version>
</dependency>