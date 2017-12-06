package cn.wcj.sso.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;

public class AuthenticationInfoSelf implements AuthenticationInfo{

	@Override
	public PrincipalCollection getPrincipals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

}
