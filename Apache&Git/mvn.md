  
1. maven��飺  
2. settings.xml ,pom.xml  
3. demo  
4. mvn error  
5. ��װ������  
6. ���ÿ�ܰ�����(http://mvnrepository.com)  
7. ����mirrors ��repository  
8. ���Eclipse����Maven��Ŀ���޷�����src/main/java��Դ�ļ��еİ취  
9. mvn ����  
10. mvn commond build webapp java web project  
11. nux Nexus  
12. eclipse maven plugin  
13. mvn spring   
14. mvn update  
15. eclipse create mvn project  
16. mvn ���ò�������sqllserver��jar��  
  
..............................................  
Զ������ֿ�ĵ�ַΪ http://repo1.maven.org/  
http://search.maven.org  
http://maven.oschina.net/home.html  
http://mvnrepository.com/  ��mvn�ٷ��ֿ�  
  
  
------------------------------------------------------------------------------------------  
1. maven��飺  
  
���ã�  
��������ʵ����Ŀ�Ĺ��������ԡ�����Ͳ���  
  
ԭ��  
Maven�ǻ�����Ŀ����ģ��(POM)��  
����ͨ��һС��������Ϣ��������Ŀ�Ĺ�����������ĵ��������Ŀ�����ߡ�  
���Զ����Ŀ�й���JAR  
  
��������:  
�������� mvn install ��ʱ�򱻵��á�  
����������� Maven ִ��һϵ�е�����Ĳ��裬  
ֱ��������ָ�����������ڡ���������������;�е�һ��Ӱ����ǣ�  
Maven ���������Ĭ�ϵĲ��Ŀ�꣬��ЩĿ������������ʹ���һ�� JAR �ļ������Ĺ�����  
���⣬Maven�ܹ��ܷ���İ��������Ŀ���棬����վ�㣬����JAR�ļ����ȵȡ�  
  
�ŵ㣺  
��Java��Ŀ��,ÿ����Ŀ���кܶ�������Jar��,  
����һ�㶼�����Լ���Ŀ��libĿ¼��,  
�����ͻᵼ��ͬ����Jar���ڸ�����ͬ����Ŀ���ظ����֣�  
ʱ�䳤�˽����˷Ѻܴ��Ӳ�̿ռ䡣ʹ��Maven����Ŀ���й���Ϳ��Խ���������⡣  
  
Mavenִ��ԭ��  
��������Ŀ����������������� Maven ���������Ψһ�����֣�  
�������ֻ��ƣ�Maven ��������Ŀ�Ĺ����ļ�������һ��ͳһ��λ�ã�  
Ҳ���� Maven �ֿ⡣  
���� Maven ��Ŀ���Դ�ͬһ�� Maven �ֿ��л�ȡ�Լ�����Ҫ������ JAR��  
���ʡ�˴�����Դ��ʵ�ʵ� Maven ��Ŀ�в���Ҫ�洢�������ļ���  
ֻ��Ҫ�� POM �ļ�������������ϵ���ڹ�����ʱ�� Maven �ͻ��Զ�ȥ�ֿ������ء�  
  
��������ѯ��  
������Ŀ�������� Jar ������ͨ�������ķ�ʽ����  
maven�ǵ�������һ����Ŀ����jar��λ�ã���֯��ʽ�ġ�  
�ڰ�װ�� Maven �Ļ����ϣ�������һ�� ~\.m2\repository Ŀ¼��  
���Ŀ¼����Ϊ���زֿ⣬�� Maven ������Ҫ������ʱ�����Ȼ��ڱ��ز��ң�  
������زֿ��д��ڣ���ֱ��ʹ�ã����� Maven ��ȥԶ�ֿ̲���ң�  
���ҵ������ص����ؽ���ʹ�á�  
  
------------------------------------------------------------------------------------------  
2. settings.xml ,pom.xml  
  
�����ļ�������  
conf/settings.xml  
  
�޸�maven���زֿ��λ�ã�  
apache-maven-3.1.2/conf/setting.xml  
<localRepository>d:maven2</localRepository>  
���޸Ĺ��󣬲����Զ��½�/.m2�ļ�  
  
settings.xml����maven��˵�൱��ȫ���Ե����ã��������е���Ŀ  
���صķ��������෴���¼�Ӱ���ϼ�  
  
  
  
  
pom.xml�ڵ��˵����  
<project>������������pom�ļ��Ķ����ڵ�  
��������<modelVersion>������object model�汾����Maven2��Maven3��˵��ֻ����4.0.0��  
��������<groupId>��������������Ŀ������֯�ı�ʶ����һ���������ĵ�д  
��������<artifactId>����������������Ŀ��������֯�ı�ʶ���µ�Ψһ��ʶ��һ����֯�¿����ж����Ŀ  
��������<packaging>��������  ����ķ�ʽ����jar��war��ear��  
��������<version>����������  ��ǰ��Ŀ�İ汾��SNAPSHOT����ʾ�ǿ��հ汾���ڿ�����  
  ������<name>�������������� ��Ŀ������  
��������<url>������������������Ŀ�ĵ�ַ  
��������<dependencies>���� ������Ŀ������jar  
��������<description>����������Ŀ������  
  
------------------------------------------------------------------------------------------  
3. demo  
  
mvn dependency search��ѯλ��  
  
1.mvn archetype:generate -DarchetypeCatalog=internal  
  ��mvn archetype:generate���򵥣�  
  
2.mvn eclipse:eclipse ��eclipse����Ŀ¼������  
3.mvn tomcat:run  
4.ָ��eclipse�е�maven�ı��زֿ��ַ,��������:   
mvn  -Declipse.workspace= <eclipse   workspace·��>   eclipse:add-maven-repo   
  
��������  
<dependency>  
   <groupId>javax.servlet</groupId>  
   <artifactId>servlet-api</artifactId>  
   <version>2.5</version>  
</dependency>  
              
http://www.jarvana.com/jarvana/   
------------------------------------------------------------------------------------------  
4. mvn error  
  
1��.���ִ�������mvn project   
����can mot resolve archetype  
windows - preference - maven -archetype  
add remote catalog  
http://repo1.maven.org/maven2/archetype-catalog.xml  
  
2��.  
<project xmlns="http://maven.apache.org/POM/4.0.0"  
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0   
http://maven.apache.org/maven-v4_0_0.xsd">  
  
����  
Description Resource Path Location Type   
Could not calculate build plan:   
Failure to transfer org.apache.maven.plugins:maven-war-plugin:pom:2.1.1 from   
http://repo1.maven.org/maven2 was cached in the local repository,  
resolution will not be reattempted until the update interval  
of central has elapsed or updates are forced. Original error:  
Could not transfer artifact org.apache.maven.plugins:maven-war-plugin:pom:2.1.1   
from/to central (http://repo1.maven.org/maven2): ConnectException  
first-maven Unknown	Maven Problem  
�����  
Ӧ����������http://repo1.maven.org/maven2����ֿ⣬��pom.xml�ļ���һ�����������   
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
  
  
3��������  
./m2/settings Ӱ��Ĭ��conf/settiongs������  
java.lang.ClassNotFoundException: org.springframework.web.context.ContextLoaderListener  
  
4��  
Failed to execute goal org.apache.maven.plugins:maven-archetype-plugin����������   
����������������е�create��Ϊgenerate��  
mvn archetype:generate -DgroupId=com.tutorialspoint.test   
-DartifactId=helloworld -DarchetypeArtifactId=maven-archetype-webapp  
  
5)Java compiler level does not match the version of the installed  
  Java project facet.SSJ UnknownFaceted Project Problem (Java Version Mismatch)	  
  properties-> java complier -> �ĳ���汾��ͬ��jre  
  
------------------------------------------------------------------------------------------  
5. ��װ������  
  
1�� ��ѹmavenѹ����  
2�� ���û������������%MAVEN_HOME%,path�����%MAVEN_HOME%\bin  
3�� ���԰�װ�Ƿ�ɹ���mvn -v  
4�� ��װeclipse�Ĳ��  
5�� eclipse����maven��Ŀ  
6�� eclipse���maven������  
        ����MyEclipse��window��Preferences��  
	Maven4MyEclipse��Maven��Installations��User Sittings  
        ����Ҳ�Browseָ��%MAVEN_HOME%\conf\settings.xml  
7��  
  
------------------------------------------------------------------------------------------  
6. ���ð�����(http://mvnrepository.com)  
     
�������     
   ����spring mvc  
   Spring Web MVC  
  
   ����hibernate  
   Hibernate Core Aggregator   
  
   ����struts  
   struts 2  
   struts-core   
  
------------------------------------------------------------------------------------------  
7. ����mirrors ��repository  
  
  ��������ֿ��Ĭ�ϵ�ַ��  
  ��ô�������Ǿͻ�ʹ�õľ����ˣ���setting.xml��������  
  <mirrorOf>,��ʾֻΪcentral�ֿ�������  
  �����Ϊ���еĲֿ���������ô���Ը�Ϊ�� <mirrorOf>*</mirrorOf>  
  
  <mirrors>  
	<mirror>  
		<id>repo1</id>  
		<name>repo1</name>  
		<url>http://repo1.maven.org/maven2/spring/</url>  
		<mirrorOf>central</mirrorOf>    <!--����Ϊcentral����ֿ����þ���ֿ�-->  
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
  
------------------------------------------------------------------------------------------  
8. ���Eclipse����Maven��Ŀ���޷�����src/main/java��Դ�ļ��еİ취  
����Ŀ���Ҽ�ѡ��properties��  
Ȼ����java build path����Librarys�£�  
�༭JRE System Library��  
ѡ��workspace default jre�Ϳ����ˡ�  
  
------------------------------------------------------------------------------------------  
9. mvn ����  
  
����mvn command ��������pom.xml�ļ��ĸ��ļ���  
  
  
�������Eclipse��Ŀ�ṹ��  
mvn eclipse:eclipse  
mvn eclipse:clean  
  
������Ŀ��target����װ��Ŀ��.m2�ļ����µ���Դ����  
mvn install  
  
����ɾ��targetĿ¼�±������ݣ�  
mvn clean  
  
�����Webҳ���ļ�  
mvn war:exploded  
  
������Ŀ  
mvn compile  
  
�������  
mvn package  
  
���ʱ��������  
mvn package -Dmaven.test.skip=ture  
  
����Դ���룺 mvn compile   
������Դ��룺mvn test-compile      
���в��ԣ�mvn test    
  
��װ��������  
�����е�����������ΪһЩԭ��������repository��û�У���Ҫ�Լ����ְ�װ��  
����sunĳЩ�汾��jar�ļ�������oracle��������  
��oracle��������Ϊ������������·��Ϊc:/driver/ojdbc14.jar��  
��10.2.0.3.0�汾  
�ڸ���ַ�ܹ��鵽��  
http://www.mvnrepository.com/artifact/com.oracle/ojdbc14  
artifactId��groupId��  
mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.3.0 -Dpackaging=jar -Dfile=c:/driver/ojdbc14.jar  
�����Ϳ�����pom�����������ˣ�  
<dependency>  
    <groupId>com.oracle</groupId>  
    <artifactId>ojdbc14</artifactId>  
    <version>10.2.0.3.0</version>  
</dependency>  
------------------------------------------------------------------------------------------  
10. mvn commond build  java web project  
  
z.	zero  
	error:		��ʹ��maven-3.1.0ʱ�����ð������Ҳ���xxx-2.0.6  
	selution:	����3.2.6�Ϳ����ˣ�������2.1.0 plugin��  
  
a. ������  
	mvn archetype:create -DgroupId=com.learn -DartifactId=LearnNew -DarchetypeArtifactId=maven-archetype-webapp  
	error:		[ERROR] Failed to execute goal org.apache.maven.plugins:maven-archetype-plugin:2  
			.4:create (default-cli) on project standalone-pom: Unable to parse configuration  
	solution:	���Խ� create ���� generate  
  
b.	����elipse��Ŀ  
	mvn eclipse:eclipse  
	�������  
	error :		ȱ��  
				Failed to execute goalk org.apache.maven.plugins:maven-eclipse-plugin:2.1.0  
	solution:	�ֶ����jar�������زֿ�  
	mvn install:install-file -Dfile=jar����λ�� -DgroupId=groupId -DartifactId=artifactId -Dversion=version -Dpackaging=jar  
  
c.	eclipse ���maven���ɵĹ���  
	export-> exists maven project  
	������Զ����� .classpath  .project  
	���ǲ���mvn configration file �ᱨ�Ҳ����ļ�����  
  
	Ҳ������myeclipse�д����ļ�  
  
d. ���У�  
	Run -> mvn install -> run as java application  
	û��mvn intall���Ҳ���.class���ļ�   
	mvn install �ǽ����õ�jar����װ����ı��ؿ��У�һ��û�����ù����� �û�Ŀ¼�µ� .m2\���档  
	mvn package ֻ�ǽ���Ĵ����������Ŀ¼��һ����� target����  
  
e. ��Ϊweb��Ŀ����  
   ����MyEclipse��Web Project����  
   ѡ�񹤳�-->MyEclipse-->Project Capabilities-->Add Web Project Capabilites��  
   �������������ò������£��ٽ������ĵ���������ѡ��yes��  
   �������ǵĹ��̾���web������(ע�⹤��ͼ���б仯������MyEclipse�Զ�������JEE�İ�)��  
  
f. �޸ĸ�·����  
   Myeclipse-->Web  
   ��һ���������޸����ù��̵�web����  
  
g. ��ҳ�滹�Ǳ�404 ��ʱ�������myeclipse��tomcat����  
	ɾ��tomcat·���µ���Ŀ���Ϳ�����  
  
  
------------------------------------------------------------------------------------------  
11. Nexus  
  
Synatype Nexus  
  
maven ��������nexus  
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
  
	1������eclipse maven plugin���  
	2����eclipse��װ�ļ����´���links�ļ���  
			�½�maven.link�ļ���  
			����Ϊ��path=D:/java/eclipse/myplugins/maven  
  
	3����eclipse��װ�ļ����´���myplugins�ļ�  
			��maven�����ѹ����ǰ�ļ�����  
  
	4������eclipse  
	5������eclipse maven plugin   
  
------------------------------------------------------------------------------------------  
13. mvn spring   
  
.classpath  
	error:  
		mvn java.lang.ClassNotFoundException: org.springframework.web.context.  
				  ContextLoaderListener  
		����ԭ����eclipseֻ��������������  
	1.solution:  
		��Ŀ��Ŀ¼�µ�.classpath,�ҵ�  
		<classpathentry kind="con" path="org.maven.ide.eclipse.MAVEN2_CLASSPATH_CONTAINER"/>  
  
		�滻Ϊ:  
		<classpathentry kind="con" path="org.maven.ide.eclipse.MAVEN2_CLASSPATH_CONTAINER">  
			 <attributes>  
				 <attribute name="org.eclipse.jst.component.dependency" value="/WEB-INF/lib"/>  
			 </attributes>  
		</classpathentry>  
	2.   
		1.�Ҽ������Ŀ--ѡ��Propertiesѡ��Deployment������ Assembly�����򼯣�,  
		���ұߵ��Add��ť���ڵ����Ĵ�����ѡ��Java Build Path Entries  
		2.���Next��ѡ��Maven Dependencies  
		3.���Finish��Ȼ����Կ����Ѿ���Maven Dependencies��ӵ�WebӦ�ýṹ����  
	  
  
���maven���������  
<classpathentry kind="con" path="org.maven.ide.eclipse.MAVEN2_CLASSPATH_CONTAINER"/>  
  
  
------------------------------------------------------------------------------------------  
14. mvn update  
	  
	1.��eclipse�ж���Ŀ�Ҽ���� maven --> update dependencies,������maven����и��¡�  
	  
	2.update Project Configration �����mavenĬ�ϵ����øı���Ŀ�������ļ�  
		�������Ըı�.classpath�ļ�  
		����Ҫ����ʹ�ã�  
  
  
  
------------------------------------------------------------------------------------------  
15. eclipse create mvn project  
  
1. new->mvn project  
2. ����web��Ŀ properties->project facets->Dynamic Web Module  
   ע����ӵ�web�ļ���λ��  
  
3. ����build path �½�java �����ñ����ļ�λ��,  
   ����java�汾  
  
4. ����maven���ð�����λ�� deployment assembly ->add -> java build enties path  
  
5. ���tomcat lib jar  
  
1. ������  
mvn archetype:create -DgroupId=com.learn -DartifactId=LearnNew -DarchetypeArtifactId=maven-archetype-webapp  
����ȱ��maven-archetype-plugin:2.4:create ��create���generate   
  
-------------------------------------------------------------------------------------  
16. mvn ���ò�������sqllserver��jar��  
	Missing artifact com.microsoft.sqlserver:sqljdbc4:jar:4.0  
  
1.�������վ����jdbc������https://www.microsoft.com/en-us/download/details.aspx?id=11774  
  
2.�ֶ����sqlserver jdbc��������mvn�ֿ��С�  
cd��jar����·����  
	mvn install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar  
----------------------------------------------------------------------------------------