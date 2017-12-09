package com.hdwang.controller;

import com.hdwang.config.shiroCas.ShiroCasConfiguration;
import com.hdwang.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by hdwang on 2017/6/21.
 * 跳转至cas server去登录（一个入口）
 */
@Controller
@RequestMapping("")
public class CasLoginController {

    /**
     * 一般用不到
     * @param model
     * @return
     */
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String loginForm(Model model){
        model.addAttribute("user", new User());
      return "login";
  //      return "redirect:" + ShiroCasConfiguration.loginUrl;
    }


    @RequestMapping(value = "logout", method = { RequestMethod.GET,
            RequestMethod.POST })
    public String loginout(HttpSession session)
    {
        return "redirect:"+ShiroCasConfiguration.logoutUrl;
    }
}
