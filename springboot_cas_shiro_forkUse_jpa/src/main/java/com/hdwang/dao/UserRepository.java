package com.hdwang.dao;

import com.hdwang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by hdwang on 2017-06-17.
 * spring data jpa 方式操作数据库
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * spring data jpa 会自动注入实现（根据方法命名规范）
     * @return
     */
    User findByName(String name);

    @Modifying
    @Query("delete from User u where u.id = :id")
    void deleteUser(@Param("id") int id);
}
