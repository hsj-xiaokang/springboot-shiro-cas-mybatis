package com.hdwang.controller;

import com.alibaba.fastjson.JSONObject;
import com.hdwang.entity.User;
import com.hdwang.service.datajpa.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by hdwang on 2017/6/19.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping("")
    public String index(HttpSession session, ModelMap map, HttpServletRequest request){
//        User user = (User) session.getAttribute("user");

        System.out.println(request.getUserPrincipal().getName());
        System.out.println(SecurityUtils.getSubject().getPrincipal());

        User loginUser = userService.getLoginUser();
        System.out.println(JSONObject.toJSONString(loginUser));

        map.put("user",loginUser);
        return "home";
    }
}
