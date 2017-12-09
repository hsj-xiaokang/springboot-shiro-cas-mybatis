package com.hdwang.dao;

import com.hdwang.entity.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by hdwang on 2017/6/15.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getById(int id) {
        //find by primary key
        return this.entityManager.find(User.class,id);
    }

    @Override
    public User getByName(String name) {
        Query query = this.entityManager.createQuery("from User u where u.name=:name",User.class);
        query.setParameter("name",name);
        User user = (User)query.getSingleResult();
        return user;
    }

    /*@Override
    public User getByName(String name) {
        Query query = this.entityManager.createQuery("from User u where u.name=:name",User.class);
        query.setParameter("name",name);
        User user = null;
        try {
            user = (User) query.getSingleResult();
        }catch (NoResultException noResultException){
            System.out.println("找不到用户！");
        }
        return user;
    }*/

    @Override
    public int addUser(User user) {
        this.entityManager.persist(user);
        //print the id
        System.out.println(user.getId());
        return user.getId();
    }

    @Override
    public void deleteUserById(int id) {
        User user = this.entityManager.find(User.class,id); //关联到记录，方可删除
        this.entityManager.remove(user);
    }

    @Override
    public User updateUser(User user) {
        User userNew = this.entityManager.merge(user);
        return userNew;
    }
}
