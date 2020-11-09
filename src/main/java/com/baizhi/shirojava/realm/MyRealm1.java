package com.baizhi.shirojava.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
/**
 * 本类继承AuthorizingRealm
 * AuthorizingRealm(该类定义抽象方法doGetAuthorizationInfo 即获取授权信息方法)
 * AuthorizingRealm继承AuthenticatingRealm类
 * AuthenticatingRealm(该类定义了抽象方法doGetAuthenticationInfo 即获取认证信息)
 * 若自定义Realm只需要获取认证信息,则只需要继承AuthenticatingRealm类 若自定义Realm需完成认证并授权,则需要继承AuthorizingRealm
 * */
public class MyRealm1 extends AuthorizingRealm {
    /**
     *doGetAuthorizationInfo 获取授权信息方法
     *      授权的两种方式:
     *          1.基于角色的访问控制(Role-Base Access Control),以角色为中心进行访问控制.
     *              if(subject.hasRole("role"){
     *                  //可控资源
     *              });
     *          2.基于资源的访问控制(Resource-base Access Control),以资源为中心进行访问控制.
     *              if(subject.isPermission(user:update:01)){
     *
     *              }
     *         权限字符串:
     *              权限字符串规则: 资源标识符:操作:资源实例标识符,那个资源的那个实例可以进行哪些操作
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //根据身份信息获取当前用户的角色信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo=null;
        if (primaryPrincipal.equals("zhangsan")){
            authorizationInfo= new SimpleAuthorizationInfo();
            authorizationInfo.addRole("super");
            authorizationInfo.addStringPermission("");
        }
        return authorizationInfo;
    }
    /**
     * doGetAuthenticationInfo 获取认证信息
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String)authenticationToken.getPrincipal();
        AuthenticationInfo authenticationInfo=null;
        if (principal.equals("zhangsan")){
            /**
             *param1:Username;param2:password;param3:随机盐(salt);param4:当前类名;
             * */
            authenticationInfo  = new SimpleAuthenticationInfo("zhangsan","7fc690da7fb084b0cbc490412d25ecbc", ByteSource.Util.bytes("ssss"),this.getName());
            return authenticationInfo;
        }
        return null;
    }
}
