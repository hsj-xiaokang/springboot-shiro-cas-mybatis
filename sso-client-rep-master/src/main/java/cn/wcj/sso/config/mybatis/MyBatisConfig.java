package cn.wcj.sso.config.mybatis;



import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
/**
 * 
 * <p>Module:MyBatisConfig </p>
 * <p>Description: MyBatis-SqlSession核心配置</p>
 * <p>Company:Software College Of ZhengZhou University </p> 
 * @author SuccessKey(WangCJ)
 * @date 2017年7月30日 下午6:49:28
 */
@Configuration
public class MyBatisConfig {
   
	@Autowired
	private DataSource dataSource   ;

	@Bean
	@ConditionalOnMissingBean //当容器没有指定Bean的情况下创建该对象
	public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource) ;  //设置数据源
		// 设置mybatis的主配置文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource mybatisConfigXml = resolver.getResource("classpath:mybatis/mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(mybatisConfigXml);
        // 设置别名包
        sqlSessionFactoryBean.setTypeAliasesPackage("cn.wcj.sso.pojo.po");
        //设置Mapper.xml文件的位置
        Resource[] mapperLocations = resolver.getResources("classpath:mybatis/mapper/*.xml");
		sqlSessionFactoryBean.setMapperLocations(mapperLocations); //加载Mapper.XML文件
		return  sqlSessionFactoryBean  ;
	}
	
	
	
	
}
