package com.shiro.shiroproject;

import com.shiro.shiroproject.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Crete by Marlon
 * Create Date: 2018/6/28
 * Class Describe
 **/

public class CustomRealmTest {

    @Test
    public void testAuthentication() {
        //导入资源文件
        CustomRealm customRealm = new CustomRealm();

        //1.创建SecurityManager 环境
        DefaultSecurityManager defaultSecurityMananger = new DefaultSecurityManager();
        defaultSecurityMananger.setRealm(customRealm);

        //对密码进行加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //设置算法名称
        matcher.setHashAlgorithmName("md5");
        //设置加密次数
        matcher.setHashIterations(1);
        //设置进行加密
        customRealm.setCredentialsMatcher(matcher);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityMananger);
        Subject subject = SecurityUtils.getSubject();

        //创建一个类似于这样的主体请求认证
        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        subject.login(token);

        //3.打印认证结果
        System.out.println("是否认证的结果：" + subject.isAuthenticated());

        //用户是否拥有这样的角色
//        subject.checkRole("admin");

        //用户是否有删除的权限
        subject.checkPermission("user:delete");

        //用户是否有更新的权限
//        subject.checkPermission("user:update");
    }

    public static void main(String[] args) {
        //加盐是加随机数 目的是很让让别人看到
        Md5Hash md5Hash = new Md5Hash("1234567", "Mark");
        System.out.println(md5Hash.toString());
    }


}
