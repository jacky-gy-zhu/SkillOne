package com.skillone.designpatterns.factory.factorymethod.pizza;

public class ZhPepperPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Zh Pepper Pizza prepare...");
    }

    @Override
    public void bake() {
        System.out.println("Zh Pepper bake...");
    }

    @Override
    public void cut() {
        System.out.println("Zh Pepper cut...");
    }

    @Override
    public void box() {
        System.out.println("Zh Pepper box...");
    }
}
