package com.baizhi.shirojava;
import com.baizhi.shirojava.realm.MyRealm;
import com.baizhi.shirojava.realm.MyRealm1;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
class ShiroJavaApplicationTests {

    @Test
    void contextLoads() {
    /**
     * 获取主体对象
     * */
        MyRealm1 realm = new MyRealm1();
        //创建凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        //将凭证匹配器加入自定义realm中
        realm.setCredentialsMatcher(credentialsMatcher);
        AuthenticationToken usernamePasswordToken = new UsernamePasswordToken("zhangsan","123456");
        SecurityManager securityManager = new DefaultSecurityManager(realm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException ae){
            ae.printStackTrace();
        }finally {
            boolean b =subject.isAuthenticated();
            System.out.println(b);
            if (b){
                List<String> strings = Arrays.asList("admin", "super");
                boolean aSuper = subject.hasRole("super");
                boolean[] booleans = subject.hasRoles(strings);
                for (boolean aBoolean : booleans) {
                    System.out.println("aBoolean:"+aBoolean);
                }

                System.out.println(aSuper);
            }
        }

    }
    @Test
    void contextLoads2(){
        Md5Hash md5Hash=new Md5Hash("123456","ssss",1024);
        String s = md5Hash.toHex();
        System.out.println(s);
    }
}
