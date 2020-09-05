package com.skillone.designpatterns.strategy;

public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly none");
    }
}
