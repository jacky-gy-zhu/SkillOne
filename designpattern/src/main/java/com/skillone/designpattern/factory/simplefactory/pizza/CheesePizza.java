package com.skillone.designpattern.factory.simplefactory.pizza;

public class CheesePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Cheese Pizza prepare...");
    }

    @Override
    public void bake() {
        System.out.println("Cheese Pizza bake...");
    }

    @Override
    public void cut() {
        System.out.println("Cheese Pizza cut...");
    }

    @Override
    public void box() {
        System.out.println("Cheese Pizza box...");
    }
}
