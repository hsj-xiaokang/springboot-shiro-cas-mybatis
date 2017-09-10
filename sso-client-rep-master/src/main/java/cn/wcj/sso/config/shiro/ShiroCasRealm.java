package cn.wcj.sso.config.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import cn.wcj.sso.pojo.po.TbUser;
import cn.wcj.sso.service.impl.UserServiceImpl;

/**
 * Created by Administrator on 2016/12/5 0005.
 */
public class ShiroCasRealm extends CasRealm{
	
	
	@Autowired
	private UserServiceImpl userService;

	/**
	 * 由于使用了SSO单点登录系统，此Ream只负责授权
	 * 权限认证（为当前登录的Subject授予角色和权限）
	 * 
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) super.getAvailablePrincipal(principals) ; //获取用户名 
		System.out.println("用户名字username->"+JSON.toJSONString(userName));
		TbUser user=null ;
		SimpleAuthorizationInfo info=null ;
		try{
			user = userService.findUserByUserName(userName);
		    System.out.println("用户对象user------->"+JSON.toJSONString(user));
	    	if (user != null){
			// 权限信息对象info，用来存放查出的用户的所有的角色及权限
			info = new SimpleAuthorizationInfo();
			    List<String> roles = userService.findRoleNamesByUserName(userName);   //根据用户名查询角色
				info.addRoles(roles);;
			    List<String> permissions= userService.findPermissionNamesByUserName(userName); //根据用户名查询权限 
				System.out.println("权限permission---------->"+JSON.toJSONString(permissions));
				info.addStringPermissions(permissions);
		  }
		}catch(Exception e){
			System.out.println("--------ShiroCasRealm异常---------------");
		}
		System.out.println("info--->"+info);
		return info;
	}
}
