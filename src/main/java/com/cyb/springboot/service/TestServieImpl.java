package com.cyb.springboot.service;


import com.cyb.springboot.mapper.TestMapper;
import com.cyb.springboot.model.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServieImpl implements TestService{

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<TestBean> query() {
        return testMapper.query();
    }
}
