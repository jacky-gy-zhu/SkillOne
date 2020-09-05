package com.skillone.designpattern.factory.abstractfactory.order;

import com.skillone.designpattern.factory.abstractfactory.pizza.Pizza;

//抽象工厂
public abstract class PizzaFactory {

    public abstract Pizza createPizza(String type);

}
