package cn.wcj.sso.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wcj.sso.service.impl.UserServiceImpl;
/**
 * 
 * <p>Module:UserController </p>
 * <p>Description: 用户控制层</p>
 * <p>Company:Software College Of ZhengZhou University </p> 
 * @author SuccessKey(WangCJ)
 * @date 2017年8月4日 下午9:22:53
 */
@Controller
public class UserController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserServiceImpl userService;


	@RequestMapping("/403")
	public String unauthorizedRole(){
		return "403";
	}

	@RequestMapping("/user")
	public String getUserList(Map<String, Object> model,HttpServletRequest req,HttpServletResponse respon)throws Exception{
		model.put("userList", userService.findAll());
		return "user";
	}
	
	@RequestMapping("/inde")
	public String inde()throws Exception{
		return "index.html";
	}

	
	@RequestMapping("/userGet")
	@ResponseBody
	public String userGet(HttpServletRequest req,HttpServletResponse respon)throws Exception{
		Subject currentUser = SecurityUtils.getSubject();		
		return (String) currentUser.getPrincipal();
	}
   //http://www.ssoclient.com:8989/sso-client/user/edit/1
	@RequestMapping("/user/edit/{id}")
	public String toUserEditPage(@PathVariable int id){
		return "user_edit";
	}
}
