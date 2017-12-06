package cn.wcj.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class RestController {
	private final static Logger logger = LoggerFactory.getLogger(RestController.class);
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
	public String restlogin(HttpServletRequest req,HttpServletResponse respon)throws Exception{
//		return RestFulLogin.validateFromCAS("hsjhsj", "hsjhsj");
		        //校验有没有username和password
                String username = "hsjhsj";
		        UsernamePasswordToken token = new UsernamePasswordToken("hsjhsj","hsjhsj");
		        //获取当前的Subject  
		        Subject currentUser = SecurityUtils.getSubject();  
		        try {  
		            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
		            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
		            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
		            logger.info("对用户[" + username + "]进行登录验证..验证开始");  
		            currentUser.login(token);  
		            logger.info("对用户[" + username + "]进行登录验证..验证通过");  
		        }catch(UnknownAccountException uae){  
		            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");  
		            return "403";
		        }catch(IncorrectCredentialsException ice){  
		            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");  
		            return "403";
		        }catch(LockedAccountException lae){  
		            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");  
		            return "403";
		        }catch(ExcessiveAttemptsException eae){  
		            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多"); 
		            return "403";
		        }catch(AuthenticationException ae){  
		            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
		            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");  
		            ae.printStackTrace();  
		            return "403";
		        }  
		        //验证是否登录成功  
		        if(currentUser.isAuthenticated()){  
		            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");  
		            return "inde";
		        }else{  
		            token.clear();  
		            return "403";
		        }  
	}
}
