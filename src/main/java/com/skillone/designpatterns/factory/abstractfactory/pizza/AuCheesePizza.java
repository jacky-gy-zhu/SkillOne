package com.skillone.designpatterns.factory.abstractfactory.pizza;

import java.util.Calendar;

public class AuCheesePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Au Cheese Pizza prepare...");
    }

    @Override
    public void bake() {
        System.out.println("Au Cheese Pizza bake...");
    }

    @Override
    public void cut() {
        System.out.println("Au Cheese Pizza cut...");
    }

    @Override
    public void box() {
        System.out.println("Au Cheese Pizza box...");
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
    }
}
