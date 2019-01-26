---
title: hexo使用
date: 2015/9/9
comments: true
categories:
- tools
tags:
- tools-hexo使用
---


### 安装
1. 安装node.js
        npm -v 验证安装是否成功
2. 安装hexo
        npm install -g hexo-cli

### 关联git
1. 新建git Repositories  
tonghuajianghan.github.io 必须为这种形式,和git的名字相同  

2. 配置_config.yml 注意格式  
  ```
  # Deployment
  ## Docs: https://hexo.io/docs/deployment.html
  deploy:
    type: git
    repo: git@github.com:tonghuajianghan/tonghuajianghan.github.io.git
    branch: master
  ```
repo 必须为ssl地址,https连接不上

3. 安装Git部署插件  
        npm install hexo-deployer-git --save

### 设置主体
https://theme-next.iissnan.com/getting-started.html


### 运行
1. hexo init blog 新建(只需执行一次)
2. hexo clean
3. hexo g
4. hexo d

### 配置:
  https://hexo.io/zh-cn/docs/

#### NexT 主页不显示全文
next主体配置_config.yml配置

    auto_excerpt:
      enable: true
      length: 150


---
### 命令简写
hexo n "我的博客" == hexo new "我的博客" #新建文章  
hexo g == hexo generate #生成  
hexo s == hexo server #启动服务预览  
hexo d == hexo deploy #部署  

hexo server #Hexo会监视文件变动并自动更新，无须重启服务器  
hexo server -s #静态模式  
hexo server -p 5000 #更改端口  
hexo server -i 192.168.1.1 #自定义 IP  
hexo clean #清除缓存，若是网页正常情况下可以忽略这条命令  
