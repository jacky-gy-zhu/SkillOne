package com.skillone.designpatterns.strategy;

public class BeijingDuck extends Duck {

    public BeijingDuck() {
        flyBehavior = new NoFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("beijing duck");
    }

}
