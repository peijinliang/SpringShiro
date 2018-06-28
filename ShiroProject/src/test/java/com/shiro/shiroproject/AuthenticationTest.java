package com.shiro.shiroproject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * Crete by Marlon
 * Create Date: 2018/6/28
 * Class Describe
 **/

public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        // 在数据库中添加用户
        simpleAccountRealm.addAccount("Mark", "123456", "admin","user");
    }

    /**
     * 如果账号错误报错：UnknownAccountException
     * 如果密码错误： IncorrectCredentialsException
     * 如果用户没有该角色报错 UnauthorizedException: Subject does not have role [admin1]
     */
    @Test
    public void testAuthentication() {
        //1.创建SecurityManager 环境
        DefaultSecurityManager defaultSecurityMananger = new DefaultSecurityManager();
        defaultSecurityMananger.setRealm(simpleAccountRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityMananger);
        Subject subject = SecurityUtils.getSubject();

        //创建一个类似于这样的主体请求认证
        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        subject.login(token);

        //3.打印认证结果
        System.out.println("是否认证的结果：" + subject.isAuthenticated());

        //4.退出认证
//        subject.logout();
//        System.out.println("是否认证的结果：" + subject.isAuthenticated());


// 检查授权

        //用户是否拥有这样的角色
//        subject.checkRole("admin");
        //同时check多个角色
        subject.checkRoles("admin","user");
    }


}
