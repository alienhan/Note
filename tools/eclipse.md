---
title: eclipse
date: 2015/9/9
categories:
- tools
tags:
- tools-eclipse使用
---


1. eclipse JavaEE版本 如何发布WEB应用到tomcat中
2. eclipse 快捷键
3. eclipse error
4. Eclipse如何卸载插件
5. Eclipse 设置 编译目录
6. delete error reminder(提示)
7. eclipse svn项目导出问题
8. Building Workspace速度慢
9. Breakpoints 
10. 兼容性视图设置
11. 自动注释
12. javadoc
13. Eclipse plujins
---

### eclipse JavaEE版本 如何发布WEB应用到tomcat中

1)首先要打包的右击—》export ->web /war file  
2)将war包 文件拷贝到 webapps下，启动tomcat  直接访问即可  

### eclipse 快捷键  

ALT+shift+J	给类注释  
			建一个类,鼠标点在类名(或者方法名)处,按添加注释的快捷键  
ctrl + H 全局查找 可以设置只弹出File Search 在Customize中设置  
ctrl + shift + -> 选取单个单词  
Ctrl+Shift+ i 显示对象对应的参数  
ctrl + shift + 数字键盘/    缩略方法  
ctrl + shift + 数字键盘*    反缩略方法  
ctrl + alt + 向下箭头   复制选中  

### eclipse error
报不知名的错误：  
Servlet.service() for servlet [jsp]   
in context with path [/dew] threw exception   [javax.servlet.Servl  
解决：project ->clean ->清空当前服务器中的项目缓存。从起tomcat
  
出现没有理由的包引用错误：  
重新加载自己添加的Liberaries 就可以了  

### Eclipse如何卸载插件
eclipse 
Help ---> About Eclipse -->Installation details--->]  
选中你的插件-->Uninstall..  

### Eclipse 设置 编译目录  

right click -> properties -> Java Build Path ->Default   output folder  
最好设置在 web目录下  

### delete error reminder(提示)  
 window - properties - java - compiler - error/warm

### eclipse svn项目导出问题  
 
 1. 乱码 svn: Can't open file  导出的文件loc与.metadata下的文件冲突  
	 ->开放读写权限 ->换一个工程名可能就好了->不要变默认WebContent文件名  

### Building Workspace速度慢  

Building Workspace速度慢   
的很大一部分原因是在没必要地validate那些JS文件。  

关掉Preference -> General -> Workspace中的Build automatically,  
或者：将Preference -> MyEclipse -> Validation中的Manual以及Build关掉  
（备注：注意华丽丽的第一项：  
 Allow projects to override these preference settings）  
都解决不了本质问题，因为每个项目根目录下的  
.project文件中包含了如何Validate以及如何Build等信息  
（如上面的备注，项目的配置可能覆盖系统已经设置好的默认配置）。  

右击项目->properties->Builders-> validator  
 ->开放读写权限 ->换一个工程名可能就好了->不要变默认WebContent文件名  

### breakpoints debug Debug操作，eclipse调试程序

- tomcat 添加断点程序
Run -> Apache Tomcat -> Source -> 添加需要Debug的程序

- F5键与F6键均为单步调试，

- F5是step into,也就是进入本行代码中执行，

- F6是step over，也就是执行本行代码，跳到下一行，

- F7 继续执行当前方法，当 当前方法执行完毕的时候，控制将转到当前方法被调用的行。（慎用）

- F8是执行到最后 ,表示当前实现继续运行直到下一个断点 。(执行完这个功能)

- F6过后，查询对象的值  
  - Ctrl+Shift+ i 显示对象对应的参数  
  - step Filter 逐步过滤 一直执行直到遇到未经过滤的位置或断点  
  - resume 重新开始执行debug,一直运行直到遇到breakpoint。  
  - watch 实时地监视对象、方法或变量的变化  

- 断点属性： 
   1. hit count 执行多少次数后暂挂 用于循环
   2. enable condition 遇到符合你输入条件(为ture\改变时)就暂挂
   3. suspend thread 多线程时暂挂此线程
   4. suspend VM 暂挂虚拟机

- 异常断点  
在断点view中有一个看起来像J！的按钮，我们可以使用它添加一个基于异常的断点  

- Drop to frame就是说，可以重新跳到当前方法的开始处重新执行

- Step 过滤  
debug缺点:可能会进入到一些库的内部  
Prefrences ->search ->  step filtering  

- debug error   
当前台点击button不能定位到eclipse 的具体类的语句时，多点击几回就可以了  

### 兼容性视图设置 设置ie的兼容性。
IE  
- 兼容性视图设置可能影响  
    按钮点击不触发后台方法  
	加载jsp影响显示  


### 自动注释 导入自动注释文档

windows ->preferences ->  
java ->code style  
->code comments  

types  
```java
/** 
 * 	
 *
 * @ClassName: ${type_name} 
 * @author jh 
 * @date ${date} ${time} 
 * 
 * ${tags} 
 */
```

methods
```java
/** 
 *
 *
 * @Title: ${enclosing_method} 
 * @Author: jianghan
 * ${tags}
 *    
 */
```

->code 
new java files  
```java
/*------------------------------------------------------------------------- 
 * 版权所有：
 * 作者：姜晗
 * 联系方式：jianghan@gyyx.cn 
 * 创建时间：${date} ${time} 
 * 版本号：v1.0 
 * 本类主要用途描述： 
 * 
-------------------------------------------------------------------------*/
```

### javadoc 在eclipse自动生成javadoc

资料：  
http://blog.csdn.net/zklxuankai/article/details/8235640
