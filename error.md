error
错误所属技术
错误原因
错误解决
------------------------------------------------------------------------------------------------
1.hibernate
Caused by: java.lang.ClassNotFoundException:
javax.persistence.EntityListene
用的是Hibernate3.6.0.final,
出现这个问题的可能原因是没有导入
hibernate-jpa-2.0-api-1.0.0.Final.jar这个包

------------------------------------------------------------------------------------------------
2.eclipse
.报不知名的错误
Servlet.service() for servlet [jsp] in context with path
[/dew] threw exception [javax.servlet.Servl
解决：project ->clean ->清空当前服务器中的项目缓存。从起tomcat

------------------------------------------------------------------------------------------------
3. java API
java.lang.NumberFormatException: For input string: ""
当你强制转换一个String为Integer时,
而这个String为""时的异常,即你的String不能转为Integer的异常

------------------------------------------------------------------------------------------------
4. struts，springMVC  中文乱码问题

url传值到Action的乱码解决方法：
	jsp页面 URL传中文参数到Action里面出现乱码，
用过滤器和控制器都解决不了，用了我两个多小时解决这个小问题！---------

原因：
默认情况下，tomcat使用的的编码方式：iso-8859-1

解决方法有以下几种：
第一种：在Action中用 new String(str.getBytes("ISO-8859-1"), "UTF-8")，进行转码，因为传递的时候，中文默人的是ISO-8859-1
					 new String(str.getBytes("GBK"), "ISO-8859-1")

第二种：可通过配置TOMCAT来解决此问题，具体解决方法如下：在tomcat的server.xml里，找到<Connector port="8080" 
              maxThreads="150" minSpareThreads="25" maxSpareThreads="75" 
              enableLookups="false" redirectPort="8443" acceptCount="100" 
              debug="0" connectionTimeout="20000" 
              disableUploadTimeout="true" useBodyEncodingForURI="true" URIEncoding="gbk">
其中是修改 disableUploadTimeout="true" useBodyEncodingForURI="true" URIEncoding="gbk" 其方法是防止在url中出现乱码
然后在每个Jsp页面添加如下代码
<%@ page pageEncoding=”gb2312″%>
<%@ page contentType=”text/html;charset=gb2312″%>
<%request.setCharacterEncoding(”gb2312″);%>

第三种：直接在ACTION中利用以下方法解决：request.setCharacterEncoding("gb2312");
        response.setCharacterEncoding("gb2312");

action,controller 返回页面的值乱码：
	前台传过来的参数
	str = new String(str.getBytes("ISO-8859-1"),"utf-8");
	返回前台的参数--下载文件时
	String str2 = new String(str.getBytes("GBK"), "ISO-8859-1");

getBytes方法：
	String的getBytes()方法是得到一个操作系统默认的编码格式的字节数组。
这个表示在不通OS下，返回的东西不一样！ 
	String.getBytes(String decode)方法会根据指定的decode编码返回某字符串在该编码下的byte数组表示，如
byte[] b_gbk = "中".getBytes("GBK"); 
byte[] b_utf8 = "中".getBytes("UTF-8");
byte[] b_iso88591 = "中".getBytes("ISO8859-1");
将分别返回“中”这个汉字在GBK、UTF-8和ISO8859-1编码下的byte数组表示，
此时b_gbk的长度为2，b_utf8的长度为3，b_iso88591的长度为1。 

	而与getBytes相对的，可以通过new String(byte[], decode)的方式来还原这个“中”字时，
这个new String(byte[], decode)实际是使用decode指定的编码来将byte[]解析成字符串。
String s_gbk = new String(b_gbk,"GBK");
String s_utf8 = new String(b_utf8,"UTF-8");
String s_iso88591 = new String(b_iso88591,"ISO8859-1");

通过打印s_gbk、s_utf8和s_iso88591，会发现，s_gbk和s_utf8都是“中”，而只有s_iso88591是一个不认识的字符，
为什么使用ISO8859-1编码再组合之后，无法还原“中”字呢，其实原因很简单，
因为ISO8859-1编码的编码表中，根本就没有包含汉字字符，
当然也就无法通过"中".getBytes("ISO8859-1");来得到正确的“中”字在ISO8859-1中的编码值了，
所以再通过new String()来还原就无从谈起了。
因此，通过String.getBytes(String decode)方法来得到byte[]时，
一定要确定decode的编码表中确实存在String表示的码值，这样得到的byte[]数组才能正确被还原。

------------------------为什么要转换编码：
有时候，为了让中文字符适应某些特殊要求（如http header头要求其内容必须为iso8859-1编码），
可能会通过将中文字符按照字节方式来编码的情况，如
String s_iso88591 = new String("中".getBytes("UTF-8"),"ISO8859-1")，
这样得到的s_iso8859-1字符串实际是三个在 ISO8859-1中的字符，在将这些字符传递到目的地后，
目的地程序再通过相反的方式
String s_utf8 = new String(s_iso88591.getBytes("ISO8859-1"),"UTF-8")
来得到正确的中文汉字“中”。这样就既保证了遵守协议规定、也支持中文。 

servlet规范：
(1) HttpServletRequest.setCharacterEncoding()方法 仅仅只适用于设置post提交的request body的编码而不是设置get方法提交的queryString的编码。该方法告诉应用服务器应该采用什么编码解析post传过来的内容。很多文章并没有说明这一点。
(2) HttpServletRequest.getPathInfo()返回的结果是由Servlet服务器解码(decode)过的。
(3) HttpServletRequest.getRequestURI()返回的字符串没有被Servlet服务器decoded过。
(4) POST提交的数据是作为request body的一部分。
(5) 网页的Http头中ContentType("text/html; charset=GBK")的作用：
    (a) 告诉浏览器网页中数据是什么编码；
    (b) 表单提交时，通常浏览器会根据ContentType指定的charset对表单中的数据编码，然后发送给服务器的。
这里需要注意的是：这里所说的ContentType是指http头的ContentType，而不是在网页中meta中的ContentType。

-------------------------------------------------------------------------------------------------------------
5 ctrl + c ??tomcat

-------------------------------------------------------------------------------------------------------------
6.required="true"
  stuts 标签。谨慎使用

-------------------------------------------------------------------------------------------------------------
7.<A href="javascript:void(0)">点击</a>点击链接后不会回到网页顶部

-------------------------------------------------------------------------------------------------------------
No row with the given identifier exists
有两张表,table1和table2.
产生此问题的原因就是table1
里做了关联<one-to-one>或者<many-to-one unique="true">
(特殊的多对一映射,实际就是一对一)
来关联table2.当hibernate查找的时候,
table2里的数据没有与table1相匹配的,
这样就会报No row with the given identifier exists这个错.
(一句话,就是数据的问题!)

-------------------------------------------------------------------------------------------------------------
9.SQL Server 2005“备份集中的数据库备份与现有的数据库不同”解决方法
http://www.jb51.net/article/19233.htm

-------------------------------------------------------------------------------------------------------------
10.
java.lang.NumberFormatException: For input string: ""
类型转换的问题
-------------------------------------------------------------------------------------------------------------

11. xgb 缓存
ClientAbortException: java.net.SocketException:
Connection reset by peer: socket write error
原因如下：
1、这个问题一般是客户端在连接还没有完全建立的时候就取消连接，
比如用户按了浏览器上面的“停止”按钮，一般来说没有什么问题。
但是如果频繁出现，就表示很多客户端连接到Apache服务器的响应时间太长了，
可能是网络的问题或者服务器性能问题 可能你的网络连接存在一些问题，
你的数据传输的时候，可能由于时间等待的太久，
但是server段设置的连接检验时间限制一定，
那么就可能出现这种情况的！ 
-------------------------------------------------------------------------------------------------------------
12、xgb 缓存
ClientAbortException: java.net.SocketException:
Connection reset by peer: socket write error
的原因是由于处理http连接时，正在输出内容时，
用户关闭了IE，会出现一个"ClientAbortException"，
属于I/O处理中出现的一个异常，应用服务器应该会捕捉。
3、应该是线程被占用的太多了 无法控制了 
我想可能会是：.数据库库连接达到极限
，.应用服务器 最大线程并发数 达到极限，.内存耗尽

-------------------------------------------------------------------------------------------------------------

13	string
奖惩查询时错误
   string强制转换int 时，null," " 不能被转换
-------------------------------------------------------------------------------------------------------------
14.	缓存
socket write error
这个异常的原因是，你的页面有一些动态生成的东西，而且相对较大。
你的服务器向客户端发送数据，结果刚发一半，客户端关闭了链接，就造成了这个错误，

一般是类似验证码这样的地方。 总之这个异常是合理的，至于是否引起tomcat挂掉，我不认为是这个原因。

除非你的这部分程序有错误！

-------------------------------------------------------------------------------------------------------------
15.	缓存
Java heap space
tomcat缓存不足

-------------------------------------------------------------------------------------------------------------
16.	serlet
String filePath = request.getSession().getServletContext().getRealPath("/");
request.getSession().getServletContext() 获取的是Servlet容器对象，
相当于tomcat容器了。getRealPath("/") 获取实际路径，“/”指代项目根目录
，所以代码返回的是项目在容器中的实际发布运行的根路径如
：I:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\
wtpwebapps\UMPWeb_20131230\

-------------------------------------------------------------------------------------------------------------
17.	eclipse 设置默认编码为Utf-8
需要设置的几处地方为：

 Window->Preferences->General ->Content Type->Text->JSP 最下面设置为UTF-8

 Window->Preferences->General->Workspace 面板Text file encoding 选择UTF-8
 Window->Preferences->Web->JSP Files面板选择 ISO 10646/Unicode(UTF-8)

-------------------------------------------------------------------------------------------------------------
18. eclipse
	项目旁有!
    以前引用的包，因为路径的等问题
    找不到了
    buildpath就可以

-------------------------------------------------------------------------------------------------------------
19	Tomcat
Tomcat报错：Document base ……does not exist or is not a readable directory
删除eclipse中的tomcat就好了

-------------------------------------------------------------------------------------------------------------
20	log4j
log4j:ERROR LogMananger.repositorySelector was null likely due to error in class reloading, usin (2011-05-10 19:54:32)
标签： 杂谈	分类： javascript_SSH
Today I got this error message from Tomcat 6.0.24:
log4j:ERROR LogMananger.repositorySelector was null likely due to error
in class reloading, using NOPLoggerRepository.

The reason for the error is a new listener in Tomcat 6.0.24. You can fix this error by adding this line:
org.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false
to the "conf/catalina.properties" file in your tomcat directory.

-------------------------------------------------------------------------------------------------------------
21 JSP
org.apache.jasper.JasperException: Unable to compile class for JSP: 

An error occurred at line: 23 in the generated java file
The method getJspApplicationContext(ServletContext) is undefined for the type JspFactory

项目中有像jsp-api.jar servlet-api.jar跟tomcat6的lib中同样的包，
但版本比tomcat6的版本要低，在运行的时候，因为会优先加载项目中的包，
所以 产生了错误，把项目中和tomcat相同的包删除(必须删除，如果替换还会产生其他的错误)，
问题就可以解决了。

-------------------------------------------------------------------------------------------------------------
22. 规范--wonders
xhzs
方法命名规范：
	1.java驼峰命名法，首字母以小写开头，每个单词首字母大写（第一个单词除外）
	2.方法表示一种行为，它代表一种动作，最好是一个动词或者动词词组或者第一个单词为一个动词。
		页面跳转命名规则：to+XXX
		页面查询命名规则：query+XXX，find+XXX，get+XXX，load+XXX，search+XXX
		页面更新命名规则：update+XXX, modify+XXX
		页面保存命名规则：save+XXX
		页面删除命名规则：remove+XXX，delete+XXX

-------------------------------------------------------------------------------------------------------------
23 spring fileupload 上传 文件上传 多文件上传
xhzs
user jar：
	使用commons-fileupload.jar上传
code：
	html：
	<form id="formInfo" method="post" action="" enctype="multipart/form-data">
		<input id="realFile" type="file" name="realFile" class="inp_w45" />
	</form>

	java：
	boolean isFileUpload = ServletFileUpload.isMultipartContent(request);
	if (isFileUpload) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("realFile");//readFile是前台input的name值
		if (file.getSize() == 0) { // 上传文件大小为0,并且是上传文件后点击查询进来
		} else if (file.getSize() != 0) { // 上传文件大小不为0,并且是上传文件后点击查询进来
			InputStream in = file.getInputStream();
			String fileName = file.getOriginalFilename();//Original原本的
			//判别上传文件是不是excel文件
			if (fileName.endsWith("xls")) {
				fileType1 = "xls";
			} else if (fileName.endsWith("xlsx")) {
				fileType1 = "xlsx";
			}
		}
	}
