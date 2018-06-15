微信公众号开发

---
1.微信 mysql nickname 乱码问题: 
	java.sql.SQLException: Incorrect string value: '\xF0\x9F\x92\x94' for column 'nickname' at row 1

	1.mysql在5.5.3版本中引入新的编码格式，取名为utf8mb4，
	所以如果电脑上的版本低于此版本，请先更新电脑中的Mysql版本

	2.mysql.ini
	添加编码格式配置：
	[client]
	default-character-set = utf8mb4
	
	[mysql]
	default-character-set = utf8mb4

	[mysqld]
	character-set-client-handshake = FALSE
	character-set-server = utf8mb4
	collation-server = utf8mb4_unicode_ci

	3.mysql-connector-java
		没有问题的mysql-connector-java-5.1.39-bin.jar
	4.数据库连接池配置:
		jdbc.url=jdbc\:mysql\://localhost\:3306/ams?autoReconnect\=true&amp;failOverReadOnly\=false
		#jdbc.url=jdbc\:mysql\://localhost\:3306/ams?useUnicode\=true&amp;characterEncoding\=utf-8&amp;autoReconnect\=true&amp;failOverReadOnly\=false

---
2.			