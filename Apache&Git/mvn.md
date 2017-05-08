  
1. maven简介：  
2. settings.xml ,pom.xml  
3. demo  
4. mvn error  
5. 安装，配置  
6. 引用框架包配置(http://mvnrepository.com)  
7. 配置mirrors ，repository  
8. 解决Eclipse建立Maven项目后无法建立src/main/java资源文件夹的办法  
9. mvn 命令  
10. mvn commond build webapp java web project  
11. nux Nexus  
12. eclipse maven plugin  
13. mvn spring   
14. mvn update  
15. eclipse create mvn project  
16. mvn 引用不到链接sqllserver的jar包  
  
-------------------------------------------------------
远程中央仓库的地址为 http://repo1.maven.org/  
http://search.maven.org  
http://maven.oschina.net/home.html  
http://mvnrepository.com/  是mvn官方仓库  
  
  
------------------------------------------------------------------------------------------  
1. maven简介：  
  
作用：  
用来帮助实现项目的构建、测试、打包和部署  
  
原理：  
Maven是基于项目对象模型(POM)，  
可以通过一小段描述信息来管理项目的构建，报告和文档的软件项目管理工具。  
可以多个项目中共享JAR  
  
生命周期:  
当你运行 mvn install 的时候被调用。  
这条命令告诉 Maven 执行一系列的有序的步骤，  
直到到达你指定的生命周期。遍历生命周期旅途中的一个影响就是，  
Maven 运行了许多默认的插件目标，这些目标完成了像编译和创建一个 JAR 文件这样的工作。  
此外，Maven能够很方便的帮你管理项目报告，生成站点，管理JAR文件，等等。  
  
优点：  
在Java项目中,每个项目都有很多依赖的Jar包,  
我们一般都放在自己项目的lib目录中,  
这样就会导致同样的Jar包在各个不同的项目中重复出现，  
时间长了将会浪费很大的硬盘空间。使用Maven对项目进行管理就可以解决以上问题。  
  
Maven执行原理：  
依赖和项目构建的输出都可以由 Maven 的坐标进行唯一的区分，  
基于这种机制，Maven 将所有项目的构件文件放置在一个统一的位置，  
也就是 Maven 仓库。  
所有 Maven 项目可以从同一个 Maven 仓库中获取自己所需要的依赖 JAR，  
这节省了磁盘资源。实际的 Maven 项目中不需要存储依赖的文件，  
只需要在 POM 文件中生成依赖关系，在构建的时候 Maven 就会自动去仓库中下载。  
  
依靠包查询：  
我们项目中依赖的 Jar 包可以通过依赖的方式引入  
maven是单独管理一个项目。的jar包位置，组织形式的。  
在安装了 Maven 的机器上，会生成一个 ~\.m2\repository 目录，  
这个目录被称为本地仓库，当 Maven 查找需要的依赖时，首先会在本地查找，  
如果本地仓库中存在，则直接使用，否则 Maven 回去远程仓库查找，  
查找到后下载到本地进行使用。  
  
------------------------------------------------------------------------------------------  
2. settings.xml ,pom.xml  
  
配置文件作用域：  
conf/settings.xml  
  
修改maven本地仓库的位置：  
apache-maven-3.1.2/conf/setting.xml  
<localRepository>d:maven2</localRepository>  
当修改过后，不会自动新建/.m2文件  
  
settings.xml对于maven来说相当于全局性的配置，用于所有的项目  
加载的方向正好相反，下级影响上级  
  
  
  
  
pom.xml节点的说明：  
<project>　　　　　　pom文件的顶级节点  
　　　　<modelVersion>　　　object model版本，对Maven2和Maven3来说，只能是4.0.0　  
　　　　<groupId>　　　　　　项目创建组织的标识符，一般是域名的倒写  
　　　　<artifactId>　　　　定义了项目在所属组织的标识符下的唯一标识，一个组织下可以有多个项目  
　　　　<packaging>　　　　  打包的方式，有jar、war、ear等  
　　　　<version>　　　　　  当前项目的版本，SNAPSHOT，表示是快照版本，在开发中  
  　　　<name>　　　　　　　 项目的名称  
　　　　<url>　　　　　　　　项目的地址  
　　　　<dependencies>　　 构建项目依赖的jar  
　　　　<description>　　　　项目的描述  
  
------------------------------------------------------------------------------------------  
3. demo  
  
mvn dependency search查询位置  
  
1.mvn archetype:generate -DarchetypeCatalog=internal  
  或mvn archetype:generate（简单）  
  
2.mvn eclipse:eclipse 在eclipse工作目录下运行  
3.mvn tomcat:run  
4.指定eclipse中的maven的本地仓库地址,运行命令:   
mvn  -Declipse.workspace= <eclipse   workspace路径>   eclipse:add-maven-repo   
  
创建依赖  
<dependency>  
   <groupId>javax.servlet</groupId>  
   <artifactId>servlet-api</artifactId>  
   <version>2.5</version>  
</dependency>  
              
http://www.jarvana.com/jarvana/   
------------------------------------------------------------------------------------------  
4. mvn error  
  
1）.出现创建不了mvn project   
错误：can mot resolve archetype  
windows - preference - maven -archetype  
add remote catalog  
http://repo1.maven.org/maven2/archetype-catalog.xml  
  
2）.  
<project xmlns="http://maven.apache.org/POM/4.0.0"  
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0   
http://maven.apache.org/maven-v4_0_0.xsd">  
  
报错：  
Description Resource Path Location Type   
Could not calculate build plan:   
Failure to transfer org.apache.maven.plugins:maven-war-plugin:pom:2.1.1 from   
http://repo1.maven.org/maven2 was cached in the local repository,  
resolution will not be reattempted until the update interval  
of central has elapsed or updates are forced. Original error:  
Could not transfer artifact org.apache.maven.plugins:maven-war-plugin:pom:2.1.1   
from/to central (http://repo1.maven.org/maven2): ConnectException  
first-maven Unknown	Maven Problem  
解决：  
应该是连不上http://repo1.maven.org/maven2这个仓库，在pom.xml文件加一下下面的配置   
```
<repositories>    
    <repository>    
      <snapshots>    
        <enabled>false</enabled>    
      </snapshots>    
      <id>central</id>    
      <name>Maven Repository Switchboard</name>    
      <url>http://repo2.maven.org/maven2</url>    
    </repository>    
</repositories>   
```
  
  
3）分析：  
./m2/settings 影响默认conf/settiongs的配置  
java.lang.ClassNotFoundException: org.springframework.web.context.ContextLoaderListener  
  
4）  
Failed to execute goal org.apache.maven.plugins:maven-archetype-plugin错误解决方法   
解决方法：将命令中的create改为generate：  
mvn archetype:generate -DgroupId=com.tutorialspoint.test   
-DartifactId=helloworld -DarchetypeArtifactId=maven-archetype-webapp  
  
5)Java compiler level does not match the version of the installed  
  Java project facet.SSJ UnknownFaceted Project Problem (Java Version Mismatch)	  
  properties-> java complier -> 改成与版本相同的jre  
  
------------------------------------------------------------------------------------------  
5. 安装，配置  
  
1） 解压maven压缩包  
2） 配置环境变量，添加%MAVEN_HOME%,path中添加%MAVEN_HOME%\bin  
3） 测试安装是否成功，mvn -v  
4） 安装eclipse的插件  
5） eclipse创建maven项目  
6） eclipse添加maven的配置  
        进入MyEclipse→window→Preferences→  
	Maven4MyEclipse→Maven→Installations→User Sittings  
        点击右侧Browse指向%MAVEN_HOME%\conf\settings.xml  
7）  
  
------------------------------------------------------------------------------------------  
6. 引用包配置(http://mvnrepository.com)  
     
搜索结果     
   引用spring mvc  
   Spring Web MVC  
  
   引用hibernate  
   Hibernate Core Aggregator   
  
   引用struts  
   struts 2  
   struts-core   
  
------------------------------------------------------------------------------------------  
7. 配置mirrors ，repository  
  
  覆盖中央仓库的默认地址，  
  那么这里我们就会使用的镜像了，在setting.xml里面配置  
  <mirrorOf>,表示只为central仓库做镜像，  
  如果想为所有的仓库做镜像那么可以改为： <mirrorOf>*</mirrorOf>  
```
  <mirrors>  
	<mirror>  
		<id>repo1</id>  
		<name>repo1</name>  
		<url>http://repo1.maven.org/maven2/spring/</url>  
		<mirrorOf>central</mirrorOf>    <!--表明为central中央仓库配置镜像仓库-->  
	</mirror>  
  </mirrors>  
  <profiles>   
	    
		<profile>  
		  <id>repo</id>    
		  <repositories>  
			   <repository>    
				  <id>com.springsource.repository.maven.release</id>    
				  <url>http://maven.springframework.org/release/</url>    
				  <snapshots><enabled>false</enabled></snapshots>    
			   </repository>   
		  </repositories>  
	   </profile>  
  
  </profiles>  
   <activeProfiles>    
	 <activeProfile>repo</activeProfile>  	  
  </activeProfiles>    
```
 
------------------------------------------------------------------------------------------  
8. 解决Eclipse建立Maven项目后无法建立src/main/java资源文件夹的办法  
在项目上右键选择properties，  
然后点击java build path，在Librarys下，  
编辑JRE System Library，  
选择workspace default jre就可以了。  
  
------------------------------------------------------------------------------------------  
9. mvn 命令  
  
运行mvn command 必须在有pom.xml文件的父文件夹  
  
  
生成清除Eclipse项目结构：  
mvn eclipse:eclipse  
mvn eclipse:clean  
  
编译项目到target、安装项目到.m2文件夹下的资源库中  
mvn install  
  
清理（删除target目录下编译内容）  
mvn clean  
  
仅打包Web页面文件  
mvn war:exploded  
  
编译项目  
mvn compile  
  
打包发布  
mvn package  
  
打包时跳过测试  
mvn package -Dmaven.test.skip=ture  
  
编译源代码： mvn compile   
编译测试代码：mvn test-compile      
运行测试：mvn test    
  
安装第三方包  
经常有第三方包，因为一些原因，在网上repository上没有，需要自己动手安装。  
比如sun某些版本的jar文件，比如oracle的驱动。  
已oracle驱动程序为例，比如驱动路径为c:/driver/ojdbc14.jar，  
是10.2.0.3.0版本  
在该网址能够查到：  
http://www.mvnrepository.com/artifact/com.oracle/ojdbc14  
artifactId和groupId。  
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.3.0 -Dpackaging=jar -Dfile=c:/driver/ojdbc14.jar  
这样就可以在pom中依赖引用了：  
<dependency>  
    <groupId>com.oracle</groupId>  
    <artifactId>ojdbc14</artifactId>  
    <version>10.2.0.3.0</version>  
</dependency>  
------------------------------------------------------------------------------------------  
10. mvn commond build  java web project  
  
z.	zero  
	error:		当使用maven-3.1.0时报引用包错误。找不到xxx-2.0.6  
	selution:	更换3.2.6就可以了，它引用2.1.0 plugin包  
  
a. 创建：  
	mvn archetype:create -DgroupId=com.learn -DartifactId=LearnNew -DarchetypeArtifactId=maven-archetype-webapp  
	error:		[ERROR] Failed to execute goal org.apache.maven.plugins:maven-archetype-plugin:2  
			.4:create (default-cli) on project standalone-pom: Unable to parse configuration  
	solution:	可以将 create 换成 generate  
  
b.	生成elipse项目  
	mvn eclipse:eclipse  
	待解决：  
	error :		缺包  
				Failed to execute goalk org.apache.maven.plugins:maven-eclipse-plugin:2.1.0  
	solution:	手动添加jar包到本地仓库  
	mvn install:install-file -Dfile=jar包的位置 -DgroupId=groupId -DartifactId=artifactId -Dversion=version -Dpackaging=jar  
  
c.	eclipse 添加maven生成的工程  
	export-> exists maven project  
	导入后自动生成 .classpath  .project  
	但是不能mvn configration file 会报找不到文件错误  
  
	也可以在myeclipse中创建文件  
  
d. 运行：  
	Run -> mvn install -> run as java application  
	没有mvn intall则找不到.class类文件   
	mvn install 是将你打好的jar包安装到你的本地库中，一般没有设置过是在 用户目录下的 .m2\下面。  
	mvn package 只是将你的代码打包到输出目录，一般的是 target下面  
  
e. 作为web项目运行  
   增加MyEclipse的Web Project功能  
   选择工程-->MyEclipse-->Project Capabilities-->Add Web Project Capabilites，  
   弹出窗口中设置参数如下，再接下来的弹出窗口中选择yes。  
   这样我们的工程就是web工程了(注意工程图标有变化，而且MyEclipse自动依赖了JEE的包)。  
  
f. 修改根路径名  
   Myeclipse-->Web  
   这一个是用来修改设置工程的web特性  
  
g. 当页面还是报404 的时候可能是myeclipse的tomcat缓存  
	删除tomcat路径下的项目，就可以了  
  
  
------------------------------------------------------------------------------------------  
11. Nexus  
  
Synatype Nexus  
  
maven 配置依赖nexus  
<repositories>  
	 <repository>  
		 <id>nexus</id>  
		 <name>Team Nexus Repository</name>  
		 <url>http://localhost:8081/nexus/content/groups/public</url>  
	 </repository>  
</repositories>  
<pluginRepositories>  
	 <pluginRepository>  
		 <id>nexus</id>  
		 <name>Team Nexus Repository</name>  
		 <url>http://localhost:8081/nexus/content/groups/public</url>  
	 </pluginRepository>  
</pluginRepositories>  
  
------------------------------------------------------------------------------------------  
12. eclipse maven plugin  
  
	1）下载eclipse maven plugin插件  
	2）在eclipse安装文件夹下创建links文件夹  
			新建maven.link文件：  
			内容为：path=D:/java/eclipse/myplugins/maven  
  
	3）在eclipse安装文件夹下创建myplugins文件  
			将maven插件解压到当前文件夹下  
  
	4）重启eclipse  
	5）配置eclipse maven plugin   
  
------------------------------------------------------------------------------------------  
13. mvn spring   
  
.classpath  
	error:  
		mvn java.lang.ClassNotFoundException: org.springframework.web.context.  
				  ContextLoaderListener  
		根本原因是eclipse只是添加了类的引用  
	1.solution:  
		项目根目录下的.classpath,找到  
		<classpathentry kind="con" path="org.maven.ide.eclipse.MAVEN2_CLASSPATH_CONTAINER"/>  
  
		替换为:  
		<classpathentry kind="con" path="org.maven.ide.eclipse.MAVEN2_CLASSPATH_CONTAINER">  
			 <attributes>  
				 <attribute name="org.eclipse.jst.component.dependency" value="/WEB-INF/lib"/>  
			 </attributes>  
		</classpathentry>  
	2.   
		1.右键点击项目--选择Properties选择Deployment（部署） Assembly（程序集）,  
		在右边点击Add按钮，在弹出的窗口中选择Java Build Path Entries  
		2.点击Next，选择Maven Dependencies  
		3.点击Finish，然后可以看到已经把Maven Dependencies添加到Web应用结构中了  
	  
  
添加maven库包的引用  
<classpathentry kind="con" path="org.maven.ide.eclipse.MAVEN2_CLASSPATH_CONTAINER"/>  
  
  
------------------------------------------------------------------------------------------  
14. mvn update  
	  
	1.在eclipse中对项目右键点击 maven --> update dependencies,这样对maven库进行更新。  
	  
	2.update Project Configration 会根据maven默认的设置改变项目的配置文件  
		例：可以改变.classpath文件  
		（不要轻易使用）  
  
  
  
------------------------------------------------------------------------------------------  
15. eclipse create mvn project  
  
1. new->mvn project  
2. 生成web项目 properties->project facets->Dynamic Web Module  
   注意添加的web文件的位置  
  
3. 配置build path 新建java ，配置编译文件位置,  
   配置java版本  
  
4. 配置maven引用包编译位置 deployment assembly ->add -> java build enties path  
  
5. 添加tomcat lib jar  
  
1. 创建：  
mvn archetype:create -DgroupId=com.learn -DartifactId=LearnNew -DarchetypeArtifactId=maven-archetype-webapp  
若是缺少maven-archetype-plugin:2.4:create 则将create变成generate   
  
-----------------------------------------------------------------------------
mvn 引用不到链接sqllserver的jar包  
    Missing artifact com.microsoft.sqlserver:sqljdbc4:jar:4.0  

  
1. 在这个网站下载jdbc驱动包
    https://www.microsoft.com/en-us/download/details.aspx?id=11774  
  
2. 手动添加sqlserver jdbc驱动包到mvn仓库中。  
cd到jar包的路径下  
    ```
	mvn install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar
    ```  

-----------------------------------------------------------------------------
新建maven多子项目，想要添加的子项目不在父项目下(与父项目并行)

1. file-->import-->General-->Existing Project into Workspace
2. 删除掉父项目下的文件夹即可

-----------------------------------------------------------------------------


