package cn.wcj.sso.config.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;



//使用了这个注解可以方便的引用前缀
@ConfigurationProperties(prefix = "shiro.cas")
public class CasConfig {
	
	private String casServerUrlPrefix  ;  //CAS服务器地址
	
	private String casServerLoginUrl ;   //CAS登录地址
	
	private String casServerLogoutUrl ;  //CAS登出地址
	
	private String localServerUrlPrefix ;   //应用服务器地址
	
	private String casFilterUrlPattern ;  //应用服务器配置的CAS过滤器前缀
	
	private String localServerLoginUrl ;  //本地服务器登录地址   
	

	public String getCasServerUrlPrefix() {
		return casServerUrlPrefix;
	}

	public void setCasServerUrlPrefix(String casServerUrlPrefix) {
		this.casServerUrlPrefix = casServerUrlPrefix;
	}

	public String getCasServerLoginUrl() {
		return casServerLoginUrl;
	}

	public void setCasServerLoginUrl(String casServerLoginUrl) {
		this.casServerLoginUrl = casServerLoginUrl;
	}

	public String getCasServerLogoutUrl() {
		return casServerLogoutUrl;
	}

	public void setCasServerLogoutUrl(String casServerLogoutUrl) {
		this.casServerLogoutUrl = casServerLogoutUrl;
	}

	public String getLocalServerUrlPrefix() {
		return localServerUrlPrefix;
	}

	public void setLocalServerUrlPrefix(String localServerUrlPrefix) {
		this.localServerUrlPrefix = localServerUrlPrefix;
	}

	public String getCasFilterUrlPattern() {
		return casFilterUrlPattern;
	}

	public void setCasFilterUrlPattern(String casFilterUrlPattern) {
		this.casFilterUrlPattern = casFilterUrlPattern;
	}

	public String getLocalServerLoginUrl() {
		return localServerLoginUrl;
	}

	public void setLocalServerLoginUrl(String localServerLoginUrl) {
		this.localServerLoginUrl = localServerLoginUrl;
	}

	public String getCasService(){
		return localServerUrlPrefix + casFilterUrlPattern;
	}
}
