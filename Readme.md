
# Shiro安全框架简介

Apache的强大灵活的开源安全框架。

认证、授权、企业会话管理、安全加密。
Shrio    与  Spring Security
简单灵活         复杂笨重
可脱离Spring   不可脱离spring
粒度较粗       粒度更细

##认识Shiro的整体架构,各组件的概念


##SecurityMananger的构成：

1)Authenticator: 认证和登出
2)Authorizer 赋予我们主体有哪一些权限
3)Session Manager   一套session管理机制
4)Session Dao   提供了 Session 的增删改查操作
5)Cache Manager 缓存管理：缓存角色数据和权限数据
6)Pluggable Realms 数据库和数据源之间的桥梁;获取认证数据、权限数据来获取
7)Cryptography 数据加密


## Shiro认证,授权的过程

###Shiro认证过程：
1)创建SecurityMananger对象，构建SecurityMananger 环境
2)主体提交认证请求
3)SecurityMananger认证
4)通过Authenticator认证
5)Realm 验证


###Shiro授权过程：

1)创建SecurityMananger  管理类;
2)主体授权;
3)SecurityManager 授权;
4) Authorizer 授权;
5) Realm 获取角色权限数据;

### Shiro自定义的Realm(领域、范围),Filter

内置Realm:

IniRealm 
JdbcRealm
    创建jdbc默认表：
   
    
    DROP TABLE IF EXISTS users;
    create table users(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户表主键',
    username VARCHAR(20) COMMENT '用户名',
    password  VARCHAR(100) COMMENT '密码' 
    ) charset=utf8 ENGINE=InnoDB;
    
    
    DROP TABLE IF EXISTS user_roles;
    create  table user_roles(
    id bigint PRIMARY KEY auto_increment comment '角色表主键',
    username VARCHAR(20) comment '用户名',
    role_name VARCHAR(20) comment '角色名称' 
    ) charset=utf8 ENGINE=InnoDB;
    
    
    DROP TABLE IF EXISTS roles_permissions;
    create table roles_permissions(
    id bigint PRIMARY KEY auto_increment COMMENT '权限表主键',
    role_name  VARCHAR(20) COMMENT '角色名称' ,
    permission VARCHAR(20) COMMENT '权限名称'
    ) charset=utf8 ENGINE=InnoDB;
    
    
    使用自己的表：
    DROP TABLE IF EXISTS test_user;
    create table test_user(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户表主键',
    username VARCHAR(20) COMMENT '用户名',
    password  VARCHAR(20) COMMENT '密码' 
    ) charset=utf8 ENGINE=InnoDB;

    DROP TABLE IF EXISTS test_user_role;
    create table test_user_role(
    username VARCHAR(20) COMMENT '用户名',
    user_role  VARCHAR(20) COMMENT '角色' 
    ) charset=utf8 ENGINE=InnoDB;

自定义的Realm

###Shiro 加密
Shiro 散列配置

HashedCredentialsMatcher
自定义Realm使用散列
盐的使用

### Shiro集成Spring
shiro-web 项目

### Shiro Session管理


### Shiro 缓存管理



