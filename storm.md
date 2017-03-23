### storm 

2017/3/22 17:19:31 
---------------------------------------------------------------------------------
* strom 安装

1. 安装python  
   这是为了测试安装效果，我们将部署 storm-starter project案例中word coun程序， 
   用的是python写的multi-lang bolt，使用python 2.7.6， 
    
   安装路径在：  
      C:\Python27\    
   python好像安装的时候自动就配置了环境变量
   这个我也不知道为什么要安装，网上教程安装，我也安装了，不过没有测试是否有用   

2. 安装并运行zookeeper
   地址： http://zookeeper.apache.org/doc/r3.3.6/zookeeperStarted.html#sc_Download  
   配置：
      > cd zookeeper-3.3.6  
      > copy conf\zoo_sample.cfg conf\zoo.cfg  
      > .\bin\zkServer.cmd  (启动zookeeper服务)
   
3. 安装storm

   Storm的windows官方版还没有释放，因此网上找了一些个人做的安装包。   
   windows运行版本： https://dl.dropboxusercontent.com/s/iglqz73chkul1tu/storm-0.9.1-incubating-SNAPSHOT-12182013.zip  
   source版本： https://github.com/ptgoetz/storm/tree/windows-test  
  
4. 配置storm环境变量  
      `STORM_HOME：D:\java\storm\storm-0.9.1  `
      在path中加入：
        `%STORM_HOME%\bin;C:\Python27;C:\Python27\Lib\site-packages\;C:\Python27\Scripts\`

5. 启动Storm
    storm好像是由这三部分组成的，因此需要分别启动storm的三个组件
    启动Nimbus  
     ` cd %STORM_HOME%  > storm nimbus   `
    启动Supervisor  
      `cd %STORM_HOME%  > storm supervisor   `
    启动Storm UI    
      ` cd %STORM_HOME%  > storm ui   `
      
        验证启动成功：
        浏览器打开http://localhost:8080/ 可看到Storm运行。  
        问题：storm ui运行在jetty中第一次启动可能看到jetty页面，刷新一次就好了  

6. 部署work count  

    网上文档给的，不知道是什么  
    
    下载地址：
    https://dl.dropboxusercontent.com/s/kc933u6vz2crqkb/storm-starter-0.0.1-SNAPSHOTjar-with-dependencies.jar   
     
    部署这个jar在本地：  
    >storm jar storm-starter-0.0.1-SNAPSHOT-jar-with-dependencies.jar storm.starter.WordCountTopology WordCount -c nimbus.host=localhost
      
    验证：  
        刷新 Storm UI页面，会看到 “WordCount” topology显示列出，点按链接确认它处理数据。


------------------------------------------------------------------------------
* storm java 链接 
