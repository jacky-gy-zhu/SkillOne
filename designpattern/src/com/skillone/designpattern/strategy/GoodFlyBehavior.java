package com.skillone.designpattern.strategy;

public class GoodFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly good");
    }
}
