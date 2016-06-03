#js            
-------------------------------------------------------------------------------------------------------              
* 定义JavaScript 通常用于操作 HTML 元素               
            
	JavaScript 语句              
	JavaScript 语句向浏览器发出的命令。              
	语句的作用是告诉浏览器该做什么。              
* document	            
            
	使用 document.write() 仅仅向文档输出写内容。            
	如果在文档已完成加载后执行 document.write，整个 HTML 页面将被覆盖：            
	            
	```            
	<!DOCTYPE html>            
	<html>            
		<body>            
			<button onclick="myFunction()">点击这里</button>            
			<script>            
			function myFunction()            
			{            
			document.write("糟糕！文档消失了。");            
			}            
			</script>            
		</body>            
	</html>            
            
	JavaScript 语句向 id="demo"             
	的 HTML 元素输出文本 "Hello World"：            
	document.getElementById("demo").innerHTML="Hello World";            
	```            
            
* 空格                
            
	JavaScript 会忽略多余的空格。            
	您可以向脚本添加空格，来提高其可读性。            
	下面的两行代码是等效的            
            
------------------------------------------------------------------------------------------------------------            
#jquery            
            
* jquery td 遍历            
	```            
	//通过遍历来取得所有的td            
	var tr=$(".result .checkFlag");            
	tr.each(function(){            
		var checkFlag = $(this).attr("id");            
		alert(checkFlag);            
		if("1" == checkFlag){            
			$(this).attr("style","color:green");            
			$(this).parent().find(".check").html("");            
		}             
		if("2" == checkFlag){            
			$(this).attr("style","color:red");            
			$(this).parent().find(".check").html("");            
		}             
	});            
	```            
            
---------------------------------------------------------------------------------------------------------------