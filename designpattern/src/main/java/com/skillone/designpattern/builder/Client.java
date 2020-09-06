package com.skillone.designpattern.builder;

public class Client {

    public static void main(String[] args) {
        //建造者
        ConcreteBuilder concreteBuilder = new ConcreteBuilder();
        //指挥者
        Director director = new Director(concreteBuilder);
        //建造
        director.construct();
        //建造后的产品
        Product product = concreteBuilder.getResult();
        //输出产品
        System.out.println(product);

        ConcreteBuilder2 concreteBuilder2 = new ConcreteBuilder2();
        director = new Director(concreteBuilder2);
        director.construct();
        product = concreteBuilder.getResult();
        System.out.println(product);
    }

}
