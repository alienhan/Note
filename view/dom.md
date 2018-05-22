#dom            
-----------------------------------------------------------------------------------------------------------            
*dom基础            
            
	1定义：            
	通过 JavaScript，            
	您可以重构整个 HTML 文档。            
	您可以添加、移除、改变或重排页面上的项目。            
	要改变页面的某个东西，            
	JavaScript 就需要获得对 HTML 文档中所有元素进行访问的入口。            
	这个入口，            
	连同对 HTML 元素进行添加、移动、改变或移除的方法和属性            
	都是通过文档对象模型来获得的（DOM）            
*分类            
	            
	html dom            
	HTML 文档中的每个成分都是一个节点。            
	节点            
	根据 DOM，HTML 文档中的每个成分都是一个节点。            
	DOM 是这样规定的：            
	整个文档是一个文档节点            
	每个 HTML 标签是一个元素节点            
	包含在 HTML 元素中的文本是文本节点            
	每一个 HTML 属性是一个属性节点            
	注释属于注释节点            
            
	html dom节点访问            
            
	getElementById() 和 getElementsByTagName()            
            
	getElementById() 和 getElementsByTagName() 这两种方法，            
	可查找整个 HTML 文档中的任何 HTML 元素。            
	这两种方法会忽略文档的结构。            
	假如您希望查找文档中所有的 <p> 元素，            
	getElementsByTagName() 会把它们全部找到，            
	不管 <p> 元素处于文档中的哪个层次。同时，            
	getElementById() 方法也会返回正确的元素，            
	不论它被隐藏在文档结构中的什么位置。            
	这两种方法会向您提供任何你所需要的 HTML 元素，            
	不论它们在文档中所处的位置！            
            
	getElementById() 可通过指定的 ID 来返回元素：            
            
	getElementById() 语法            
	document.getElementById("ID");             
	注释：getElementById() 无法工作在 XML 中。在 XML 文档中，            
	您必须通过拥有类型 id 的属性来进行搜索，而此类型必须在 XML DTD 中进            
	行声明。            
	getElementsByTagName() 方法会使用指定的标签名返回所有的元素（作为一个节点列表），            
	这些元素是您在使用此方法时所处的元素的后代            
	。            
	getElementsByTagName() 可被用于任何的 HTML 元素：            
	getElementsByTagName() 语法            
	document.getElementsByTagName("标签名称");             
	或者：            
	document.getElementById('ID').getElementsByTagName("标签名称");             
            
	下面这个例子会返回所有 <p> 元素的一个节点列表，            
	且这些 <p> 元素必须是 id 为 "maindiv" 的元素的后代：            
	document.getElementById('maindiv').getElementsByTagName("p");             
            
*节点列表（nodeList）            
            
-------------------------------------------------------------------------------------------------------------------            
