package com.testhelper.admin.shiro;

import com.testhelper.admin.entity.User;
import com.testhelper.admin.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired(required = false)
    private UserService userService;

    /**
       * 执行授权逻辑
       *
       * @param arg0
       * @return
       */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("执行授权逻辑");
//给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//不授权先不写
        return info;
    }

    /**
       * 执行认证逻辑
       *
       * @param arg0
       * @return
       * @throws AuthenticationException
       */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        // 判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;
        // 编写shiro判断逻辑，判断用户名和密码
        User user = userService.login(token.getUsername());
        // 该用户不存在
        if (user == null) {
            // shiro底层会抛出UnKnowAccountException
            return null;
        }
        // 判断密码
        return new SimpleAuthenticationInfo(user, user.getPwd(), "");
    }
}
