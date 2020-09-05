package com.skillone.designpattern.strategy;

public class BadFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly bad");
    }
}
