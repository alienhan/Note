---
title: sonar
date:
categories:
- sonar
tags:
- sonar
---

### sonar分析，成功使用：

1. 客户端连接：
```xml
	maven setting.xml配置：
	 <profile>
		<id>sonar</id>
		<activation>
			<activeByDefault>true</activeByDefault>
		</activation>
		<properties>
			<!-- Optional URL to server. Default value is http://localhost:9000 -->
			<sonar.host.url>
				http://10.37.87.244:9000
			</sonar.host.url>
		</properties>
	</profile>
```
2. 启动：
```shell
	mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=true
	mvn sonar:sonar
```
3. 启动完成会发网址：
	http://10.37.87.244:9000/dashboard/index/com.suning.csiqcs.web:csiqcs-web-war
