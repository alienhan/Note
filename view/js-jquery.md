---
title: jquery
date: 2015/10/10
categories:
- 前端
tags:
- jquery
---
  
### jquery td 遍历      
```js  
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

### jquery   
	//获取这个input的最近父form表单  
	var $form = $(this).closest('form');  
