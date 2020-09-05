package com.skillone.designpattern.strategy;

public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly none");
    }
}
