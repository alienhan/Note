## 光宇运营开发部J2EE开发环境配置指南 ##
###需要使用到的软件###

* [JDK 1.7 x64](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 
* [STS 3.5.1.RELEASE x64](http://spring.io/tools/sts/all)
* [maven 3.2.1](http://maven.apache.org)  
 *jdk & maven 推荐安装到c:\java下*  
![java dir](https://lebbdq.bn1304.livefilestore.com/y2pvKpEyUwRZ9FTBSwf_-u8tRItpM7AxTuFgAW_aPQLnTBBEqjBRYGuKU6Nfz2vXQwCfRejyQyyoi6xuzjy5GbklVatF_67KXrxfnc9IT2qWn0/javadir.png?psid=1)

###开发前要优化的部分###
* ~/.m2/目录下新增settings.xml文件内容如下，此文件会让之后加载的maven包全部从内网服务器内获取，增快访问速度。  

		<settings>
		  <mirrors>
		    <mirror>
		      <!--This sends everything else to /public -->
		      <id>nexus</id>
		      <mirrorOf>*</mirrorOf>
		      <url>http://192.168.6.192:8081/nexus/content/groups/public</url>
		    </mirror>
		  </mirrors>
		  <profiles>
		    <profile>
		      <id>nexus</id>
		      <!--Enable snapshots for the built in central repo to direct -->
		      <!--all requests to nexus via the mirror -->
		      <repositories>
		        <repository>
		          <id>central</id>
		          <url>http://central</url>
		          <releases><enabled>true</enabled></releases>
		          <snapshots><enabled>true</enabled></snapshots>
		        </repository>
		      </repositories>
		     <pluginRepositories>
		        <pluginRepository>
		          <id>central</id>
		          <url>http://central</url>
		          <releases><enabled>true</enabled></releases>
		          <snapshots><enabled>true</enabled></snapshots>
		        </pluginRepository>
		      </pluginRepositories>
		    </profile>
		  </profiles>
		  <activeProfiles>
		    <!--make the profile active all the time -->
		    <activeProfile>nexus</activeProfile>
		  </activeProfiles>
		</settings>
* 修改eclipse支持utf8编码 `window->Preferences->General->workspace->Text file encoding->Other:UTF-8`

![utf8](https://lebwfw.bn1304.livefilestore.com/y2pHzgNkqSgOvWizozF_QlYmU6wFihzPgvujy_RuJPH0ukJ56QDKI3bruPDdXCnU2-A365Ps2Aq4FnoZahuyW-FT5U2Qho7qkNbKrrJwRsOJ8c/ide-encodeing-utf.png?psid=1)
###hello world spring mvc###
* `File->New->Spring Project ` 在弹出的对话窗体内输入你的项目名，并在模板中选择 `Spring MVC Project`

![sts-1](https://lebwfw.bn1304.livefilestore.com/y2palO8xFHgD1buWAjcYvDW2udRLUHCT4cjMI4Ye2IeOliA7P5ZlTeYA8GkYqOa6BsEe8aX5X4_Cmjik62ziKCMSIGVdM4HjuhFC0n2h2IpivM/sts-1.png?psid=1)

* Next后在对话框内输入项目所在的包

![sts-2](https://lebwfw.bn1304.livefilestore.com/y2pkQlLjBNLngtw9_bdsupGWPwFzloTDv0xuKRQn9X21bpsDxIwD35t97UFFjkGY92SG-BcGWsdw-fnw4bVTwAY5stMXmT4F6WbWaMU0pVaCjg/sts-2.png?psid=1)

* Spring MVC项目目录结构

![spring mvc project](https://lebwfw.bn1304.livefilestore.com/y2pHb3yrNTq7OLXKjALfxy_ghVsYN9ZRdwErbSk3_ZgkbxPBwoJix1zfgf0CMOSMifQ8xPz9BW7FsFS71yD7bvZVeEQeMSdE6nD1woTghjOGBg/spring-mvc-project.png?psid=1)

###使用Maven向项目添加包引用###
* 使用内网[Maven代理](http://192.168.6.192:8081/nexus/)来搜索你要使用的包*本向导使用velocity为例子*

![search maven pack](https://lebwfw.bn1304.livefilestore.com/y2pzR8lQUz0gARjPqumAPjsEScTh6sbtLtDW_Cl-DL6pXugQcstbxmKL7Hi8RWnv7lVEeg-0RoR7ceoxzLN-fatHJXLv9jkH7y88I8JbY4_4jc/search-maven-pack.png?psid=1)

###让你的Spring MVC项目支持velocity模板引擎###
* 修改项目中`servlet-context.xml`文件,注释掉之前的jsp节点增加`velocityConfig`与`velocityViewResolver`节点。修改后文件如下

		<?xml version="1.0" encoding="UTF-8"?>
		<beans:beans xmlns="http://www.springframework.org/schema/mvc"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xmlns:beans="http://www.springframework.org/schema/beans"
			xmlns:context="http://www.springframework.org/schema/context"
			xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
				http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
				http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
		
			<!-- DispatcherServlet Context: defines this servlet's request-processing infrastructure -->
			
			<!-- Enables the Spring MVC @Controller programming model -->
			<annotation-driven />
		
			<!-- Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory -->
			<resources mapping="/resources/**" location="/resources/" />
		
			<!-- Resolves views selected for rendering by @Controllers to .jsp resources in the /WEB-INF/views directory -->
			<!-- 
			<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
				<beans:property name="prefix" value="/WEB-INF/views/" />
				<beans:property name="suffix" value=".jsp" />
			</beans:bean>
			 -->
			<context:component-scan base-package="cn.gyyx.java" />
			<!-- velocity -->
			<beans:bean id="velocityConfig" class="org.springframework.web.servlet.view.velocity.VelocityConfigurer">  
			    <beans:property name="resourceLoaderPath" value="/WEB-INF/views" />  
			    <beans:property name="configLocation" value="classpath:common/velocity.properties" />  
			</beans:bean>  
			  
			<beans:bean id="velocityViewResolver"  
			    class="org.springframework.web.servlet.view.velocity.VelocityViewResolver">  
			    <beans:property name="suffix" value=".vm" />  
				<beans:property name="contentType" value="text/html;charset=UTF-8" />
			</beans:bean>  			
		</beans:beans>

* 在`resources/common`目录下创建`velocity.properties`内容为

		#encoding  
		input.encoding  =UTF-8  
		output.encoding=UTF-8  
		contentType=text/html;charset=UTF-8  
		  
		#autoreload when vm changed  
		file.resource.loader.cache=false  
		file.resource.loader.modificationCheckInterval  =1  
		velocimacro.library.autoreload=false  
* 让eclipse支持velocity语法高亮，`Help->Install New Software...`在URL内直接添加veloeclipse插件本地镜像 `http://192.168.6.192/veloeclipse/` 安装veloeclipse插件即可。
* 在`views`目录下创建`home.vm`并添加如下内容

		<html>
		<head>
			<title>Home</title>
		</head>
		<body>
		<h1>
			Hello world!  
		</h1>
		
		<P>  The time on the server is ${serverTime}. </P>
		
		</body>
		</html>
* 运行你的项目测试veolocity模板吧。选择当前项目`Ctrl+F11`启动运行圣诞框，选择Run on Server。在这里我们使用默认的`VMWare vFabric tc Server Developer Edition`来运行项目。

![velocity over](https://lebwfw.bn1304.livefilestore.com/y2pmrcd_m5dO-FlHTrjr7xRcDaAjKvUe8_CNhnW_q7lJpTeKY_i7cLUQWXrB0uZeeIYT_UufDN9_iV0WoPRd3RJFFaoZd9jeN51plFKRG8qy7U/velocity-over.png?psid=1)