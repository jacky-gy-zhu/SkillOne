package com.skillone.designpattern.factory.factorymethod.pizza;

public class ZhCheesePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Zh Cheese Pizza prepare...");
    }

    @Override
    public void bake() {
        System.out.println("Zh Cheese Pizza bake...");
    }

    @Override
    public void cut() {
        System.out.println("Zh Cheese Pizza cut...");
    }

    @Override
    public void box() {
        System.out.println("Zh Cheese Pizza box...");
    }
}
