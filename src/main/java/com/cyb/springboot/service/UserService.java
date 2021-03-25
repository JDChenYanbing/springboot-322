package com.cyb.springboot.service;

import com.cyb.springboot.model.UserBean;

public interface UserService {



    UserBean queryUserByName(String userName);
}
