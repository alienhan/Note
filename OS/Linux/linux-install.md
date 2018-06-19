---
title: linux 安装
date:
categories:
- linux
tags:
- linux
---

1. 命令行
2. Gedit 文本编辑器
3. ubuntu下SCIM输入法安装
4. ubuntu java init


### 命令行
对于linux系统而言，也有自己的命令执行工具，  
中文名叫做终端，可以在linux系统搜索中输入terminal找到工具。  
相比于cmd对windows，终端对于linux系统有着更加强的操作性，  
以获取root权限为例。  
具体过程如下：  
以自己现有的帐号登录系统，打开终端，  
输入：sudo passwd root并回车，  
再输入自己帐号的密码以授权使命令执行  
（终端中输入的密码不会显示字符，输入完成就好）。  
接着输入：su并回车，这时候输入刚刚设置的密码就以root帐号登录，  
可以执行一些被权限限制的指令。当你觉得没有必要在使用root帐号以后，  
可以输入：exit来退出root帐号。 
再以cmd也使用的ping指令为例,  
输入ping并回车后会出现使用方法，  
参考cmd的ping指令我们可以使用终端下的ping指令。  

注：对于计算机指令，  
不管什么系统都有它们的相似之处，  
结合着学可以更加好的掌握。还有一个小技巧就是，  
android系统可以下载一个名为终端模拟器的软件，  
这个软件也可以使用linux系统下的部分指令。  
虽然这个软件对于一般用户不是很有使用价值，  
但是也可以通过一些简单的指令来执行查看进程，  
杀进程等操作，掌握后还是很有价值的。  

要把整个屏幕切换到终端,同时按下Ctrl-Alt-F1.   
你一共可以有六个终端,分别对应Alt-F1 一直到Alt-F6.  
按下Alt-F7可以回到图形界面下.  

### Gedit 文本编辑器



### ubuntu下SCIM输入法安装

1. 终端输入：sudo apt-get remove scim
这一步是删除安装系统的时候装的那个scim，实际是个假的，我这里不可以用
2. sudo apt-get install scim
这一步下载安装scim输入法。下载完了系统会自己装上，中间会有提示，直接y就行了
3. sudo apt-get install scim-chinese
这一步是下载安装中文输入法，前面那一步只是下载一个可以运行输入法的平台，现在是在这个具体平台上安装中文输入
4. sudo gedit /etc/X11/Xsession.d /95xinput
这个步骤是新建一个95xinput的文件，该文件的具体内容如下：
Java代码
  1./usr/bin/scim -d  
  2.XMODIFIERS="@im=SCIM"  
  3.export XMODIFIERS  
  4.export GTK_IM_MODULE=scim  
  具体为什么，我也不知道，反正可以用。  
  然后，确定无误后，保存，退出gedit  
5. exit 退出终端
6. Logout，重新登录，然后用Ctrl+Space就可以激活中文输入法啦～

### ubuntu java init
