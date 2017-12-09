package com.hdwang.service;

import com.hdwang.dao.UserDao;
import com.hdwang.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hdwang on 2017/6/15.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public com.hdwang.entity.User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public com.hdwang.entity.User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public int addUser(User user) {
        return this.userDao.addUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        this.userDao.deleteUserById(id);
    }

    @Override
    public User updateUser(User user,boolean throwEx) {
        User userNew = this.userDao.updateUser(user);
        if(throwEx){
            throw  new RuntimeException("throw a ex");
        }
        return userNew;
    }


}
