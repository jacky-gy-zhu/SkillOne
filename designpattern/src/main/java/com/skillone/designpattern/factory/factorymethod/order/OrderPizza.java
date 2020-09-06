package com.skillone.designpattern.factory.factorymethod.order;

import com.skillone.designpattern.factory.factorymethod.pizza.Pizza;

public abstract class OrderPizza {

    abstract Pizza createPizza(String type);

    private String type;

    public OrderPizza(String type) {
        this.type = type;
    }

    void order(){
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }

}
