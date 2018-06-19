---
title: java操作文件
date:
categories:
- java基础
tags:
- java操作文件
---

### java 读取classpath下的文件
```java
	String menuPath = MenuComponent.class.getResource("/config/wx_menu.xml").getFile();
```

### java 读取classpath下的文件properties文件

```java
/**
 * 保存全局属性值
 */
private static Map<String, String> map = new ConcurrentHashMap<String, String>();
/**
 * 属性文件加载对象
 */
private static PropertiesLoader propertiesLoader = new PropertiesLoader("config/application.properties");

/**
 * 获取配置
 */
public static String getConfig(String key) {
	String value = map.get(key);
	if (value == null) {
		value = propertiesLoader.getProperty(key);
		if (null == value) {
			LOG.error("配置文件加载错误,{}:{}", key, value);
		}
		map.put(key, value);
	}
	return value;
}
```
