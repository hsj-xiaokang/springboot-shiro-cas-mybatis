package cn.wcj.sso.config.common;

import javax.sql.DataSource;





import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * <p>Module:DataSourceConfig </p>
 * <p>Description:配置数据源 </p>
 * <p>Company:Software College Of ZhengZhou University </p> 
 * @author SuccessKey(WangCJ)
 * @date 2017年7月30日 下午5:16:47
 */
@PropertySource(value = { "classpath:common/jdbc.properties"})
@Configuration
public class DataSourceConfig {

	@Value("${jdbc.driverClass}")
	private String driverClass  ;
	
	@Value("${jdbc.jdbcUrl}")
	private String jdbcUrl  ;
	
	@Value("${jdbc.username}")
	private String username  ;
	
	@Value("${jdbc.password}")
	private String password  ;
	
	
	@Bean
	public DataSource dataSource(){
		DruidDataSource druidDataSource=new DruidDataSource()   ;
		druidDataSource.setDriverClassName(driverClass);
		druidDataSource.setUrl(jdbcUrl);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		return druidDataSource ;
	}
	
}
