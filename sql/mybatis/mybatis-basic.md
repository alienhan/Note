1. init mybatis
2. 建立整个mybatis项目的架构体系

---
1. init mybatis
 pom.xml添加MyBatis依赖
 在resource/下添加mybatis-config.xml
 在resource/下添加sqlserver.properties

 建实体类
 建接口
 映射接口.xml
 创建factory类
 建dao类，具体调用类。
 测试

---
1.0新建mvn项目
	第一次创建的时候可能有些慢，需要引入很多工具包
		注意：
			mvn 引用不到链接sqllserver的jar包
	设置gitignore
	可以在项目下设置gitignore，这个可以和总的gitignore并行使用

---
1.1 mvn 需要引入的包
		<!-- 用于jsp使用的jar包 -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>2.5</version>
		</dependency>
		<!-- mybatis -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.4.0</version>
		</dependency>
		<!-- mysql 驱动包 -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.21</version>
		</dependency>

---
1.2 新建 src/main/resourse 包
	在包下新建mybatis-config.xml文件
	配置文件
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE configuration
	  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
	  "http://mybatis.org/dtd/mybatis-3-config.dtd">
	<configuration>
		<environments default="development">
			<environment id="development">
				<transactionManager type="JDBC" />
				<dataSource type="POOLED">
					<property name="driver" value="com.mysql.jdbc.Driver" />
					<property name="url" value="jdbc:mysql://localhost:3306/test" />
					<property name="username" value="root" />
					<property name="password" value="root" />
				</dataSource>
			</environment>
		</environments>
		<!-- mapping 文件路径配置 -->
		<mappers>
			<mapper resource="com/jh/mapping/Users.xml" />
		</mappers>
	</configuration>

---
1.3	新建实体类，
	新建sql语句实现的mapper.xml文件，
		实现mapper接口
		error:org.apache.ibatis.binding（捆绑）.BindingException: 
			  Type interface com.jh.dao.UsersMapper is not known to the MapperRegistry（注册）.
		do:	  mapper.xml namespace与mapper接口名字应该一样。
	新建mapper接口，
	新建调用sqlsessionfactory资源类

---
2. 建立整个mybatis项目的架构体系

---
2.1创建单例工厂
	新建一个同步锁锁定的工厂实现方法

---
2.2新建service接口，service实现类
	error：@Overread报错
	do:	改变本项目的编译环境，因为mvn项目默认的
		编译环境为1.5，不支持@Overread
		right click->Properties->Java Compiler 
		改成现有jdk版本

---
3. mybatis sql insert update delete

<insert id="insertUsers" parameterType="com.jh.vo.Users">
	insert into users (id,name,age) values (#{id},#{name},#{age})
</insert>

	error:### Cause: org.apache.ibatis.builder.BuilderException: Error parsing SQL Mapper Configuration. 
			  Cause: org.apache.ibatis.builder.BuilderException: Error parsing Mapper XML. 
			  Cause: org.apache.ibatis.builder.BuilderException: Error resolving class. 
			  Cause: org.apache.ibatis.type.TypeException: Could not resolve type alias 'Users'.  
			  Cause: java.lang.ClassNotFoundException: Cannot find class: Users
	do: 没有使用类的全名作为参数写入到parameterType中，出现读不到参数类型的错误，也就是找不到类，
		将类的全名写入就ok

	error:insert 无效，数据库中没有结果
	do: sqlSession 没有提交操作  
	    加语句sqlSession.commit();
		所有影响数据库数据的操作都要commit

---
<update id="updateUsers" parameterType="com.jh.vo.Users">
	update users set 
		name = #{name},
		age = #{age}
	where id = #{id}
</update>

---
自动生成id策略

	自动生成的id 最好是int类型 timestamp时间戳可以
	当使用uuid生成策略的时候，生成的id是string类型，检索的时候效率慢。（uuid 100万年会重复）

mysql 自动生成id时，默认最大id为AUTO_INCREMENT=41，属于自动生成
-
