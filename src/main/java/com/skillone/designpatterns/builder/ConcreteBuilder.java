package com.skillone.designpatterns.builder;

public class ConcreteBuilder implements Builder {
    @Override
    public void buildPartA() {
        product.setName("White house");
        System.out.println("buildPartA findished...");
    }

    @Override
    public void buildPartB() {
        product.setWidth(1200);
        System.out.println("buildPartB findished...");
    }

    @Override
    public void buildPartC() {
        product.setHeight(20);
        System.out.println("buildPartC findished...");
    }

    @Override
    public Product getResult() {
        return product;
    }
}
