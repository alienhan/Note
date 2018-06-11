struts1, struts2

1. 引用包
2. struts基础
3. 传参
4. OGNL
5. demo
6. ActionForm struts1
7. ActionMapping 
8. struts1 配置
9. action 功能


...............................................................
1. 引用包

1).引入核心（core）包
	struts-2.3.4.1
		asm-3.3.jar
		asm-commons-3.3.jar
		asm-tree-3.3.jar
		commons-fileupload-1.2.2.jar
		commons-io-2.0.1.jar
		commons-lang3-3.1.jar
		freemarker-2.3.19.jar
		javassist-3.11.0.GA.jar
		ognl-3.0.5.jar
		struts2-core-2.3.4.1.jar
		xwork-core-2.3.4.1.jar


2).引入所有包
struts-2.3.15.2
错误：Unload....   struts2-gxp-plugin-2.3.15.2.jar 

............................................................................
2. struts基础

structs2.0是一种v—o的框架
  2.1package	
	    <package name="defult" extends="struts-default">      
		<action name="ahello">
		   <result>/helloworld.jsp</result>        
		</action>
	    </package>
			  
	    namespace 
	    namespace可以为空
	    namespace决定了action的访问路径
	    namespace默认为"" ，可以接受所有路径的action
	    namespace可以写为/ ，
		或者 /xxx ，对应的action为 /index.action 
		或 /xxx/index.action
	    namespace最好也用模块进行命名

	    extends（延伸；扩充）
	    extends表示引用structs.cfg.xml

  2.2Eclipes 中的位置 程序中的入口
	    右击project名 -> properties -> properties for struts
	    ->Web Project Settings -> Context root

  2.3constant(常数)
	    <constant name="struts.enable.DynamicMethodInvocation"
	    value="false" />
	    
	    <constant name="struts.devMode" value="true" />
		    表示是不是开发模式
		    开发模式：更改配置可以立即体现在浏览器中

	   
	    <constant name="struts.i18n.encoding" value="UTF-8" /> 
		    <!-- 指定Web应用的默认编码集，
		    相当于调用 HttpServletRequest的 
		    setCharacterEncoding方法 -->    
		    <!-- 
		    该属性指定需要Struts 2处理的请求后缀，
		    该属性的默认值是action，
		    即所有匹配*.action的请求都由Struts 2处理。 
		    如果用户需要指定多个请求后缀，
		    则多个后缀之间以英文逗号（，）隔开。 
		    --> 
	    
	    <constant name="struts.devMode" value="true" /> 
	    <!-- 开发模式下使用，这样可以打印出更详细的错误信息 --> 

  2.4定义当前xml文档语法的位置
    <!DOCTYPE struts PUBLIC
	  "-//Apache Software Foundation//DTD Struts Configuration 2.3//EN"
	  "http://struts.apache.org/dtds/struts-2.3.dtd">//定义当前xml文档语法的位置
  
  2.5 xml中 自动显示提示的操作 
	  1)windows ->properties->XML->XML Catalog->Add
	  2)解压structs.core任意一个jar文件后
	  3)location:文件绝对路径
	    Key Type:URL
	    Key:解压structs。core任意一个jar文件后

  2.6Action
	    <action name="ahello" 
		class="com.excute.action.NumberManagerAction">
		    <result>/helloworld.jsp</result> 
		    <result name="afirst">/first.jsp</result>   
		    <result name="asecond">/second.jsp</result>   		
	    </action>

	    action中的class的引用类要用.连接，没有.java后缀
	    name关联地址名  http://../../ahello.action

  2.7Result
	    凡是name为success的result的那么都可以省略
	    result关联的是具体的jsp文件地址，用相对路劲

  2.8action相关联的类

  2.9jsp页面得到自己的绝对路径名
	    structs2.0_0020_getpath
	    <% 
	      String path = request.getContextPath();//拿到/structs2.0_0020
	      String basepath = request.getScheme() + "://" + request.getServerName() + request.getServerPort() + path + "/";
	      //                http                   ://     localhost/               8080:   
	    %>
  2.10actionmethod
            动态传参动态转向页面出错？

  2.11  /structs2.0_0110_param   2012.12.9
	    以后复制可以以/structs2.0_0110_param为基础
	    重新更改了/structs2.0_0110_param_note
	    structs.xml添加标注
	    使之更易读
	    将permissionpage移到page目录下
	    将first ，second移到page新建的math下，添加标注（基础）
	    为完成：permissionindex.jsp
		    mvc传参
................................................................................
3. 传参

传的参数与action中的属性一一对应
从页面传到后台
接收参数 - 附到对象上 - 存到数据库中

........................................................................
4. OGNL

OGNL - 简介
OGNL是Object-Graph Navigation Language的缩写，
它是一种功能强大的表达式语言（Expression Language，简称为EL）
通过它简单一致的表达式语法，可以存取对象的任意属性，
调用对象的方法，遍历整个对象的结构图，
实现字段类型转化等功能。
它使用相同的表达式去存取对象的属性。
webwork2和现在的Struts2.x中
使用OGNL取代原来的EL来做界面数据绑定，
所谓界面数据绑定，
也就是把界面元素（例如一个textfield,hidden)
和对象层某个类的某个属性绑定在一起，修改和显示自动同步。

........................................................................
5. demo

........................................................................
6. ActionForm struts1

   struts1中的属性，struts2中不用配置
   struts2的优点之一就是与struts1API解耦
   
   ActionForm本质上是一种JavaBean，
   是专门用来传递表单数据的DTO(Data Transfer Object,数据传递对象)
   Struts框架利用ActionForm对象来临时存放视图页面中的表单数据。

   Struts将输入域的数据自动填充到相应的ActionForm对象中，
   然后控制层可以从该ActionForm对象中读取用户输入的表单数据，
   也可以把来自模型层的数据存放到ActionForm中，然后返回给视图显示。

   配置
   struts-config.xml
   <form-beans>		
	<form-bean name="dataQueryForm" type="com.wondersgroup.sabic.corp.dataquery.form.DataQueryForm" />	
   </form-beans>

   后台接受
   public ActionForward queryList(ActionForm form)

...................................................................................
7. ActionMapping 

首先明白ActionMapping一个实例对应了struts-config.xml文件中的一个<action>标签


.....................................................................................
8. struts1 配置

.....................................................................................
9. action 功能

action主要就做3件事，接受前台参数，调用业务层方法，封装数据返回给前台，
一般用异步json ，返回的对象要规范，基本属性要有，如，是否成功，失败原因，数据等，
这些都应该是公司类库有的，action 里东西不多，主要是调用，顺便补充下，
action是多例，这个要记住；

....................................................................................
