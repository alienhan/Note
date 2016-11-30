/*-------------------------------------------------------------------------
* 版权所有：北京光宇在线科技有限责任公司
* 作者：guohai
* 联系方式：guohai@gyyx.cn
* 创建时间： 2014年10月14日
* 版本号：v1.0
* 本类主要用途描述：
* xxxxxxxxxxxxxxxxxxxxxx
-------------------------------------------------------------------------*/
package org.guohai.test.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class TestController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("abc");
		return new ModelAndView("index");
	}

}
