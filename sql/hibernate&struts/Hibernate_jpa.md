hibernate基础配置 
annocation. 
jpql JPA
mvn hibernate
注释
......................................................................
1.hibernate基础配置  
     例子：hibernnate_0100 // hibernate_0100_HelloWorld
     引入必须jar包
	hibernate3.jar
	hibernate-testing.jar
	antlr-2.7.6.jar
	commons-collections-3.1.jar
	javassist-3.9.0.jar
	jta-1.1.jar
	slf4j-api-1.5.8.jar
	slf4j-log4j12-1.5.8.jar
	log4j-1.2.17.jar
	sqljdbc.jar


配置hibernate.cfg.xml文件 
	Hibernate.cfg.xml路径
	Hibernate.cfg.xml路径默认的是在src下 
	修改默认路径
	代码：
	sessionFactory = new Configuration().
	configure("WEB-INF/hibernate.cfg.xml").buildSessionFactory(); 

	hibernate-mapping配置：
	1)建立数据库连接时注意connection.url
	默认的使用jdbc连接 
	2)连接池：先主要是建立连接
	然后当需要连接时
	直接从连接池取就行
	缺点：消耗内存
	优点:不用每次都建立连接     
	3)xxx.hbm.xml作用主要是将class与table映射起来
	4)先建表，后建类
	5)配置报错： 没有dbcp时不要用
	<property name="connection.url">
		jdbc:jtds:sqlserver://localhost:1433;DatabaseName=kyc2
	</property>

...............................................................................
2. annocation
  例：hibernate_0101_annotation 
  hibernate3以后开始支持annocation
  
  annocation的jar包已经被包含在hibernate中，不用再次引用
  hibernate-annocation.jar的包已经被包含在hibernate3.jar中
  所以找不到
..........................................................................
3.jpql

JPQL是EJB2使用的查询语言EJB QL的扩展，它继承了EJB QL并对其做了一些改变。

JPQL全称Java Persistence Query Language Java持久化查询语言
特点：
	其特征与原生SQL语句类似，并且完全面向对象，通过类名和属性访问，
	而不是表名和表的属性。


select name ,age from user; //原生SQL语句
select u.name,u.age from User u;  //JPQL语句
select u from User u;  //JPQL语句

SQL语句是在数据库空间中对字段，列，行进行操作
JPQL语句是在EJB实体空间中面向对象,属性进行操作
JPQL必须由JPA的JPQL解析器解析为SQL才能执行，必须先获取Query对象

error:	不能使用 *
		unexpected token: *

.................................................................
4. JPA

http://blog.csdn.net/hmk2011/article/details/6289151
http://docs.oracle.com/javaee/5/api/javax/persistence/Query.html#getResultList%28%29



JPA全称Java Persistence API.
	JPA通过JDK 5.0注解或XML描述对象－关系表的映射关系，
	并将运行期的实体对象持久化到数据库中。

JPQL:
	JPA的查询语言是面向对象而非面向数据库的，
	它以面向对象的自然语法构造查询语句，
	可以看成是Hibernate HQL的等价物。
	JPA定义了独特的JPQL（Java Persistence Query Language），
	JPQL是EJB QL的一种扩展，它是针对实体的一种查询语言，
	操作对象是实体，而不是关系数据库的表，
	而且能够支持批量更新和修改、JOIN、GROUP BY、HAVING 等通常只有 
	SQL 才能够提供的高级查询特性，甚至还能够支持子查询。

hibernate and JPA
	JPA是需要Provider来实现其功能的，Hibernate就是JPA Provider中很强的一个，
	应该说无人能出其右。从功能上来说，
JPA就是Hibernate功能的一个子集。
	Hibernate 从3.2开始，就开始兼容JPA。
	JPA本身是一套接口和标准，我们可以使用hibernate的EntityManager模块来作为JPA的引擎

Spring  and JPA 
	Spring 2.0.1中，正式提供对JPA的支持

通过将@PersistenceContext注解标注在EntityManager类型的字段上，
这样得到的EntityManager就是容器管理的EntityManager。
由于是容器管理的，所以我们不需要也不应该显式关闭注入的EntityManager实例。

