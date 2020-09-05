package com.skillone.designpatterns.factory.abstractfactory.order;

import com.skillone.designpatterns.factory.abstractfactory.pizza.Pizza;

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
