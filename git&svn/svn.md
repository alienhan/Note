---
title: svn
date:
categories:
- svn
tags:
- svn
---
  

### eclipse svn 导出文件中断   
.metadata文件锁住了或者与pull下的文件发生冲突，  
应该删除文件，重新生成  
  
  

  
### eclipse 添加svn插件：  
	help ->install new SoftWare...  
	选择文件夹下的site-1.8.14.zip添加即可  
  
### 与资源库同步：  
	查看本地修改与原本中央仓库的不同。  
	提交之前先更新。解决冲突  
	  
### svn忽略文件  
	先物理上将文件删除。让svn不能监控到  
	在将文件加入到项目中，在 team->svn:ignore  
	也就是说只有新建的文件才能添加是svn:ignore  
	  
### 设置svn属性  
