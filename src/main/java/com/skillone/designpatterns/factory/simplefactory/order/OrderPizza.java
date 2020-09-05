package com.skillone.designpatterns.factory.simplefactory.order;

import com.skillone.designpatterns.factory.simplefactory.pizza.Pizza;

public class OrderPizza {

    private String type;

    public OrderPizza(String type) {
        this.type = type;
    }

    void order(){
        Pizza pizza = PizzaFactory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }

}
