# mybatis 自动代码生成 #


- mybatis-generator 简介：
    官网： http://www.mybatis.org/generator/index.html

---

## mybatis-generator安装： ##
1. 以maven插件安装：
```
<plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>1.3.2</version>
    <configuration>
        <verbose>true</verbose>
        <overwrite>true</overwrite>
    </configuration>
</plugin>

```

2. 配置文件：
位置：插件默认会读到src/main/resources目录下的generatorConfig.xml 文件。

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">


<generatorConfiguration>
	<!--数据库驱动jar -->
	<classPathEntry location="D:\maven\repository\mysql\mysql-connector-java\5.1.8\mysql-connector-java-5.1.8.jar" />

	<context id="DB2Tables" targetRuntime="Ibatis2Java5">
		<!--去除注释 -->
		<commentGenerator>
			<property name="suppressAllComments" value="true" />
		</commentGenerator>

		<!--数据库连接 -->
		<jdbcConnection driverClass="com.mysql.jdbc.Driver"
			connectionURL="jdbc:mysql://localhost:3306/test" userId="root"
			password="root">
		</jdbcConnection>
		<!--默认false Java type resolver will always use java.math.BigDecimal if 
			the database column is of type DECIMAL or NUMERIC. -->
		<javaTypeResolver>
			<property name="forceBigDecimals" value="false" />
		</javaTypeResolver>

		<!--生成实体类 指定包名 以及生成的地址 （可以自定义地址，但是路径不存在不会自动创建 使用Maven生成在target目录下，会自动创建） -->
		<javaModelGenerator targetPackage="jh.test.socketTest.mybatis.gen"
			targetProject="MAVEN">
			<property name="enableSubPackages" value="false" />
			<property name="trimStrings" value="true" />
		</javaModelGenerator>
		<!--生成SQLMAP文件 -->
		<sqlMapGenerator targetPackage="jh.test.socketTest.mybatis.gen"
			targetProject="MAVEN">
			<property name="enableSubPackages" value="false" />
		</sqlMapGenerator>
		<!--生成Dao文件 可以配置 type="XMLMAPPER"生成xml的dao实现 context id="DB2Tables" 修改targetRuntime="MyBatis3" -->
		<javaClientGenerator type="SPRING"
			targetPackage="jh.test.socketTest.mybatis.gen" targetProject="MAVEN">
			<property name="enableSubPackages" value="false" />
		</javaClientGenerator>

		<!--对应数据库表 mysql可以加入主键自增 字段命名 忽略某字段等 -->
		<table tableName="user" domainObjectName="User">
		</table>

	</context>
</generatorConfiguration>
```

3. 生成代码
- 择pom.xml文件，击右键先择Run AS——>Maven Build… ——>在Goals框中输入：mybatis-generator:generate 

- 如果在命令行输入Maven命令即可，注意：一定是当前项目目录下运行该命令：
mvn mybatis-generator:generate

4. 生成代码位置：
在项目target/generated-sources下
