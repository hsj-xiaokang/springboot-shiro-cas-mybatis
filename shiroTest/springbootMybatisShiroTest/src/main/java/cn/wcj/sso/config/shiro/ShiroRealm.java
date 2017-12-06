package cn.wcj.sso.config.shiro;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wcj.sso.pojo.po.TbUser;
import cn.wcj.sso.service.impl.UserServiceImpl;

public class ShiroRealm extends AuthorizingRealm{

	
	@Autowired
	private UserServiceImpl userService;
	
	private static Logger log = LoggerFactory.getLogger(ShiroRealm.class);
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) super.getAvailablePrincipal(principals) ; //获取用户名 
		TbUser user=null ;
		SimpleAuthorizationInfo info=null ;
		try{
			user = userService.findUserByUserName(userName);
	    	if (user != null){
			// 权限信息对象info，用来存放查出的用户的所有的角色及权限
			info = new SimpleAuthorizationInfo();
			    List<String> roles = userService.findRoleNamesByUserName(userName);   //根据用户名查询角色
				info.addRoles(roles);;
			    List<String> permissions= userService.findPermissionNamesByUserName(userName); //根据用户名查询权限 
				info.addStringPermissions(permissions);
		  }
		}catch(Exception e){
			e.printStackTrace();
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		//UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;

        log.info("验证当前Subject时获取到token为：" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE)); 
//        return new SimpleAuthenticationInfo("hsjhsj","8e24137dee97c9bbddb9a0cd6e043be4" , getName());
        return new SimpleAuthenticationInfo("hsjhsj","" , getName());
        //查出是否有此用户
//        TbUser user=null;
//        if(user!=null){
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
//            return new SimpleAuthenticationInfo(user.getUsername(), , getName());
//        }
//        return null;
	}

}
