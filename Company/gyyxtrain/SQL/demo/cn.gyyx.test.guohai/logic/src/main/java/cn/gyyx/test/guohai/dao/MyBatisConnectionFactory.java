/*-------------------------------------------------------------------------
* 版权所有：北京光宇在线科技有限责任公司
* 作者：guohai
* 联系方式：guohai@gyyx.cn
* 创建时间： 2014年9月1日
* 版本号：v1.0
* 本类主要用途描述：
* xxxxxxxxxxxxxxxxxxxxxx
-------------------------------------------------------------------------*/
package cn.gyyx.test.guohai.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis连接产生器
 * @author guohai
 *
 */
public class MyBatisConnectionFactory {
	private static SqlSessionFactory sqlAccountSessionFactory;
	
	static{
		
		try {
			String resource = "mybatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			if (sqlAccountSessionFactory == null) {
				sqlAccountSessionFactory = new SqlSessionFactoryBuilder().build(reader,"account" );
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 静态方法返回SQL实例
	 * @return
	 */
	public static SqlSessionFactory getSqlAccountSessionFactory() {
 
        return sqlAccountSessionFactory;
    }
}
