package com.hdwang.dao;

import com.hdwang.entity.User;

/**
 * Created by hdwang on 2017/6/15.
 * jpa方式操作数据库
 */
public interface UserDao{

    User getById(int id);

    //User getByNumber(String number);


    User getByName(String name);


    int addUser(User user);

    void deleteUserById(int id);

    User updateUser(User user);

}
