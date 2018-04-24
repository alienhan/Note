#redis

#安装:
  下载地址：https://github.com/dmajkic/redis/downloads
#启动服务:
  redis-server.exe redis.conf

---
#windows安装sentinel集群
复制redis 3份(redis2.8以上)

1. maste目录中的redis6379.conf配置如下
#需要绑定一下机器IP地址，切勿使用默认地址127.0.0.1，最后说明原因,配置成ipv4地址。
bind 192.168.31.196

2. slave中的redis6380.conf配置如下
bind 192.168.31.196
port 6380
# 这里配置master的地址和端口号
slaveof 192.168.31.196 6379
#如果master有密码，配置如下
masterauth xxx

3. 配置sentinel实例
创建sentinel26379.conf文件
bind 192.168.31.196
port 26379
dir sentinel
sentinel monitor mymaster 192.168.31.196 6379 1
sentinel down-after-milliseconds mymaster 60000
sentinel failover-timeout mymaster 180000
sentinel parallel-syncs mymaster 1

#启动过程
1. 启动redis(主,从)
redis-server.exe redis.windows.conf
2. 启动sentinel
redis-server.exe sentinel.conf --sentinel

#windows启动redis 客户端
redis-cli.exe -h address -p port
address,port 必须正确,否则cmd就会卡住.

---
