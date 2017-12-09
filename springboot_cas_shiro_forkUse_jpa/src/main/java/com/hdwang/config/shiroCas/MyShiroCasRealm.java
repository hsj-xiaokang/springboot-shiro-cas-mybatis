package com.hdwang.config.shiroCas;

import javax.annotation.PostConstruct;

import com.hdwang.dao.UserDao;
import com.hdwang.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hdwang on 2017/6/20.
 * 安全数据源
 */
public class MyShiroCasRealm extends CasRealm{

    private static final Logger logger = LoggerFactory.getLogger(MyShiroCasRealm.class);

    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void initProperty(){
//      setDefaultRoles("ROLE_USER");
        setCasServerUrlPrefix(ShiroCasConfiguration.casServerUrlPrefix);
        // 客户端回调地址
        setCasService(ShiroCasConfiguration.shiroServerUrlPrefix + ShiroCasConfiguration.casFilterUrlPattern);
    }

//    /**
//     * 1、CAS认证 ,验证用户身份
//     * 2、将用户基本信息设置到会话中(不用了，随时可以获取的)
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
//
//        AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
//
//        String account = (String) authc.getPrincipals().getPrimaryPrincipal();
//
//        User user = userDao.getByName(account);
//        //将用户信息存入session中
//        SecurityUtils.getSubject().getSession().setAttribute("user", user);
//
//        return authc;
//    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     * @see 经测试：本例中该方法的调用时机为需授权资源被访问时
     * @see 经测试：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * @see 经测试：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        String loginName = (String)super.getAvailablePrincipal(principalCollection);

        //到数据库查是否有此对象(1.本地查询 2.可以远程查询casserver 3.可以由casserver带过来角色／权限其它信息)
        User user=userDao.getByName(loginName);// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        if(user!=null){
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            //给用户添加角色（让shiro去验证）
            Set<String> roleNames = new HashSet<>();
            if(user.getName().equals("boy5")){
                roleNames.add("admin");
            }
            info.setRoles(roleNames);

            if(user.getName().equals("李四")){
                //给用户添加权限（让shiro去验证）
                info.addStringPermission("user:delete");
            }


            // 或者按下面这样添加
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
//            simpleAuthorInfo.addRole("admin");
            //添加权限
//            simpleAuthorInfo.addStringPermission("admin:manage");
//            logger.info("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

}