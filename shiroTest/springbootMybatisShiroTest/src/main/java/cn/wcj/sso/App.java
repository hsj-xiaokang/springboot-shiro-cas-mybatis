package cn.wcj.sso;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.wcj.sso.config.ApplicationConfig;


/**
 * 
 * <p>Module:App </p>
 * <p>Description:SpringBoot整合CAS </p>
 * <p>Company:Software College Of ZhengZhou University </p> 
 * @author SuccessKey(WangCJ)
 * @date 2017年7月30日 下午4:53:51
 */
@SpringBootApplication
@SpringBootConfiguration
//@MapperScan("cn.wcj.sso.mapper")
public class App {
     public static void main(String[] args) throws Exception{
     	SpringApplication application = new SpringApplication(ApplicationConfig.class);
     	application.run(args)   ;
	  }
}
