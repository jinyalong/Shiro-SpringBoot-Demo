package com.codefriday.controller;

import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author codefriday
 * @data 2021/4/5
 */
@Controller
public class ViewController {
    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","Hello,Shiro!");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model,HttpSession session){

        //获得Subject
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //token.setRememberMe(true);
        //执行登录方法
        try {
            subject.login(token);
            //session.setAttribute("loginUser","username");
            return "/index";
        }catch (UnknownAccountException e1){
            model.addAttribute("msg","用户名不正确！");
            return "/login";
        }catch (IncorrectCredentialsException e2){
            model.addAttribute("msg","密码不正确！");
            return "/login";
        }
    }

    @RequestMapping("/noauth")
    public String noauth(){
        return "noauth";
    }

    @RequestMapping("/logout")
    public String LogOut(HttpSession session){
        //session.removeAttribute("loginUser");//下面一行会自动注销session
        SecurityUtils.getSubject().logout();
        return "index";
    }
}
