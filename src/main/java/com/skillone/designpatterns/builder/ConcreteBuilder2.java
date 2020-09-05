package com.skillone.designpatterns.builder;

public class ConcreteBuilder2 implements Builder {
    @Override
    public void buildPartA() {
        product.setName("White house2");
        System.out.println("buildPartA findished2...");
    }

    @Override
    public void buildPartB() {
        product.setWidth(3400);
        System.out.println("buildPartB findished2...");
    }

    @Override
    public void buildPartC() {
        product.setHeight(40);
        System.out.println("buildPartC findished2...");
    }

    @Override
    public Product getResult() {
        return product;
    }
}
