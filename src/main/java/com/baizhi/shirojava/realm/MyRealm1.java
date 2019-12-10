package com.baizhi.shirojava.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;


import java.security.Principal;

public class MyRealm1 extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo=null;
        if (primaryPrincipal.equals("zhangsan")){
            authorizationInfo= new SimpleAuthorizationInfo();
            authorizationInfo.addRole("super");

        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String)authenticationToken.getPrincipal();
        AuthenticationInfo authenticationInfo=null;
        if (principal.equals("zhangsan")){
            authenticationInfo  = new SimpleAuthenticationInfo("zhangsan","7fc690da7fb084b0cbc490412d25ecbc", ByteSource.Util.bytes("ssss"),this.getName());
        }
        return authenticationInfo;
    }
}
