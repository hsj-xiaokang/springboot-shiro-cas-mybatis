package cn.wcj.sso.config.shiro;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * 
 * <p>Module:ShiroTagFreeMarkerConfigurer </p>
 * <p>Description: SpringBoot整合Shiro标签</p>
 * <p>Company:Software College Of ZhengZhou University </p> 
 * @author SuccessKey(WangCJ)
 * @date 2017年8月4日 下午8:34:19
 */
@Component
public class ShiroTagFreeMarkerConfig implements InitializingBean {

	@Autowired
	private Configuration configuration;

	@Autowired
	private FreeMarkerViewResolver resolver;

	@Override
	public void afterPropertiesSet() throws Exception {
		// 加上这句后，可以在页面上使用shiro标签
		configuration.setSharedVariable("shiro", new ShiroTags());
		// 加上这句后，可以在页面上用${context.contextPath}获取contextPath
		resolver.setRequestContextAttribute("context");
	}
	
}