----------------------------------------------------------------------------------------------------------------
24 excel  读取excel 写入excel 操作excel
xhzs
code：
/**
 * 读取excel文件数据
 * 
 * @param is输入字节流
 * @param isXlsOrXlsx文件类型
 * @return
 * @throws IOException
 */
private void readExcel(InputStream is,
			String isXlsOrXlsx) throws IOException {
	/** 根据版本选择创建Workbook的方式 */
	Workbook wb = null;

	if ("xls".equals(isXlsOrXlsx)) {
		wb = new HSSFWorkbook(is);
	} else {
		wb = new XSSFWorkbook(is);
	}
	for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
		Sheet sheet = wb.getSheetAt(numSheet);
		if (sheet == null) {
			continue;
		}
		/** 得到Excel的行数 */
		int totalRows = sheet.getPhysicalNumberOfRows();
		// 循环行Row
		for (int rowNum = 0; rowNum < totalRows; rowNum++) {
			Row row = sheet.getRow(rowNum);
			if (row != null) {
				if (row.getCell(0) != null) {
					row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					String value = row.getCell(0).getStringCellValue(); // 读取第一列单元格的值
					if (StringUtils.isNotEmpty(value)) {
						//写入到pojo
					}
				}
			}
			//list添加多个pojo
		}
	}
}
--------------------------------------------------------------------------------------------------------
25 hibernate annotation配置排序    hibernate排序  hibernate顺序

