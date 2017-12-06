package cn.wcj.sso.config.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * <p>Module:MapperScannerConfig </p>
 * <p>Description: 扫描MyBatis的Mapper接口</p>
 * <p>Company:Software College Of ZhengZhou University </p> 
 * @author SuccessKey(WangCJ)
 * @date 2017年7月30日 下午6:50:08
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MapperScannerConfig {
      
	
    //扫描Mapper接口
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("cn.wcj.sso.mapper");
        return mapperScannerConfigurer;
    }
	
}
