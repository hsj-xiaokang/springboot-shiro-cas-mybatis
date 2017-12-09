package com.hdwang.controller;

import com.hdwang.common.Constant;
import com.hdwang.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hdwang on 2017/6/5.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @Value("${msg:Welcome!}")
    private String msg;

    /**
     * get a page
     * @return a page with name called return value
     */
    @RequestMapping(value="login",method= RequestMethod.GET)
    public String getLoginPage(ModelMap map){
        map.put("welcomeMsg",this.msg);
        return "login";
    }

    /**
     * do login
     * @param user userInfo
     * @param request requestInfo
     * @param response responseInfo
     * @param map data
     * @return login result
     * @throws IOException
     */
    @RequestMapping(value="login",method = RequestMethod.POST)
    public String doLogin(User user, HttpServletRequest request, HttpServletResponse response,ModelMap map) throws IOException{
        if(!StringUtils.isEmpty(user.getName())){
            request.getSession().setAttribute(Constant.SESSION_USER_KEY,user);

            //login success, redirect to home controller, change the url also. if return viewName,can't change url.
            response.sendRedirect("/home");
            return null;
        }else{
            map.put("welcomeMsg",this.msg);
            return "login"; //login failure, return login page
        }
    }

    /**
     * logout
     * @param session session object
     * @param response response object
     * @throws IOException
     */
    @RequestMapping(value="logout")
    public void getLoginPage(HttpSession session,HttpServletResponse response) throws IOException{
        session.invalidate();
        response.sendRedirect("/common/login");
    }

}
