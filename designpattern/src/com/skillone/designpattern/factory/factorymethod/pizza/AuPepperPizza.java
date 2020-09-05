package com.skillone.designpattern.factory.factorymethod.pizza;

public class AuPepperPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Au Pepper Pizza prepare...");
    }

    @Override
    public void bake() {
        System.out.println("Au Pepper Pizza bake...");
    }

    @Override
    public void cut() {
        System.out.println("Au Pepper Pizza cut...");
    }

    @Override
    public void box() {
        System.out.println("Au Pepper Pizza box...");
    }
}
