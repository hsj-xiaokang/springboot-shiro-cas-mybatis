package cn.wcj.sso.config.redis;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * <p>Module:RedisConfig </p>
 * <p>Description:配置Redis缓存为MySQL数据库减轻访问压力 </p>
 * <p>Company:Software College Of ZhengZhou University </p> 
 * @author SuccessKey(WangCJ)
 * @date 2017年7月30日 下午10:23:03
 */
@Configuration
@EnableCaching  
public class RedisConfig extends CachingConfigurerSupport {

	@Bean  
    public KeyGenerator customKeyGenerator(){  
        return new KeyGenerator() {  
            @Override  
            public Object generate(Object target, Method method, Object... params) {  
                StringBuilder sb = new StringBuilder();  
                sb.append(target.getClass().getName());  
                sb.append(method.getName());  
                for (Object obj : params) {  
                    sb.append(obj.toString());  
                }  
                return sb.toString();  
            }  
        };  
  
    }  
  
    @Bean  
    public CacheManager cacheManager(  
            @SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {  
    	RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
    	cacheManager.setDefaultExpiration(300L);  //60s*5=5分钟过期
        return cacheManager;  
    }  
  
    @SuppressWarnings({"unchecked","rawtypes"})
	@Bean  
    public RedisTemplate<String, String> redisTemplate(  
            RedisConnectionFactory factory) {  
        StringRedisTemplate template = new StringRedisTemplate(factory);  
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);  
        ObjectMapper om = new ObjectMapper();  
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);  
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  
        jackson2JsonRedisSerializer.setObjectMapper(om);  
        template.setValueSerializer(jackson2JsonRedisSerializer);  
        template.afterPropertiesSet();  
        return template;  
    }  
	
	
}
