package com.cascilent.demo.shiroException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cascilent.demo.config.CustomPac4jRealm;

/** 
 *  如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url
 *  但是不要使用这样，controller层统一拦截！！！见：ExceptionController
* 类名称：MyExceptionResolver.java 
* 类描述：  
* @author lsq 
* 作者单位：  
* 联系方式：QQ237442461 
* @version 1.0 
 */  
public class MyExceptionResolver implements HandlerExceptionResolver{  
	private final static Logger LOGGER = LoggerFactory.getLogger(MyExceptionResolver.class);
  
	@Override
    public ModelAndView resolveException(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex) {  
        // TODO Auto-generated method stub  
		LOGGER.info("==============异常开始=============");  
        //如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url  
        if(ex instanceof UnauthorizedException){ 
        	LOGGER.info("如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url  ");
            ModelAndView mv = new ModelAndView("static/html/ShiroUnauthorizedException.html");  
            return mv;  
        }  
        ex.printStackTrace();  
        LOGGER.info("==============异常结束=============");  
        ModelAndView mv = new ModelAndView("error");  
        mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));  
        return mv;  
    }
 
  
} 
