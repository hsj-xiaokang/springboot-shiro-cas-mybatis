package com.hdwang;

import com.alibaba.fastjson.JSONObject;
import com.hdwang.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created by hdwang on 2017/6/5.
 */
//@EntityScan(value="com.hdwang.entity")
    @ServletComponentScan
@SpringBootApplication
public class Application {

    /**
     * main function
     * @param args params
     */
    public static void main(String[] args){
        /*ConfigurableApplicationContext context = */SpringApplication.run(Application.class,args);

        //test if a xml bean inject into springcontext successful
        //User user = (User)context.getBean("user1");
        //System.out.println(JSONObject.toJSONString(user));
    }
}
