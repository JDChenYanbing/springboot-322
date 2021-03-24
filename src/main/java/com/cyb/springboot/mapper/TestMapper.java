package com.cyb.springboot.mapper;

import com.cyb.springboot.model.TestBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TestMapper {

    @Select("select * from test")
    List<TestBean> query();
}
