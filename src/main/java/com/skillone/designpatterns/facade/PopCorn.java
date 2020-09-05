package com.skillone.designpatterns.facade;

public class PopCorn {

    private PopCorn() {}

    private static PopCorn instance = new PopCorn();

    public static PopCorn getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("popcorn on");
    }

    public void off() {
        System.out.println("popcorn off");
    }

    public void pop() {
        System.out.println("popcorn is popping");
    }

}
