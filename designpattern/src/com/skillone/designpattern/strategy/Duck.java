package com.skillone.designpattern.strategy;

public abstract class Duck {

    //属性，策略接口
    FlyBehavior flyBehavior;
    //其他属性


    public Duck() {
    }

    public abstract void display();

    public void fly() {
        if (flyBehavior != null) {
            flyBehavior.fly();
        }
    }
}
