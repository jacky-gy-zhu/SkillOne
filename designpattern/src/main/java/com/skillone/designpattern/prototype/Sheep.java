package com.skillone.designpattern.prototype;

import java.io.Serializable;

public class Sheep implements Serializable, Cloneable {

    public Sheep(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    //克隆该实例，使用默认的clone方法来完成
    @Override
    public Object clone() {
        Sheep sheep = null;
        try {
            sheep = (Sheep)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sheep;
    }

}
