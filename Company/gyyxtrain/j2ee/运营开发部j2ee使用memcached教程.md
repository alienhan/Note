##运营开发部j2ee使用memcached教程##
###什么是Memcached###
memcached是一套分布式的高速缓存系统，由LiveJournal的Brad Fitzpatrick开发，但目前被许多网站使用。这是一套开放源代码软件，以BSD license授权发布。

memcached缺乏认证以及安全管制，这代表应该将memcached服务器放置在防火墙后。

memcached的API使用三十二比特的循环冗余校验（CRC-32）计算键值后，将数据分散在不同的机器上。当表格满了以后，接下来新增的数据会以LRU机制替换掉。由于memcached通常只是当作高速缓存系统使用，所以使用memcached的应用程序在写回较慢的系统时（像是后端的数据库）需要额外的代码更新memcached内的数据。
###在Ubuntu12.04上安装Memcached###
`sudo apt-get install memcached`
###使用Maven安装xmemcached插件###
	<dependency>
		<groupId>com.googlecode.xmemcached</groupId>
		<artifactId>xmemcached</artifactId>
		<version>2.0.0</version>
	</dependency>

###简单例子###
	MemcachedClient client=new XMemcachedClient("host",11211);
	
	//同步存储value到memcached，缓存超时为1小时，3600秒。
	client.set("key",3600,someObject);
	//从memcached获取key对应的value
	Object someObject=client.get("key");
	
	//从memcached获取key对应的value,操作超时2秒
	someObject=client.get("key",2000);
	//更新缓存的超时时间为10秒。
	boolean success=client.touch("key",10);
	
	//删除value
	client.delete("key");
###多端口时权重的设置###
	MemcachedClientBuilder builder = new    XMemcachedClientBuilder(AddrUtil.getAddresses("localhost:12000 localhost:12001"),new int[]{1,3});
	MemcachedClient memcachedClient=builder.build();

上面的例子将"localhost:12000"节点的权重设置为1，而将"localhost:12001"节点的权重设置为3.

###动态增删节点###
	MemcachedClient client=new XMemcachedClient(AddrUtil.getAddresses("server1:11211 server2:11211"));
	//Add two new memcached nodes
	client.addServer("server3:11211 server4:11211");
	//Remove memcached servers
	client.removeServer("server1:11211 server2:11211");