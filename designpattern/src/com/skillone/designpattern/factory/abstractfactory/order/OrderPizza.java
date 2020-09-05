package com.skillone.designpattern.factory.abstractfactory.order;

import com.skillone.designpattern.factory.abstractfactory.pizza.Pizza;

public class OrderPizza {

    private String type;
    private PizzaFactory pizzaFactory;

    public OrderPizza(PizzaFactory pizzaFactory, String type) {
        this.type = type;
        this.pizzaFactory = pizzaFactory;
    }

    void order(){
        Pizza pizza = pizzaFactory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }

}
