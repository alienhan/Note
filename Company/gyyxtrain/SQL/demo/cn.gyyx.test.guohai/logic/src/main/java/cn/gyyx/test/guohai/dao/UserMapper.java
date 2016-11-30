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

import java.util.List;

import cn.gyyx.test.guohai.beans.UserInfo;

public interface UserMapper {

	UserInfo selectByAccount(String account);
	
	/**
	 * 批量获取用户信息
	 * @param listAccount 用户名
	 * @return 用户实体
	 */
	List<UserInfo> selectByAccounts(List<String> listAccount);
}
