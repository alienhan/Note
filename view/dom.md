---
title: dom
date: 2015/10/10
categories:
- 前端
tags:
- dom基础
---

           
### dom基础            
            
1. 定义：            
	通过 JavaScript，            
	- 可以重构整个 HTML 文档。            
	- 可以添加、移除、改变或重排页面上的项目。            
	
  要改变页面的某个东西，            
	JavaScript 就需要获得对 HTML 文档中所有元素进行访问的入口       
	- 入口            
	- HTML 元素进行添加、移动、改变或移除的方法和属性           
  
  通过文档对象模型来获得的（DOM）      
        
2. 分类                        
	- html dom            
	HTML 文档中的每个成分都是一个节点。  
            
	- 节点            
	 - 根据 DOM，HTML 文档中的每个成分都是一个节点。            
	 - DOM 是这样规定的：            
	 - 整个文档是一个文档节点            
	 - 每个 HTML 标签是一个元素节点            
	 - 包含在 HTML 元素中的文本是文本节点            
	 - 每一个 HTML 属性是一个属性节点            
	 - 注释属于注释节点            
            
	- html dom节点访问                      
	 - getElementById()和getElementsByTagName()可查找整个 HTML 文档中的任何 HTML 元素。                    
	 - getElementById()  
    可通过指定的 ID 来返回元素：            
  	document.getElementById("ID");             
  	注释：getElementById() 无法工作在 XML 中。在 XML 文档中，  您必须通过拥有类型 id 的属性来进行搜索，而此类型必须在 XML DTD 中进行声明。            
   - getElementsByTagName()  
    方法会使用指定的标签名返回所有的元素（作为一个节点列表），这些元素是您在使用此方法时所处的元素的后代            
  	。            
	- getElementsByTagName() 可被用于任何的 HTML 元：    
  	- document.getElementsByTagName("标签名称");              
  	- document.getElementById('ID').getElementsByTagName("标签名称");       
  	
            返回所有<p>元素的一个节点列表，            
      	    且这些<p>元素必须是 id 为 "maindiv" 的元素：  
            document.getElementById('maindiv').getElementsByTagName("p");             
            
### 节点列表（nodeList）            
            
