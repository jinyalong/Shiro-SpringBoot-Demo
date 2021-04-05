package com.codefriday.config;

import com.codefriday.pojo.User;
import com.codefriday.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author codefriday
 * @data 2021/4/5
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了====>授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User currentUser = (User) principalCollection.getPrimaryPrincipal();
        info.addStringPermission(currentUser.getPerms());
        System.out.println("AuthorizationInfo====>"+currentUser.getPerms());
        return info;
    }

    //认证，登录就会走这个方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.getUserByName(token.getUsername());
        if(user == null) return null;
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
