/*
package com.cyb.springboot.shiro;


import com.cyb.springboot.model.UserBean;
import com.cyb.springboot.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm{


    //注入Servie层
    @Autowired
    private UserService userService;

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取到输入框输入的用户名
        String userName = (String)token.getPrincipal();
        //去后台查是否有此账号
        UserBean user = userService.queryUserByName(userName);
        //判断账号是否存在
        if(user == null){
            //如果用户对象为空 抛出用户名错误异常
            throw new UnknownAccountException();
        }
        //认证器 第一个参数为用户名或用户对象 第二个参数为密码 第三个参数为当前real名称
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName,user.getPassword(),this.getName());
        return simpleAuthenticationInfo;
    }

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
*/
