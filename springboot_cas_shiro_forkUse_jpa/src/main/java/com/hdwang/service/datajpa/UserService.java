package com.hdwang.service.datajpa;

import com.hdwang.entity.User;
import java.util.List;

/**
 * Created by hdwang on 2017-06-17.
 */
public interface UserService {

    User findById(int id);

    User findByName(String name);

    List<User> findAllUserByPage(int page, int size);

    User updateUser(User user, boolean throwEx);

    void deleteUser(int id);

    User getLoginUser();
}
