package com.cyb.springboot.controller;


import com.cyb.springboot.model.TestBean;
import com.cyb.springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("test22")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return "成功接收到请求！";
    }

    @RequestMapping("query")
    @ResponseBody
    public List<TestBean> query(){
        return testService.query();
    }
}

