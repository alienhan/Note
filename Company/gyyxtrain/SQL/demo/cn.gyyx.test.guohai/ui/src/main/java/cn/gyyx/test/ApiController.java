/*-------------------------------------------------------------------------
* 版权所有：北京光宇在线科技有限责任公司
* 作者：guohai
* 联系方式：guohai@gyyx.cn
* 创建时间： 2014年9月12日
* 版本号：v1.0
* 本类主要用途描述：
* xxxxxxxxxxxxxxxxxxxxxx
-------------------------------------------------------------------------*/
package cn.gyyx.test;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.gyyx.test.guohai.beans.UserInfo;



@Controller
@RequestMapping("/api")
public class ApiController {

	@RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
	public UserInfo hello(){
		UserInfo user = new UserInfo();
		user.setAccountName("abc");
		return user;
	}
}
