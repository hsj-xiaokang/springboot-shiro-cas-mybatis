package cn.wcj.sso.config.shiro;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * <p>Module:ShiroRedisConfig </p>
 * <p>Description: Shiro-Redis配置,方便Redis操作Shiro持久化Session</p>
 * <p>Company:Software College Of ZhengZhou University </p> 
 * @author SuccessKey(WangCJ)
 * @date 2017年8月6日 上午1:45:05
 */
//使用了这个注解可以方便的引用前缀
@ConfigurationProperties(prefix = "shiro.redis")
public class ShiroRedisConfig {

	private String host ;      //redis地址
	
	private Integer port  ;   //端口
	
	private Integer timeout  ;  //超时时间 
	
	private Integer expire    ; //过期时间

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Integer getExpire() {
		return expire;
	}

	public void setExpire(Integer expire) {
		this.expire = expire;
	}
	
	
	
	
	
}
