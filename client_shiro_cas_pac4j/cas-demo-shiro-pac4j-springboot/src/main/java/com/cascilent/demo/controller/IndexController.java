package com.cascilent.demo.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.profile.CasProfile;
import org.pac4j.cas.profile.CasRestProfile;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.jwt.profile.JwtGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cascilent.demo.config.CustomPac4jRealm;

import net.minidev.json.JSONObject;

/**
 * 
 * @author hsj
 * 
 * 
 * 3.由于我使用shiro ,所以client 中获取代码如下:
   Subject subject = SecurityUtils.getSubject();
   Map attributes = (Map) subject.getPrincipals().asList().get(1);
	//获取id
    String userId=(String) attributes.get("id");
	String telPhone=(String) attributes.get("username")
	String telPhone=(String) attributes.get("password")
	String telPhone=(String) attributes.get("active")
 *
 */
@RestController
public class IndexController {
	private final static String SPL = "|";
	private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @SuppressWarnings("rawtypes")
	@Autowired
    private JwtGenerator generator;

    @Autowired
    private CasRestFormClient casRestFormClient;

    @Value("${cas.serviceUrl}")
    private String serviceUrl;
    
    @Value("${cas.logoutUrl}")
    private String logoutUrl;

    @GetMapping("/")
    public Object index() {
        return "index page";
    }

    @RequiresPermissions(value = { "user:edit" })
    @GetMapping("/user/{id}")
    public Object user(@PathVariable(value = "id") String id,HttpServletRequest request) {
    	   Subject subject = SecurityUtils.getSubject();
    	   //gonson解析
    	   PrincipalCollection att = subject.getPrincipals();
    	   LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>> {}",att);
//    	   JSONArray ja = JSON.parseArray(subject.getPrincipals().toString());
//    	   LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>> {}",ja);
    	   

        return SecurityUtils.getSubject().getPrincipals();
    }

    @RequiresPermissions(value = { "user:edit10" })
    @GetMapping("/user/detail")
    public Object detail(HttpServletRequest request) {
        return "users:" + request.getUserPrincipal().getName();
    }

    /**
     * app rest 登录获取token
     * eg:http://localhost:8081/user/login?cilent_name=rest&username=hsjhsj&password=hsjhsj
     * 然后获取资源：http://localhost:8081/user/1?token=eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiZGlyIn0..7usGh1GK3jl5_wPH.QJdYqNp81zRyAs6OHmN4573l67z_UgxQ7WXJ7OUsDw50Dato2X9Tyh5kXBAJF5l9LmmKe8y-kHrhyx9gcEIa6PC97mo5fPbCw9WoOypyTqdWkE1Q9mM44Zn8CZZVH9PTml7_0jwln0W_bzDWjN3f-0Pk2etxU6lXwz5insFVz4nGt5SEmykhvOdKlscLsYbHGQVqze4nlXuAtVXQ08CuphRsZ2FmSaK-LFR8Ivs.DkqbT-PgEjE0ZS6pgNVqGA
     * @Description:TODO
     * @author:hsj qq:2356899074
     * @time:2017年12月11日 下午2:36:30
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/user/login")
    public Object login(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();
        J2EContext context = new J2EContext(request, response);
        final ProfileManager<CasRestProfile> manager = new ProfileManager(context);
        final Optional<CasRestProfile> profile = manager.get(true);
        //获取ticket
        TokenCredentials tokenCredentials = casRestFormClient.requestServiceTicket(serviceUrl, profile.get(), context);
        //根据ticket获取用户信息
        final CasProfile casProfile = casRestFormClient.validateServiceTicket(serviceUrl, tokenCredentials, context);
        //生成jwt token
        String token = generator.generate(casProfile);
        model.put("token", token);
        return new HttpEntity<>(model);
    }
    
    /* 单点登出
	 * @return
	 */
	@RequestMapping(value = "/logout", method = { RequestMethod.POST, RequestMethod.GET })
	public Object   logout(){
		JSONObject result = new JSONObject();
		result.put("logoutUrl", logoutUrl+"?service=" +serviceUrl);
		return result;
	} 
}
