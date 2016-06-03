slf4j
	SLF4J不同于其他日志类库，与其它有很大的不同。
	SLF4J(Simple logging Facade for Java)不是一个真正的日志实现，
	而是一个抽象层（ abstraction layer），它允许你在后台使用任意一个日志类库。
	如果是在编写供内外部都可以使用的API或者通用类库，
	那么你真不会希望使用你类库的客户端必须使用你选择的日志类库。


----------------------------------------------------------------------------------------------------------
配置slf4j,配置log4j

引入jar包：
      slf4j-api-1.5.8.jar
      slf4j-log4j12-1.5.8.jar
      log4j-1.2.17.jar	

配置slf4j： 
     

配置log4j：
	配置log4j.properties
    
        
① 配置根Logger
	配置显示日志记录的优先级
	log4j.rootLogger=warn, stdout

② 配置日志信息输出目的地Appender

log4j.appender.appenderName.option = valueN 

	valueN的值
	org.apache.log4j.ConsoleAppender（控制台）， 
	org.apache.log4j.FileAppender（文件）， 
　	org.apache.log4j.DailyRollingFileAppender（每天产生一个日志文件），
　	org.apache.log4j.RollingFileAppender（文件大小到达指定尺寸的时候产生一个新的文件）， 
　	org.apache.log4j.WriterAppender（将日志信息以流格式发送到任意指定的地方） 
   
3. 输出debug语句
	log4j.logger.org.hibernate=debug

-----------------------------------------------------------------------------------------------
web.xml配置：

<context-param>
	<param-name>log4jConfigLocation</param-name>
	<param-value>/WEB-INF/classes/log4j.properties</param-value>
</context-param>
<listener>
	<listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>
</listener>

------------------------------------------------------------------------------------------------
java 调用：

	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	
	private static Logger log = LoggerFactory.getLogger(TicketQrInfoAction.class);

--------------------------------------------------------------------------------------------------------
log4j.properties

	# Output pattern : date [thread] priority category - message   FATAL 0  ERROR 3  WARN 4  INFO 6  DEBUG 7 
	log4j.rootLogger=DEBUG,Console,File

	#Console
	log4j.appender.Console=org.apache.log4j.ConsoleAppender
	log4j.appender.Console.threshold=DEBUG
	log4j.appender.Console.layout=org.apache.log4j.PatternLayout
	log4j.appender.Console.layout.ConversionPattern=%d [%t] %-5p [%c] - %m%n

	#Springframework level
	log4j.logger.org.springframework=DEBUG
	log4j.logger.com.mybatis=DEBUG
	log4j.logger.org.apache.ibatis=DEBUG
	log4j.logger.com.ibatis = DEBUG
	log4j.logger.java.sql=DEBUG
	log4j.logger.java.sql.ResultSet=DEBUG  
	log4j.logger.org.apache=DEBUG  
	log4j.logger.java.sql.Connection=DEBUG  
	log4j.logger.java.sql.Statement=DEBUG  
	log4j.logger.java.sql.PreparedStatement=DEBUG   

	#log4j.appender.File=org.apache.log4j.DailyRollingFileAppender
	#log4j.appender.File.File=${tms.root}logs/log
	#log4j.appender.File.DatePattern='_'yyyy-MM-dd'.log' 
	#log4j.appender.File.layout=org.apache.log4j.PatternLayout
	#log4j.appender.File.layout.ConversionPattern=[TMS] %p [%t] %-d{yyyy-MM-dd HH\:mm\:ss} %C.%M(%L) | %m%n

---------------------------------------------------------------------------------------------------------

