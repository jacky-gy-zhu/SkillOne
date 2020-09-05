package com.skillone.designpatterns.factory.factorymethod.order;

public class PizzerStore {

    public static void main(String[] args) {
        OrderPizza oder1 = new AuOrderPizza("cheese");
        OrderPizza oder2 = new ZhOrderPizza("pepper");
        oder1.order();
        oder2.order();
    }

}
