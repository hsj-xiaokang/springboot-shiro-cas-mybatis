package com.cascilent.demo.shiroException;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一拦截异常
 * 
 * @Description:TODO
 * @author:hsj qq:2356899074
 * @time:2017年12月13日 上午9:42:51
 */
@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(value = UnauthorizedException.class) // 处理访问方法时权限不足问题
	public String defaultErrorHandler(HttpServletRequest req, Exception e) {
		return "你无权访问该方法！---403";
	}
}
