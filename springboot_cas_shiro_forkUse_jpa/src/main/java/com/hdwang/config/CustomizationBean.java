package com.hdwang.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by hdwang on 2017/6/6.
 * 定制servlet容器
 */
//@Component
public class CustomizationBean implements EmbeddedServletContainerCustomizer{

    /**
     * 定制方法一：实现EmbeddedServletContainerCustomizer
     * @param container
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        //container.setPort(8083);
    }

    /**
     * 定制方法二：注入EmbeddedServletContainerFactory
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(8083);
        factory.setSessionTimeout(10, TimeUnit.MINUTES);
        //优先级高于配置在/static/error文件夹里面的404.html页面
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/403.html"));
        return factory;
    }
}
