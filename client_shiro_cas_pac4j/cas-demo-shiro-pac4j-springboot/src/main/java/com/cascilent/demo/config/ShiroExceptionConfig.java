package com.cascilent.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cascilent.demo.shiroException.MyExceptionResolver;

/**
 *  如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url
 *  但是不要使用这样，controller层统一拦截！！！见：ExceptionController
 * @Description:TODO
 * @author:hsj qq:2356899074
 * @time:2017年12月13日 上午9:28:14
 */
//@Configuration
public class ShiroExceptionConfig {

	
//	@Bean
    public MyExceptionResolver MyExceptionResolverConfig() {
        return new MyExceptionResolver();
    }
}
