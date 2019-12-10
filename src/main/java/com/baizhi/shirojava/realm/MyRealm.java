package com.baizhi.shirojava.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

public class MyRealm extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        AuthenticationInfo authenticationInfo = null;

        if (principal.equals("zhangsan")){
            authenticationInfo  = new SimpleAuthenticationInfo("zhangsan","7fc690da7fb084b0cbc490412d25ecbc", ByteSource.Util.bytes("ssss"),this.getName());
        }
        return authenticationInfo;
    }
}
