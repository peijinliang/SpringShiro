package com.shiro.dao;

import com.shiro.vo.User;

import java.util.List;

/**
 * Crete by Marlon
 * Create Date: 2018/6/28
 * Class Describe
 **/

public interface UserDao {

    User getPasswordByUserName(String userName);

    List<String> getRolesByUserName(String userName);

}