@OneToMany(cascade = { CascadeType.PERSIST ,CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "meetingInfo")
@Cascade(org.hibernate.annotations.CascadeType.ALL)
@OrderBy("uploadTime asc")//根据关联类的上传时间排序
public Set<MeetingAttach> getMeetingAttachs() {
	return meetingAttachs;
}
--------------------------------------------------------------------------------------------------------
26 jquery valicate jquery提交验证 jquery验证 表单验证插件jquery validation  xhzs

<!--引入 jquery validate 相关js -->
<script src="${assets_path}/assets/js/jquery.validate.min.js"></script>
<script src="${assets_path}/assets/js/messages_zh.min.js"></script>

默认校验规则
序号	规则	        描述
1	required:true	    必须输入的字段。
2	remote:"check.php"	使用 ajax 方法调用 check.php 验证输入值。
3	email:true	        必须输入正确格式的电子邮件。
4	url:true			必须输入正确格式的网址。
5	date:true			必须输入正确格式的日期。日期校验 ie6 出错，慎用。
6	dateISO:true		必须输入正确格式的日期（ISO），例如：2009-06-23，1998/01/22。只验证格式，不验证有效性。
7	number:true			必须输入合法的数字（负数，小数）。
8	digits:true			必须输入整数。
9	creditcard:			必须输入合法的信用卡号。
10	equalTo:"#field"	输入值必须和 #field 相同。
11	accept:				输入拥有合法后缀名的字符串（上传文件的后缀）。
12	maxlength:5			输入长度最多是 5 的字符串（汉字算一个字符）。
13	minlength:10		输入长度最小是 10 的字符串（汉字算一个字符）。
14	rangelength:[5,10]	输入长度必须介于 5 和 10 之间的字符串（汉字算一个字符）。
15	range:[5,10]		输入值必须介于 5 和 10 之间。
16	max:5				输入值不能大于 5。
17	min:10				输入值不能小于 10。

jquery.validate.min.js里自带的messages就是错误规则提示 
messages: {
    required: "This field is required.",
    remote: "Please fix this field.",
    email: "Please enter a valid email address.",
    url: "Please enter a valid URL.",
    date: "Please enter a valid date.",
    dateISO: "Please enter a valid date (ISO).",
    dateDE: "Bitte geben Sie ein gültiges Datum ein.",
    number: "Please enter a valid number.",
    numberDE: "Bitte geben Sie eine Nummer ein.",
    digits: "Please enter only digits",
    creditcard: "Please enter a valid credit card number.",
    equalTo: "Please enter the same value again.",
    accept: "Please enter a value with a valid extension.",
    maxlength: $.validator.format("Please enter no more than {0} characters."),
    minlength: $.validator.format("Please enter at least {0} characters."),
    rangelength: $.validator.format("Please enter a value between {0} and {1} characters long."),
    range: $.validator.format("Please enter a value between {0} and {1}."),
    max: $.validator.format("Please enter a value less than or equal to {0}."),
    min: $.validator.format("Please enter a value greater than or equal to {0}.")
},

code：
//校验
$('#addMeetingInfo').validate({
	rules : {
		'meetingName':{required:true,maxlength:100},
		'meetingType':{required:true}
	},messages : {
		'meetingName':{required:"请输入会议名称！",maxlength:"会议名称不能超过33字！"},
		'meetingType':{required:"请选择会议类型"}
	},
	submitHandler:function() {
		var success = true;
		if(success) {
			var isNecessary = "${dicAttachTypeList[0].isNecessary}";
			//获取
			if(isNecessary == 1){
				
			}else{
				var form=document.getElementById("addMeetingInfo");
				var url=form.action;
				url=url+"?status="+status;
				form.action=url;
				document.getElementById("addMeetingInfo").action=url;
				form.submit();
			}
		}
	}
});

code2：
$(document).ready(function validateFrom (argument) {
     $("#form").validate({
          rules:{
               name:{		//input name 值
                    required:true,
                    minlength:2,
                    maxlength:10
               },
               IDNumber:{
                    required:true,
                    minlength:15,
                    maxlength:18
               }
          },
          messages:{
               name:{
                    required:"必须填写用户名",
                    minlength:"用户名最小为2位",
                    maxlength:"用户名最大为10位"
               },
               IDNumber:{
                    required:"身份证不能为空",
                    minlength:15,
                    maxlength:18
               }
          }
     });
});

validate()方法配置项：
submitHandler(form) 通过验证后运行的函数，可以加上表单提交的方法
invalidHandler 无效表单提交后运行的函数
ignore 对某些元素不进行验证
rules 定义检验规则
messages 定义提示信息
groups 对一组元素的验证，用一个错误提示，用errorPlacement控制把错误信息放在哪里
onsubmit 提交时是否进行验证，默认是true，提交的时候进行验证
onfocusout 是否在获取焦点时验证
onkeyup 是否在敲击键盘时验证
onclick 是否在鼠标点击时验证，一般用于checkbox
focusInvalid 提交表单后，未通过验证的表单是否获得焦点
focusCleanup 当未通过验证的元素获取焦点时，是否移除错误提示
errorClass 指定错误提示的css类名，可以自定义错误提示样式
validClass 指定验证通过使用的css的类名
errorElement 使用什么标签标记错误
wrapper 使用什么标签把上边的errorElement包起来
errorLabelContainer 把错误信息统一放在一个容器里面
errorContainer 显示或者隐藏验证信息，可以自动实现有错误信息出现时把容器属性变为显示，无错误时隐藏
showErrors(errorMap,errorList) 显示总共有多少个未通过验证的元素
errorPlacement 定义错误信息放在哪里
success: 要验证的元素通过验证后的动作
highlight(element,errorClass,validClass) 可以给未通过验证的元素加效果
unhighlight 去除未通过验证的元素的效果，一般和highlight同时使用

选择器扩展
:blank 选择所有值为空的元素
:filled 选择所有值不为空的元素
:unchecked 选择所有没有被选中的元素

自定义验证方法:
jQuery.validator.addMethod(name,method,[message])
name 方法名称
method: function(value,element,params) 方法逻辑
messages:提示消息

.....................................
jquery valicate 正则表达式 匹配  添加新的自己的函数，添加自己的函数
	$(document).ready(function(){
		//联系电话(手机/电话皆可)验证   
		$.validator.addMethod("isPhone", function(value,element) {   
			var length = value.length;    
			var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;   
			var tel = /^\d{3,4}-?\d{7,9}$/;   
			return this.optional(element) || (tel.test(value) || mobile.test(value));
		}, "请正确填写您的联系电话");   
		$('#formStatus').validate({
			rules:{
				'contactTel':{isPhone:true}
			},
			messages:{
				'contactTel':{isPhone:"请输入一个有效的联系电话"}
			},
			submitHandler:function(){
				_ajax_submit($('#formStatus'),return2Todo);
			}
		});
		
	})
	function return2Todo(responseText) {
		parent.parent.$('#dialogDiv').dialog('close');
	}


--------------------------------------------------------------------------------------------------------
27 jquery ajax ajax ajax提交 
	//ajax提交后天
	var meetingYear = $("#meetingYear").val();
	var meetingType = $("#meetingType").val();
	//数组模式
	var data = {
		meetingYear:meetingYear,
		meetingType:meetingType
	};
	var url = _path + 'xhzs/policysupport/getMeetingNo';
	$.ajax({
		type : "post",
		url : url,
		data : data,
		dataType : "text",
		success : function(data) {
			var obj = eval("("+data+")");
			$("#meetingNo").val(obj.object);
		},
		error : function(data, textStatus) {
			var obj = eval("("+data+")");
			alert("会议年份不正确");
		}
	}); 
--------------------------------------------------------------------------------------------------------
28 jquery ui dialog dialog对话框








--------------------------------------------------------------------------------------------------------
29 svn锁定 代码提交锁定 代码不能更新
org.apache.subversion.javahl.ClientException: Attempted to lock an already-locked dir
svn: Working copy 'D:\workspace2\xhzs-sabic' locked.
svn: 'D:\workspace2\xhzs-sabic' is already locked.

右击项目名-> Team -> Refresh/cleanUp


--------------------------------------------------------------------------------------------------------
30 sql 转义单引号
code:
	insert into dic_load_config values('dic_unit_tag','企业表示字典','1','DIC_ITEM','CODE','NAME','WHERE DIC_ID=''73''','','')


--------------------------------------------------------------------------------------------------------
31 jsp:include 传值   jsp:param   
传值：
	<jsp:include flush="true" page="../unitdailymanager/openDetailUnitDeclare.jsp" >
		<jsp:param value="unit_tag" name="unit_tag"/>
	</jsp:include>
	接受值：
	<%
		String unit_tag=request.getParameter("unit_tag"); 
	%>

--------------------------------------------------------------------------------------------------------
32 java error map map.entry 遍历map
	  修改，修正；改变
java.util.ConcurrentModificationException
在使用iterator.hasNext()操作迭代器的时候，如果此时迭代的对象发生改变，比如插入了新数据，或者有数据被删除。

则使用会报以下异常：
java.util.ConcurrentModificationException
        at java.util.HashMap$HashIterator.nextEntry(HashMap.java:793)
        at java.util.HashMap$KeyIterator.next(HashMap.java:828)
//遍历map
	public static Map<String, String> DicToSingle(String deptId, String dic) {
		Map<String, String> map = DicLoadUtil.getInstance().getDic(dic);
		if (!map.isEmpty()) {
			if (map.containsKey(deptId)) {
				Iterator<Map.Entry<String, String>> it = map.entrySet()
						.iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					String key = entry.getKey();
					if (!StringUtils.equals(deptId, key)) {
						it.remove();
					}
				}
			}
		}
		return map;
	} 
