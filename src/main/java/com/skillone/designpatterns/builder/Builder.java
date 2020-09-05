package com.skillone.designpatterns.builder;

public interface Builder {

    Product product = new Product();

    void buildPartA();

    void buildPartB();

    void buildPartC();

    Product getResult();

}
