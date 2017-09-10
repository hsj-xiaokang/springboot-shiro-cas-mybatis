package cn.wcj.sso.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.wcj.sso.config.shiro.CasConfig;
import cn.wcj.sso.pojo.po.TbUser;
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
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private CasConfig casConfig;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginForm(Model model){
		model.addAttribute("user", new TbUser()) ;
//		return "login";
		return "redirect:" + casConfig.getLocalServerLoginUrl();
	}

	/**
	 * 
	 * @Description:cas就不用在这里登录认证了，认证在cas认证中心，sso-client只是做了授权
	 * @author:hsj -heshengjin
	 * @time:2017年9月10日 下午4:17:56
	 * @param user
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
	public String login(TbUser user,RedirectAttributes attributes){
		String username = user.getUsername();
		System.out.println(username);
		System.out.println(user.getPassword());
		UsernamePasswordToken token = new UsernamePasswordToken(username, user.getPassword());
		Subject currentUser = SecurityUtils.getSubject();
		try {
			// 将调用MyShiroRealm.doGetAuthenticationInfo()方法
			currentUser.login(token);
		} catch (AuthenticationException e){
			if (e instanceof UnknownAccountException){
				attributes.addFlashAttribute("message", "未知账户");
			} else if (e instanceof IncorrectCredentialsException){
				attributes.addFlashAttribute("message", "密码不正确");
			} else if (e instanceof LockedAccountException){
				attributes.addFlashAttribute("message", "账户已锁定");
			} else if (e instanceof ExcessiveAttemptsException){
				attributes.addFlashAttribute("message", "用户名或密码错误次数超限");
			} else {
				e.printStackTrace();
				attributes.addFlashAttribute("message", "用户名或密码不正确");
			}
		}
		if (currentUser.isAuthenticated()){
			return "redirect:/user";
		} else {
			token.clear();
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(RedirectAttributes attributes){
		SecurityUtils.getSubject().logout();
//		attributes.addFlashAttribute("message", "您已安全退出");
//		return "redirect:/login";
		return "redirect:" + casConfig.getCasServerLogoutUrl();
	}

	@RequestMapping("/403")
	public String unauthorizedRole(){
		return "403";
	}

	@RequestMapping("/user")
	public String getUserList(Map<String, Object> model)throws Exception{
		model.put("userList", userService.findAll());
		return "user";
	}
   //http://www.ssoclient.com:8989/sso-client/user/edit/1
	@RequestMapping("/user/edit/{id}")
	public String toUserEditPage(@PathVariable int id){
		return "user_edit";
	}
}
