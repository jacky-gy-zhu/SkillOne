package com.skillone.designpatterns.factory.simplefactory.pizza;

public class GreekPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Greek Pizza prepare...");
    }

    @Override
    public void bake() {
        System.out.println("Greek Pizza bake...");
    }

    @Override
    public void cut() {
        System.out.println("Greek Pizza cut...");
    }

    @Override
    public void box() {
        System.out.println("Greek Pizza box...");
    }
}
