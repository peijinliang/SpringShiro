package com.shiro.shiroproject;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import javax.sql.DataSource;

/**
 * Crete by Marlon
 * Create Date: 2018/6/28
 * Class Describe
 **/

public class JdbcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("");
    }


    @Test
    public void testAuthentication() {
        //需要引入jdbc 数据包和数据源
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //注意 在使用jdbcRealm 的时候应该注意他的开关
        jdbcRealm.setPermissionsLookupEnabled(true);

        //采用自己的sql查询语句
        String sql = "select password from test_user where username = ?";
        jdbcRealm.setAuthenticationQuery(sql);

        //采用自己的sql语句查询角色
        String rolesql = "select user_role from test_user_role where username = ?";
        jdbcRealm.setUserRolesQuery(rolesql);

        //采用自己的sql语句查询权限
//        String  permissionsql = "";
//        jdbcRealm.setPermissionsQuery(permissionsql);

        //1.创建SecurityManager 环境
        DefaultSecurityManager defaultSecurityMananger = new DefaultSecurityManager();
        defaultSecurityMananger.setRealm(jdbcRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityMananger);
        Subject subject = SecurityUtils.getSubject();

        //创建一个类似于这样的主体请求认证
//        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
//        subject.login(token);

        //使用自定义的sql语句 进行认证
        UsernamePasswordToken token = new UsernamePasswordToken("xiaoming", "654321");
        subject.login(token);

//        //3.打印认证结果
//        System.out.println("是否认证的结果：" + subject.isAuthenticated());
//
//        //用户是否拥有这样的角色
//        subject.checkRole("admin");
          subject.checkRole("user");

//        //用户是否有删除的权限
//        subject.checkPermission("user:delete");
//
//        //用户是否有更新的权限
//        subject.checkPermission("user:update");
    }

}
