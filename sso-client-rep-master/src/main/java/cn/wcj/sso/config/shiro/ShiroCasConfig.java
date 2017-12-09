package cn.wcj.sso.config.shiro;

import java.util.HashMap;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
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
@EnableConfigurationProperties({CasConfig.class,ShiroRedisConfig.class})
@AutoConfigureAfter(RedisConfig.class)  //在Redis配置完成后加载
public class ShiroCasConfig {
	
	
	private static final String CAS_FILTER = "casFilter";

//原生Redis保存Session的思路，不过不好用，测试未通过考验	
//	@Bean
//	public JavaUuidSessionIdGenerator sessionIdGenerator(){  //随机生成SesssionID
//		  return new JavaUuidSessionIdGenerator()  ;
//	}
//	
//	
//	
//	@Bean
//	public RedisSessionDAO  sessionDAO(){
//		 RedisSessionDAO redisSessionDAO=new RedisSessionDAO()  ;
//		 redisSessionDAO.setActiveSessionsCacheName("shiro-activeSessionsCache");
//		 redisSessionDAO.setSessionIdGenerator(this.sessionIdGenerator()) ;
//		 return redisSessionDAO   ;
//	}
//	
//	@Bean
//	public DefaultSessionManager sessionManager(){
//		DefaultSessionManager sessionManager=new DefaultSessionManager()  ;
//		sessionManager.setGlobalSessionTimeout(1800000) ;
//		sessionManager.setSessionValidationSchedulerEnabled(true) ;
//		sessionManager.setSessionDAO(this.sessionDAO());
//		sessionManager.setDeleteInvalidSessions(true) ;
//		return  sessionManager  ;
//	}
	
	
	
	
//	@Bean
//	public SimpleCookie rememberMeCookie(){
//		SimpleCookie cookie=new SimpleCookie("rememberMe")   ;
//		cookie.setHttpOnly(true);
//		cookie.setMaxAge(2592000) ;
//		return cookie  ;
//	}
	
//	@Bean
//	public CookieRememberMeManager rememberMeManager(){
//		CookieRememberMeManager rememberMeManager=new CookieRememberMeManager() ;
//		byte[] decode = Base64.decode("4AvVhmFLUs0KTA3Kprsdag==")   ;
//		rememberMeManager.setCipherKey(decode);
//		rememberMeManager.setCookie(this.rememberMeCookie());
//		return rememberMeManager ;
//	}
	
//	@Bean
//	public EhCacheManager ehCacheManager(){
//		EhCacheManager ehCacheManager =new EhCacheManager()  ;
//		ehCacheManager.setCacheManagerConfigFile("classpath:common/ehcache-shiro.xml");
//		return ehCacheManager ;
//	}
	
//	private String host="123.206.50.129"  ;   //redis地址
//	
//	private Integer port=6379 ;   //端口
//	
//	private Integer timeout  =1800  ;   //超时时间 
//	
//	private Integer expire =5000  ;    //过期时间
	
	
	
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
	
	
   	
	
	@Bean(name = "shiroCasRealm")
	public ShiroCasRealm shiroRealm(CasConfig casConfig){
		ShiroCasRealm realm = new ShiroCasRealm();
		realm.setCasServerUrlPrefix(casConfig.getCasServerUrlPrefix());
		realm.setCasService(casConfig.getCasService());
		return realm;
	}
	
	
	/** 
     * 注册单点登出的listener 
     * @return 
     */  
  /*  @SuppressWarnings({ "rawtypes", "unchecked" })  
    @Bean  
//    @Order(Ordered.HIGHEST_PRECEDENCE)// 优先级需要高于Cas的Filter  
    public ServletListenerRegistrationBean<?> singleSignOutHttpSessionListener(){  
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();  
        bean.setListener(new SingleSignOutHttpSessionListener());  
        bean.setEnabled(true);  
        return bean;  
    } */
  
    /** 
     * 注册单点登出filter 
     * @return 
     */  
 /*   @Bean 
//    @Order(Ordered.HIGHEST_PRECEDENCE)// 优先级需要高于Cas的Filter  
    public FilterRegistrationBean singleSignOutFilter(){  
        FilterRegistrationBean bean = new FilterRegistrationBean();  
        bean.setName("singleSignOutFilter");  
        bean.setFilter(new SingleSignOutFilter());  
//        bean.addUrlPatterns("/*");  
        bean.setEnabled(true);  
        return bean;  
    }*/ 

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
	
	 /** 
     * 下面两个配置主要用来开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持; 
     * @return 
     */  
    @Bean  
    @DependsOn("lifecycleBeanPostProcessor")  
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {  
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();  
        daap.setProxyTargetClass(true);  
        return daap;  
    }  
      
    /** 
     * @param securityManager 
     * @return 
     */  
    @Bean  
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {  
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();  
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);  
        return authorizationAttributeSourceAdvisor;  
    } 

	/*@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}*/

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager defaultWebSecurityManager(ShiroCasRealm realm){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);
		// 指定SubjectFactory
		securityManager.setSubjectFactory(new CasSubjectFactory());
		securityManager.setSessionManager(this.sessionManager());
		securityManager.setCacheManager(this.redisCacheManager());
		return securityManager;
	}

	/*@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}*/

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager, CasConfig casConfig, CasFilter casFilter){
		ShiroFilterFactoryBean factoryBean = new MyShiroFilterFactoryBean();
		factoryBean.setSecurityManager(securityManager);
		factoryBean.setLoginUrl(casConfig.getLocalServerLoginUrl());
		factoryBean.setSuccessUrl("/user");
		factoryBean.setUnauthorizedUrl("/403");
		// 添加casFilter到shiroFilter中
		Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
		filterMap.put(CAS_FILTER, casFilter);
		factoryBean.setFilters(filterMap);

		loadShiroFilterChain(factoryBean, casConfig);
		return factoryBean;
	}

	/**
	 * 加载ShiroFilter权限控制规则
	 */
	private void loadShiroFilterChain(ShiroFilterFactoryBean factoryBean, CasConfig casConfig) {
		/**下面这些规则配置最好配置到配置文件中*/
		Map<String, String> filterChainMap = new LinkedHashMap<String, String>();
		filterChainMap.put(casConfig.getCasFilterUrlPattern(), CAS_FILTER);//shiro集成cas后，首先添加该规则
		
		filterChainMap.put("/user", "authc");
		filterChainMap.put("/inde", "authc");
		filterChainMap.put("/userGet", "authc");
		filterChainMap.put("/casLogin", "authc");
		filterChainMap.put("/user/edit/**", "authc,perms[user:edit]");
		filterChainMap.put("/restlogin", "anon");
		filterChainMap.put("/**", "anon");
//		filterChainMap.put("/shiro-cas", "cas");
		
		factoryBean.setFilterChainDefinitionMap(filterChainMap);
	}

	/**
	 * CAS过滤器
	 */
	@Bean
	public CasFilter casFilter(CasConfig casConfig){
		CasFilter casFilter = new CasFilter();
		casFilter.setName(CAS_FILTER);
		casFilter.setEnabled(true);
		casFilter.setFailureUrl(casConfig.getLocalServerLoginUrl());
//		casFilter.setSuccessUrl("/user");
//		casFilter.setLoginUrl(casConfig.getLocalServerLoginUrl());
		return casFilter;
	}
	

}
