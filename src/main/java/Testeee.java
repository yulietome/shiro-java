import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class Testeee {
    public static void main(String[] args) {
        /**
         * 创建默认安全管理对象
         * */
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        /**
         * 设置安全管理器realm
         * */
        defaultSecurityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        /**
         * 给全局安全工具类设置安全管理器
         * */
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        /**
         * 关键对象subject主体
         * */
        Subject subject = SecurityUtils.getSubject();
        /**
         * creat UsernamePasswordToken
         * */
        UsernamePasswordToken token = new UsernamePasswordToken("xiaming","123");
        try{
            subject.login(token);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
