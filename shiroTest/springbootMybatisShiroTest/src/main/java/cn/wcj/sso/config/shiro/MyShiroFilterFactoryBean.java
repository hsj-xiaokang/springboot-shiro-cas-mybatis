package cn.wcj.sso.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.BeanInitializationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * <p>Module:MyShiroFilterFactoryBean </p>
 * <p>Description: 继承ShiroFilterFactoryBean处理拦截资源文件问题</p>
 * <p>Company:Software College Of ZhengZhou University </p> 
 * @author SuccessKey(WangCJ)
 * @date 2017年8月4日 下午9:16:03
 */
public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean {
	
	// ShiroFilter将直接忽略的请求
	private Set<String> ignoreExt;

	public MyShiroFilterFactoryBean(){
		super();
		ignoreExt = new HashSet<String>();
		ignoreExt.add(".jpg");
		ignoreExt.add(".png");
		ignoreExt.add(".gif");
		ignoreExt.add(".bmp");
		ignoreExt.add(".js");
		ignoreExt.add(".css");
	}

	@Override
	protected AbstractShiroFilter createInstance() throws Exception {
		SecurityManager securityManager = getSecurityManager();
		if (securityManager == null){
			throw new BeanInitializationException("SecurityManager property must be set.");
		}

		if (!(securityManager instanceof WebSecurityManager)){
			throw new BeanInitializationException("The security manager does not implement the WebSecurityManager interface.");
		}

		PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
		FilterChainManager chainManager = createFilterChainManager();
		chainResolver.setFilterChainManager(chainManager);
		return new MySpringShiroFilter((WebSecurityManager)securityManager, chainResolver);
	}

	private class MySpringShiroFilter extends AbstractShiroFilter {
		public MySpringShiroFilter(
				WebSecurityManager securityManager, PathMatchingFilterChainResolver chainResolver) {
			super();
			if (securityManager == null){
				throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
			}
			setSecurityManager(securityManager);
			if (chainResolver != null){
				setFilterChainResolver(chainResolver);
			}
		}

		@Override
		public void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse,
				FilterChain chain)
				throws ServletException, IOException {
			HttpServletRequest request = (HttpServletRequest)servletRequest;
			String str = request.getRequestURI().toLowerCase();
			boolean flag = true;
			int idx = 0;
			if ((idx = str.indexOf(".")) > 0){
				str = str.substring(idx);
				if (ignoreExt.contains(str.toLowerCase())){
					flag = false;
				}
			}
			if (flag){
				super.doFilterInternal(servletRequest, servletResponse, chain);
			} else {
				chain.doFilter(servletRequest, servletResponse);
			}
		}
		
	}
	
	
}
