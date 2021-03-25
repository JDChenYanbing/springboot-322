package com.cyb.springboot.mapper;

import com.cyb.springboot.model.UserBean;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {


    @Select("select * from user where username = #{userName}")
    UserBean queryUserByName(String userName);
}
