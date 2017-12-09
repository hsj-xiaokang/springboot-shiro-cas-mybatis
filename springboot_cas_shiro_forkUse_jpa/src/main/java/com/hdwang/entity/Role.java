package com.hdwang.entity;

import javax.persistence.*;

/**
 * Created by hdwang on 2017/6/20.
 */
@Entity
@Table(name="role")
public class Role {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
