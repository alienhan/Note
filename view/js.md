---
title: js基础
date: 2015/10/10
categories:
- 前端
tags:
- js基础
---

### 定义JavaScript 通常用于操作 HTML 元素     
	JavaScript 语句     
	JavaScript 语句向浏览器发出的命令。      
	语句的作用是告诉浏览器该做什么。      

### document操作	    
使用 document.write() 仅仅向文档输出写内容。    
如果在文档已完成加载后执行 document.write，整个 HTML 页面将被覆盖：  	  
  ```html  
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
  ```
JavaScript 语句向 id="demo"   
的HTML元素输出文本 "Hello World":
document.getElementById("demo").innerHTML="Hello World";  
	  
### 空格      
  JavaScript 会忽略多余的空格。    
	您可以向脚本添加空格，来提高其可读性。    
	下面的两行代码是等效的    
  
### cookie  js操作cookie       
                 
添加：
```js     
	document.cookie = "agentMerNo=" + $("#agentMerNo").val();          
```

获取：  
```js
$("#agentMerNo option[value='" + getCookie("agentMerNo") + "']").attr("selected", "selected");          

function getCookieVal(offset) { //取得项名称为offset的cookie值           
	var endstr = document.cookie.indexOf(";", offset);//获取字符串长度          
	if (endstr == -1)          
		endstr = document.cookie.length;          
	return unescape(document.cookie.substring(offset, endstr));          
}          
        
function getCookie(name) { //取得名称为name的cookie值           
	var arg = name + "=";          
	var alen = arg.length;          
	var clen = document.cookie.length;          
	var i = 0;          
	while (i < clen) {          
		var j = i + alen;          
		if (document.cookie.substring(i, j) == arg)          
			return getCookieVal(j);          
		i = document.cookie.indexOf(" ", i) + 1;          
		if (i == 0)          
			break;          
	}          
	return "";          
}          
```          
