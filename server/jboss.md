#jboss


#安装&运行&关闭
1. bin/ add-user.bat
  添加用户
2. standalone.bat
  启动(单机模式)
3. 访问管理页面
  http://localhost:9990/
  登录:添加的用户
4. 服务端口
  http://localhost:8080/
关闭
   1. 启动 : bin/ jboss-cli.bat
   2. 连接 : cmd 中 connect localhost:9990/
   3. shutdown

---
#jboss post 提交中文乱码
    在HTTP的POST请求中，如果是这种情况 Content-Type: application/x-www-form-urlencoded;charset=utf-8 
，若没有设置charset=utf-8，这时可能会出现请求参数中文乱码的情况
do:
1. 改成get提交
2. Headers改 Content-Type:application/x-www-form-urlencoded;charset=UTF-8