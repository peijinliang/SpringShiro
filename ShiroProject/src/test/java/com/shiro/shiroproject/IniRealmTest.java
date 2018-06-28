package com.shiro.shiroproject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Crete by Marlon
 * Create Date: 2018/6/28
 * Class Describe
 **/
public class IniRealmTest {

    @Test
    public void testAuthentication() {
        //导入资源文件
        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        //1.创建SecurityManager 环境
        DefaultSecurityManager defaultSecurityMananger = new DefaultSecurityManager();
        defaultSecurityMananger.setRealm(iniRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityMananger);
        Subject subject = SecurityUtils.getSubject();

        //创建一个类似于这样的主体请求认证
        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        subject.login(token);

        //3.打印认证结果
        System.out.println("是否认证的结果：" + subject.isAuthenticated());

        //用户是否拥有这样的角色
        subject.checkRole("admin");

        //用户是否有删除的权限
        subject.checkPermission("user:delete");

        //用户是否有更新的权限
        subject.checkPermission("user:update");
    }

}
