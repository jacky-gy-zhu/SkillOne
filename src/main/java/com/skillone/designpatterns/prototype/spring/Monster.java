package com.skillone.designpatterns.prototype.spring;

public class Monster {

    private int id = 10;
    private String name = "Jacky";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
