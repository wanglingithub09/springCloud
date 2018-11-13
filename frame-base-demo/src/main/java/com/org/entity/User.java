package com.org.entity;

import lombok.Data;
@Data
public class User {

    private Integer id;

    private String name;

    private Integer age;

    private String phone;

    public User(){}

    public User(Integer id, String name, Integer age,String phone){
        this.id=id;
        this.name=name;
        this.age=age;
        this.phone=phone;
    }
}
