     package com.baizhi.shirojava.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomizeMD5Realm extends AuthorizingRealm {
    public static  HashedCredentialsMatcher credentialsMatcher;
    static {
         credentialsMatcher = new HashedCredentialsMatcher("md5");
        credentialsMatcher.setHashIterations(1024);
    }
    public CustomizeMD5Realm() {
        super();
        super.setCredentialsMatcher(credentialsMatcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * doGetAuthenticationInfo 获取认证信息
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String)token.getPrincipal();
        AuthenticationInfo authenticationInfo=null;
        if (principal.equals("zhangsan")){
            /**
             *param1:Username;param2:password;param3:当前类名
             * */
            authenticationInfo  = new SimpleAuthenticationInfo("zhangsan","9c8e3523a2a1ceb964917d0dc71e10f9",ByteSource.Util.bytes("dasfff") ,this.getName());
            return authenticationInfo;
        }
        return null;
    }
}
