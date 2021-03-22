package com.cyb.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test2")
public class TestController {

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return "成功接收到请求！";
    }
}

