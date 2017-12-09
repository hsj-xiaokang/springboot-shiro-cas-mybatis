package com.hdwang.config;

import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by hdwang on 2017/6/6.
 * （ 异常流程 controller->出错跳转->BasicErrorController->调用errorHtml方法->getErrorAttributes(获取错误属性返回model)->丢给页面处理器ErrorViewResolver）
 * 自定义错误映射页面
 */
@Component
public class MyErrorViewResolver implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        if(status!= HttpStatus.NOT_FOUND){
//            Set<Map.Entry<String,Object>> entrySet = model.entrySet();
//            for(Map.Entry<String,Object> entry:entrySet){
//                System.out.print(entry.getKey()+":"+entry.getValue()+",");
//            }
//            timestamp:Tue Jun 06 14:53:31 CST 2017,status:500,error:Internal Server Error,exception:java.lang.ArithmeticException,message:/ by zero,path:/test/throwex,2017-06-06 14:58:52.727 ERROR 2731 --- [0.1-8081-exec-4] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet dispatcherServlet threw exception

//            Map<String,Object> data = new HashMap<>();
//            String errorMsg = request.getAttribute("javax.servlet.error.exception").toString();
//            data.put("errorMsg",errorMsg);
//            data.putAll(model);
            return new ModelAndView("/error/error",model);
        }
        return null;
    }

}