persistence.xml 配置：
	<?xml version="1.0" encoding="UTF-8"?>   
	<persistence version="1.0"  
	xmlns:persistence="http://java.sun.com/xml/ns/persistence"  
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
	xsi:schemaLocation="http://java.sun.com/xml/ns/persistence persistence_1_0.xsd ">    
	<!--    
	     Name属性用于定义持久化单元的名字 (name必选,空值也合法);   
	     transaction-type 指定事务类型(可选)    
	-->  
	<persistence-unit name="unitName" transaction-type="JTA">    
	   <!-- 描述信息.(可选) -->  
	   <description> </description>    
	   <!-- javax.persistence.PersistenceProvider接口的一个实现类(可选) -->  
	   <provider>   </provider>    
	   <!-- Jta-data-source和 non-jta-data-source用于分别指定持久化提供商使用的JTA和/或non-JTA数据源的全局JNDI名称(可选) -->  
	   <jta-data-source>java:/MySqlDS</jta-data-source>  
	   <non-jta-data-source> </non-jta-data-source>    
	   <!-- 声明orm.xml所在位置.(可选) -->  
	   <mapping-file>product.xml</mapping-file>    
	   <!-- 以包含persistence.xml的jar文件为基准的相对路径,添加额外的jar文件.(可选) -->  
	   <jar-file>../lib/model.jar</jar-file>    
	   <!-- 显式列出实体类,在Java SE 环境中应该显式列出.(可选) -->  
	   <class>com.domain.User</class>  
	   <class>com.domain.Product</class>   
	   <!-- 声明是否扫描jar文件中标注了@Enity类加入到上下文.若不扫描,则如下:(可选) -->  
	   <exclude-unlisted-classes/>   
	   <!--   厂商专有属性(可选)   -->  
	   <properties>  
	    <!-- hibernate.hbm2ddl.auto= create-drop / create / update -->  
	    <property name="hibernate.hbm2ddl.auto" value="update" />  
	    <property name="hibernate.show_sql" value="true" />  
	   </properties>   
	</persistence-unit>   
	</persistence>
.....................................
ORM 是Object-Relation-Mapping

即对象关系影射技术，是对象持久化的核心。
ORM是对JDBC的封装，从而解决了JDBC的各种存在问题

EntityManager
EntityManager 是用来对实体Bean 进行操作的辅助类。
他可以用来产生/删除持久化的实体Bean，
通过主键查找实体bean，也可以通过EJB3 QL 语言查找满足条件的实体Bean。
实体Bean 被EntityManager 管理时，
EntityManager跟踪他的状态改变，
在任何决定更新实体Bean 的时候便会把发生改变的值同步到数据库中。
当实体Bean 从EntityManager 分离后，他是不受管理的，
EntityManager 无法跟踪他的任何状态改变。
EntityManager 的获取前面已经介绍过，
可以通过@PersistenceContext 注释由EJB 容器动态注入.

Entity的获取：
  find(*.class,ID) :若实体Bean不存在,则返回null
	getReference(*,class,ID) :若实体Bean不存在,
	则抛出javax.persistence.EntityNotFoundException
  persist() :添加实体Bean
  merge () :是在实体Bean 已经脱离了EntityManager 的管理时使用，
当容器决定flush 时，数据将会同步到数据库中.
  Remove() :删除对象
  createQuery() 返回Query对象,以执行JPQL语句
  createNativeQuery() 返回Query对象,以执行SQL语句
  refresh() 刷新实体Bean,以得到对新对象
  contains() 检测实体当前是否被管理
  flush() 将实体的改变立刻刷新到数据库中

error:
javax.persistence.PersistenceException: [PersistenceUnit: itcast] class or package not found
persistences.xml不在编译路径下，mvn update 后，build project


..............................................................................
5. mvn hibernate

mvnsearch:
Hibernate Core Aggregator

	<dependency>
		<groupId>org.hibernate</groupId>
		<artifactId>hibernate</artifactId>
		<version>3.2.7.ga</version>
	</dependency>
	<dependency>
		<groupId>javax.transaction</groupId>
		<artifactId>jta</artifactId>
		<version>1.1</version>
	</dependency>
	<dependency>
		<groupId>org.hibernate.javax.persistence</groupId>
		<artifactId>hibernate-jpa-2.0-api</artifactId>
		<version>1.0.1.Final</version>
	</dependency>
	<dependency>
		<groupId>org.hibernate</groupId>
		<artifactId>hibernate-annotations</artifactId>
		<version>3.2.1.ga</version>
	</dependency>
	<dependency>
		<groupId>org.hibernate</groupId>
		<artifactId>hibernate-entitymanager</artifactId>
		<version>3.2.1.ga</version>
	</dependency>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>3.1.14</version>
	</dependency>

...................................................................................
6. 注释

    @OneToMany(mappedBy="",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    //拥有mappedBy注解的实体类为关系被维护端
    //mappedBy=""中的为多对一方中的对应属性


------------------------------------------------------------------------------------
  
