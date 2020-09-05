package com.skillone.designpatterns.prototype.deepclone;

import com.skillone.designpatterns.prototype.Sheep;

import java.io.*;

public class Farmer implements Serializable, Cloneable {

    public Farmer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;
    private Sheep sheep;

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

    public Sheep getSheep() {
        return sheep;
    }

    public void setSheep(Sheep sheep) {
        this.sheep = sheep;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object target = null;
        target = super.clone();
        ((Farmer)target).setSheep((Sheep)sheep.clone());
        return target;
    }

    public Object deepClone() {
        Object copy = null;
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        ) {
            objectOutputStream.writeObject(this);
            try(
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    ){
                copy = objectInputStream.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copy;
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sheep=" + sheep +
                '}'+sheep.hashCode();
    }
}
