package com.skillone.designpatterns.factory.simplefactory.order;

public class PizzerStore {

    public static void main(String[] args) {
        OrderPizza oder1 = new OrderPizza("cheese");
        OrderPizza oder2 = new OrderPizza("greek");
        oder1.order();
        oder2.order();
    }

}