java Map及Map.Entry详解
Map提供了一些常用方法，如keySet()、entrySet()等方法。
keySet()方法返回值是Map中key值的集合；
entrySet()的返回值也是返回一个Set集合，此集合的类型为Map.Entry。
Map.Entry是Map声明的一个内部接口，此接口为泛型，定义为Entry<K,V>。
它表示Map中的一个实体（一个key-value对）。接口中有getKey(),getValue方法。

	Map<String, String> map = ...
	for (Map.Entry<String, String> entry : map.entrySet())
	{
		System.out.println(entry.getKey() + "/" + entry.getValue());
	}

--------------------------------------------------------------------------------------------------------
33 jqery 设置 <form:select> <input type="select"> 默认值  select默认值 设置select selected
	$(function(){
		 $("#unitTag option[value='01']").attr("selected", true); 
	});


--------------------------------------------------------------------------------------------------------
34 form表单
	数据提交不到后台  两个form表单重叠

--------------------------------------------------------------------------------------------------------
35 tomcat myeclipse 
java.lang.UnsupportedOperationException: Manual close is not allowed over a Spring managed SqlSession

org.springframework.beans.factory.BeanCreationException: 
Error creating bean with name 'cashierAction' defined in file 
[D:\java\apache-tomcat-6.0.44-windows-x86\apache-tomcat-6.0.44\webapps\mendian-tms\WEB-INF\classes\com\mendian\action\appStore\CashierAction.class]:
Instantiation of bean failed; nested exception is org.springframework.beans.BeanInstantiationException: 
Could not instantiate bean class [com.mendian.action.appStore.CashierAction]: Constructor threw exception; nested exception is java.lang.Error: 
Unresolved compilation problems: 

do:
myeclise servers 从新导入项目
一般都是未加载，未编译的问题


------------------------------------------------------------------------------------------------------------------
36 Caused by: java.lang.ClassNotFoundException:javax.persistence.EntityListene
使用Hibernate3.6.0.final出现问题
原因是没有导入
hibernate-jpa-2.0-api-1.0.0.Final.jar这个包


-----------------------------------------------------------------------------------------------------------------
37 org.springframework.beans.factory.BeanCreationException: 
   Error creating bean with name 'entityManagerFactory' defined in class path resource [beans.xml]

不是缺少了包，反而可能是多了jpa包，极有可能是项目里面引了一个jpa包，hibernate又自带了一个，且版本不一样。
删除hibernate-jpa-2.0-api-1.0.0.Final.jar
hibernate3.6final，已经把Annotation类库集成到了hibernate3.6。当我们想使用Annotation时，
下载hibernate3.6final，不再需要下载hibernate-annotations-3.4.0.GA.zip了。

------------------------------------------------------------------------------------------------------------------

