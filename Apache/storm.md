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

   地址： 
    >http://zookeeper.apache.org/doc/r3.3.6/zookeeperStarted.html#sc_Download  

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
     ` cd %STORM_HOME%  > storm nimbus`

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

代码：
https://github.com/tonghuajianghan/storm-test/


    error1:
        java.net.SocketException: Address family not supported by protocol family: connect
        
        解决方法：
        ping localhost，如果没有正确回应把hosts文件配置一下试试！
        运行main class的时候添加“-Djava.net.preferIPv4Stack=true”参数试试！

    error2:
        在windows下运行ExclamationTopology或者WordCountTopology时，
        报“Unable to delete file错误”
    
        解决方法：
        这是storm的一个bug，0.8.2版本还没有解决，这个错误在cluster.shutdown();时候抛出，
        没有什么好解决办法，注释掉shutdown()方法程序将不会自己停止，这个错误影响有限，忽略它吧。 






------------------------------------------------------------------------------
- storm 集群