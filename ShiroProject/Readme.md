
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

    可以通过自定义的Realm   

过滤器Filter：

认证过滤器
anon 不需要任何认证，直接可以访问
authBasic  HttpBasic 
authc  需要认证之后才可以进行访问
user   需要当前存在用户才可以访问
logout  退出


授权过滤器：
perms["prm1" ,"prm2"] 需要具备一些相关的权限才可以访问
roles[ "admin" , "user"] 需要一定的角色才可以访问
ssl  安全的协议https
port [6399,2342]  需要是中括号后边写的端口才可以访问

###Shiro 加密
Shiro 散列配置

HashedCredentialsMatcher
自定义Realm使用散列
盐的使用

### Shiro Session管理

SessionManager 
SessionDAO 实现session增删改查

Redis 实现Session共享
Redis 实现Session共享存在的问题

### Shiro 缓存管理
CacheManager 
Cache

Redis实现CacheMananger

### Shiro集成Spring
shiro-web 项目


