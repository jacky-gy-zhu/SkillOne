package com.skillone.designpattern.factory.abstractfactory.order;

public class PizzaStore {

    public static void main(String[] args) {
        OrderPizza order1 = new OrderPizza(new AuPizzaFactory(),"pepper");
        order1.order();
    }

}
