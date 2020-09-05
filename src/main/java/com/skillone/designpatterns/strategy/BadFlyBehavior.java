package com.skillone.designpatterns.strategy;

public class BadFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly bad");
    }
}
