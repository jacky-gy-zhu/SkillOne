package com.skillone.designpatterns.strategy;

public class GoodFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly good");
    }
}
