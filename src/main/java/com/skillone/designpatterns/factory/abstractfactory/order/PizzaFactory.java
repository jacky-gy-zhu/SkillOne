package com.skillone.designpatterns.factory.abstractfactory.order;

import com.skillone.designpatterns.factory.abstractfactory.pizza.Pizza;

//抽象工厂
public abstract class PizzaFactory {

    public abstract Pizza createPizza(String type);

}
