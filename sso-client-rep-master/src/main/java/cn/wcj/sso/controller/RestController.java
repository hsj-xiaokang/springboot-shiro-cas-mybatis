package cn.wcj.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class RestController {
	/**
	 * 前后端分离的情况之下rest风格登录获取TGT ST
	 * @Description:TODO
	 * @author:hsj qq:2356899074
	 * @time:2017年12月1日 下午2:49:40
	 * @param req
	 * @param respon
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/restlogin")
	@ResponseBody
	public String restlogin(HttpServletRequest req,HttpServletResponse respon)throws Exception{
		return RestFulLogin.validateFromCAS("hsjhsj", "hsjhsj");
	}
}
