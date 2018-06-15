---
title: js 插件  
date:
categories:
- 前端
tags:
- js插件
---
          
### autocomplete            
定义：             
AutoComplete控件就是在用户在文本框输入前几个字母或是汉字的时候,            
该控件就能从存放数据的文本或是数据库里将所有以这些字母开头的数据提示给用户            
            
eclipse中默认的浏览器不兼容autocomplete            
            
autocomplete不能集成到同一个文本框中            
            
最简格式：html          
  
```html            
	<link rel="stylesheet" href="styles.css" />本地包            
	<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>            
	<script src="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>            
	<script src="jqm.autoComplete-1.5.2.js"></script>            
            
	<input autocomplete="off" id="searchField" class="searchField" placeholder="Categories" />            
	<div id="suggestions" data-role="listview" data-inset="true"></div>            
            
            
	<script>            
		$("#mainPage").bind("pageshow", function(e) {            
			var data = ['姜晗','C', 'Clojure', 'Java', 'Scala', 'Objective-C', 'C++', 'PHP', 'C#', '(Visual) Basic',		            
						'Python', 'Perl', 'JavaScript', 'Ruby', 'Visual Basic .NET', 'Transact-SQL', 'Lisp', 'Pascal',		            
						'Bash', 'PL/SQL', 'Delphi/Object Pascal', 'Ada', 'MATLAB'];            
			$("#searchField").autocomplete({            
				target: $('#suggestions'),            
				source: data,            
				link: 'target.html?term=',            
				minLength: 1,            
				matchFromStart: false,					            
			});            
		});             
	</script>            
```
           
### Flexigrid            
*安装*                
/Flexigrid/WebContent/page/test/main_busic.jsp                
            
2012.12.14初步实现了flexigrid的安装实现                
            
将flexigrid.js                
flexigrid.css                
jquery-1.8.2.js                
导入到项目中的前台文件                
初始化必须的两个方法                
$("#flex1").flexigrid                
function addFormData()            
            
            
*Flexigrid功能*            
	重量轻，但丰富的数据网格，可调整大小的列和滚动数据相匹配的标题，              
	再加上连接到一个XML或JSON数据源使用Ajax加载的内容的能力。              
	类似的概念与外部电网唯一的纯的jQuery的爱，这使得它重量轻，最少的配置与运行如下的jQuery的口头禅。              
***特点***           
	可调整大小的列            
	可调高度和宽度            
	可排序的列标题            
	酷主题            
	可以将一个普通的表            
	能够连接到一个Ajax数据源（XML和JSON）            
	分页            
	显示/隐藏列            
	工具栏（新）            
	搜索（新）            
	可访问API            
	可调整大小的宽度            
	JSON支持            
	工具栏            
	表切换按钮            
	显示/隐藏列控制移动的列标题，（尝试它通过一个头mouseovering，点击右边的黑色三角形）            
	固定分页问题上的多个实例            
	Mootools和固定的原型noConflict（）的兼容性问题            
	onError事件Ajax交互，（它会通过一个变量调用数据的服务器），让你来处理服务器的问题            
	新的$（）。flexAddData方法，可以让你直接添加新的数据到网格中使用自己的数据源。            
	新预处理API，允许您修改或处理由服务器发送数据之前将它传递给Flexigrid，例如允许你使用自己的JSON格式。            
	单排选择使用{singleSelect：真正}中的选项            
	快速搜索            
	还有更多的            
	我来添加一个可编辑和Resortable的的行功能，??以及其他很酷的GUI功能。            
	我的主要目标的插件，最终还是保持它的重量轻！            
***ajax***            
	flexigrid表格中的数据——>自动生成xml格式——>添加到新建的.xml文件中             
	支持ajax跨域：            
	    url中加callback=?            
	    后台获得callback函数的名字            
		返回json数据格式为:print(callbackName+"("+jsonString+")");          
    flexigrid生成的xml保存在一个.xml文件中            
	  url: 'grid_lock.xml',             
	  dataType: 'xml',             
          
