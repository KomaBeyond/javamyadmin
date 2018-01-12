package org.koma.javamyadmin.auth;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * @author koma <komazhang@foxmail.com>
 */
public class MyAuthenticationInfo implements AuthenticationInfo {
    protected PrincipalCollection principals;
    protected Object credentials;

    public MyAuthenticationInfo (Object principal, Object credentials, String realmName) {
        this.principals = new SimplePrincipalCollection(principal, realmName);
        this.credentials = credentials;
    }

    public PrincipalCollection getPrincipals() {
        return this.principals;
    }

    public Object getCredentials() {
        return this.credentials;
    }
}
