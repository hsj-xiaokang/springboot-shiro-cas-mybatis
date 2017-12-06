package cn.wcj.sso.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import cn.wcj.sso.config.redis.RedisConfig;

/**
 * 
 * <p>Module:ShiroCasConfiguration </p>
 * <p>Description: Shiro集成CAS配置</p>
 * <p>Company:Software College Of ZhengZhou University </p> 
 * @author SuccessKey(WangCJ)
 * @date 2017年8月4日 下午9:10:26
 */
@Configuration
@EnableConfigurationProperties({ShiroRedisConfig.class})
@AutoConfigureAfter(RedisConfig.class)  //在Redis配置完成后加载
public class ShiroConfig {		
	@Bean
	public ShiroRedisConfig shiroRedisConfig(){
		 return new ShiroRedisConfig()  ;
	}

	/**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        ShiroRedisConfig shiroRedisConfig = shiroRedisConfig();
        redisManager.setHost(shiroRedisConfig.getHost());
        redisManager.setPort(shiroRedisConfig.getPort());
        redisManager.setExpire(shiroRedisConfig.getExpire());// 配置缓存过期时间
        redisManager.setTimeout(shiroRedisConfig.getTimeout());
        //redisManager.setPassword(password);  //内网无密码，提高速度
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }


    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * shiro session的管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
    
    /**
     * 密码匹配凭证管理器
     * 
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashIterations(1024);// 散列的次数，比如散列两次，相当于
                                                            // md5(md5(""));

        return hashedCredentialsMatcher;
    }
    
    /**
     * realm
     * 
     * @return
     */
    @Bean(name = "shiroRealm")
    public ShiroRealm myAuthRealm(
            @Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher
           ) {
    	ShiroRealm shiroRealm = new ShiroRealm();
        // 设置密码凭证匹配器
    	shiroRealm.setCredentialsMatcher(matcher); // myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        return shiroRealm;
    }
	

	/**
	 * 注册shiroFilter
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		// 该值缺省为false，表示生命周期有SpringApplicationContext管理，设置为true则表示由ServletContainer管理
		filterRegistration.addInitParameter("targetFilterLifecycle", "true");
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*"); 
		return filterRegistration;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("shiroRealm") ShiroRealm realm){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);
		// 指定SubjectFactory
		securityManager.setSessionManager(this.sessionManager());
		securityManager.setCacheManager(this.redisCacheManager());
		return securityManager;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean factoryBean = new MyShiroFilterFactoryBean();
		factoryBean.setSecurityManager(securityManager);
		factoryBean.setLoginUrl("/restlogin");
		factoryBean.setSuccessUrl("/user");
		factoryBean.setUnauthorizedUrl("/403");

		loadShiroFilterChain(factoryBean);
		return factoryBean;
	}

	/**
	 * 加载ShiroFilter权限控制规则
	 */
	private void loadShiroFilterChain(ShiroFilterFactoryBean factoryBean) {
		/**下面这些规则配置最好配置到配置文件中*/
		Map<String, String> filterChainMap = new LinkedHashMap<String, String>();
		filterChainMap.put("/user", "authc");
		filterChainMap.put("/inde", "authc");
		filterChainMap.put("/userGet", "authc");
		filterChainMap.put("/casLogin", "authc");
		filterChainMap.put("/user/edit/**", "authc,perms[user:edit]");
		filterChainMap.put("/restlogin", "anon");
		filterChainMap.put("/**", "anon");
		factoryBean.setFilterChainDefinitionMap(filterChainMap);
	}



  


	

}

