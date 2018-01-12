package org.koma.javamyadmin.auth;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author koma <komazhang@foxmail.com>
 */
public class MyAuthenticator extends AuthorizingRealm {
    /**
     * 获取登录用户身份认证信息
     *
     * 在这里判断当用户不存在或者密码错误或者失败次数太多等情况
     * 时抛出相应的异常
     *
     * 当调用 login 方法时就会调用该方法
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println(token.getHost());
        System.out.println(token.getUsername());
        System.out.println(token.getPassword());

        MyAuthenticationInfo authenticationInfo = new MyAuthenticationInfo(
                token.getUsername(),
                token.getPassword(),
                getName()
        );
        return authenticationInfo;
    }

    /**
     * 获取登录用户权限信息
     *
     * 在这里根据用户的身份信息获取并设置用户对应的角色和权限信息
     * 方便 subject 去调用相应的权限或角色检测方法去检测用户的操作是否具备权限
     *
     * 只有调用对应的权限检测代码时才会调用该方法
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("get authorization");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }
}
