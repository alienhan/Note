/*-------------------------------------------------------------------------
* 版权所有：北京光宇在线科技有限责任公司
* 作者：guohai
* 联系方式：guohai@gyyx.cn
* 创建时间： 2014年9月1日
* 版本号：v1.0
* 本类主要用途描述：
* xxxxxxxxxxxxxxxxxxxxxx
-------------------------------------------------------------------------*/
package cn.gyyx.test.guohai.beans;

public class UserInfo{

	private int userCode;
	private String accountName;
	private String password;
	private String emp;
	
	/**
	 * @return
	 */
	public int getUserCode() {
		return userCode;
	}
	
	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmp() {
		return emp;
	}
	
	public void setEmp(String emp) {
		this.emp = emp;
	}
	
	
}
