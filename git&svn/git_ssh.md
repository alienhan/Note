---
title: Git
date:
categories:
- Git
tags:
- Git
---

官网 http://www.git-scm.com/  
---  
1. github   
username :tonghuajianghan  
email :tonghuajianghan@gmail.com  
password : 百度  
  
---
2. 创建git仓库  
  
添加用户名和密码：  
>git config --global user.name "jh" （github登录名）  
>git config --global user.email "tonghuajianghan@gmail.com"（github登陆邮箱）  
  
初始化git本地存储库：  
创建一个文件->cd 这个文件夹（例：cd E：note）  
->git init（初始化本地git）  
或  
图形化界面Git GUI创建一个新版本库  
或  
克隆一个本地存储库  
>cd f:  
>$ git clone git://github.com/schacon/grit.git  
  
  
Eclipse Git 插件：  
右键项目名->Team->share project ->Git->  
->选择文件位置（存储库）  
->选择版本名（项目所属位置）  
  
---  
3. git本地仓库与github连接  
  
ssh 操作  
1).生成ssh密钥在Administrator/.ssh（文件位置，默认生成）  
这个格式改过，每个git版本有些许的不同：  
  
格式1:
>$ssh keygen -t rsa -C "tonghuajianghan@gmail.com"  
可能报错：  
>ssh: Could not resolve hostname keygen: Name or service not known  
  
格式2：
>$ssh-keygen -t rsa -C "tonghuajianghan@gmail.com"  
  
生成 id_rsa 和 id_rsa.pub 两个秘钥文件  
接着又会提示你输入两次密码（该密码是你push文件的时候要输入的密码，  
而不是github管理者的密码）  
  
2).添加你的 SSH key 到 github上面去  
account setting下设置  
  
3).新建本地版本库  
创建一个文件，在文件下  
git init 就创建一个git本地版本库  
生成一个.git文件  
sts右击项目——>Team->share Patch  
->选中刚才设置的git本地版本库就行  
  
4).新建github远程版本库（在github网站上新建）  
  4.1new repository ->create Repository->完成  
  4.2Create a new repository on the command line  

touch README.md  
git init  
git add README.md  
git commit -m "first commit"  
git remote add origin git@github.com:lizhenyu/helloworld.git  
git push -u origin master  
Push an existing repository from the command line  
  
  
5). 连接本地版本库与远程版本库  
origin是默认已有远程版本库  
$git remote -v 列出远程版本库详细内容  

要添加一个新的远程仓库,可以指定一个简单的名字,  
以便将来引用,运行 git remote add [shortname] [url]:  

例1：  
>git remote add origin https://github.com/lizhenyu/helloworld.git  
>git push -u origin master	

例2：  
>$git remote add origintestgit0001 git@github.com:tonghuajianghan/firstdemo.git  
                (用于本地标名识)，取代firstdemo.git  
  
$ git push -u origin master  
Counting objects: 19, done.  
Delta compression using up to 4 threads.  
Compressing objects: 100% (19/19), done.  
Writing objects: 100% (19/19), 13.73 KiB, done.  
Total 23 (delta 6), reused 0 (delta 0)  
To git@github.com:tonghuajianghan/firstdemo.git  
  [new branch]      master -> master  
Branch master set up to track remote branch master from origin.  
把本地库的内容推送到远程，用git push命令，实际上是把当前分支master推送到远程。  
由于远程库是空的，我们第一次推送master分支时，  
加上了-u参数，Git不但会把本地的master分支内容推送的远程新的master分支，  
还会把本地的master分支和远程的master分支关联起来，  
  
6).再次提交远程版本库  
$ git push origin master	  
在以后的推送或者拉取时就可以简化命令。  
只要本地作了提交，就可以通过命令：  
  
7).  
将某个远程主机的更新，全部取回本地。  
$ git fetch <远程主机名>  
默认情况下，git fetch取回所有分支（branch）的更新。  
如果只想取回特定分支的更新，可以指定分支名。  
$ git fetch <远程主机名> <分支名>  
比如，取回origin主机的master分支。  
$ git fetch origin master  
所取回的更新，在本地主机上要用"远程主机名/分支名"的形式读取。  
比如origin主机的master，就要用origin/master读取。  
  
8).克隆远程服务器仓库到本地：  
$git clone git@github.com:tonghuajianghan/firstdemo.git  
  
9).git 提交时，忽略某些文件  
  
* error:
>git无法pull仓库refusing to merge unrelated histories
git pull origin master --allow-unrelated-histories

---
git基本命令：  
  
只是用于提交的用户名和邮箱（用作标示）  
$git config --global user.name ""  
$git config --global user.email ""  
  
显示config配置  
$git config --list  
  
updates were rejected because the tip  
远程版本库高于本地版本库  
应该先pull  
  
---   
git branch  
  
git branch 不带参数：列出本地已经存在的分支，  
并且在当前分支的前面加“*”号标记  
  
git branch -r 列出远程分支  
  
git branch -a 列出本地分支和远程分支  
  
git branch 创建一个新的本地分支，需要注意，  
此处只是创建分支，不进行分支切换  
example: git branch mybranch  
  
$ git checkout -b iss53 新建并切换  
  
git branch -m | -M oldbranch newbranc  
重命名分支，如果newbranch名字分支已经存在，  
则需要使用-M强制重命名，否则，使用-m进行重命名。  
  
git branch -d | -D branchname   
删除branchname分支  
  
git branch -d -r branchname   
删除远程branchname分支  
  
要切换到其他分支，可以执行 git checkout 命令  
$ git checkout testing  
当添加文件时，没有git add ，git不会对文件进行监听  
这样在切换分支时，文件不会受到影响  
  
不同分支初始化时，均继承master的文件  
  
git merge 合并  
  
---  
git 删除文件  
  
在被 git 管理的目录中删除文件时，可以选择如下两种方式来记录删除动作:  
一、rm + git commit -am "abc"  
二、git rm + git commit -m "abc"  
另外，git add . 仅能记录添加、改动的动作，删除的动作需靠 git rm 来完成。  
  
最后，rm 删除的文件是处于 not staged 状态的，  
也就是一种介于 “未改动” 和 “已提交过” 之间的状态。  
  
  
---  
git .gitignore  
	注意空格！！！  
  
	在 .git 相同文件夹下新建.gitignore文件  
  
	#注释  
	/build/ 忽略根目录下build文件夹下的所有文件  
  
---  
git 回滚 reset  
  
本地回滚  
  
git reset --soft | --mixed | --hard  
					默认  
  
--mixed  
会保留源码,只是将git commit和index 信息回退到了某个版本.  
  
--soft  
保留源码,只回退到commit 信息到某个版本.不涉及index的回退,如果还需要提交,直接commit即可.  
  
--hard  
源码也会回退到某个版本,commit和index 都回回退到某个版本.(注意,这种方式是改变本地代码仓库源码)  
  
---  
git commit -a -m 'added new benchmarks'   
  
跳过使用暂存区域  
尽管使用暂存区域的方式可以精心准备要提交的细节，  
但有时候这么做略显繁琐。 Git 提供了一个跳过使用暂存区域的方式，  
只要在提交的时候，给 git commit 加上 -a 选项，  
Git 就会自动把所有已经跟踪过的文件暂存起来一并提交，从而跳过 git add 步骤  
  
---  
EGit myeclipse   
  
egit http://archive.eclipse.org/egit/updates-3.0  
myeclipse 2013
