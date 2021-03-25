package com.cyb.springboot.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    /**
     * 当用户名密码不正确时
     * 跳转到此方法
     * 认证失败之后会进入此方法 提示用户失败原因
     * @return
     */
    @RequestMapping("login")
    public String login(HttpServletRequest request, ModelMap map){
        //认证器会根据对应的错误 返回对应的异常 根据异常判断对应错误
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");

        if(UnknownAccountException.class.getName().equals(exceptionClassName)
                || AuthenticationException.class.getName().equals(exceptionClassName)){
            System.out.println("request = [ 用户名异常]");
            map.put("message","用户名错误");
        }else{
            System.out.println("request = [密码异常]");
            map.put("message","密码错误");
        }
        return "login";
    }
    @RequestMapping("toLogin")
    public String toLogin(){
        //登陆页面
        return "login";
    }
    @RequestMapping("index")
    public String toIndex(){
        //主页面
        return "main";
    }
}

