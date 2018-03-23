## read to mybatis source ##


---
- 新建一个简单的mybatis项目用于测试连接成功

        error:mybatis-config.xml    
    			url不能使用与druid相同的url,使用原始的url,我也不知道这是为什么
    			jdbc:mysql://localhost:3306/test
    			
    			mapper resource属性前面没有'/'
    
    	error:mapper.xml
    			resultType使用类的全路径

---				