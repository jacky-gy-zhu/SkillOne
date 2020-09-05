package com.skillone.designpattern.factory.abstractfactory.order;

import com.skillone.designpattern.factory.abstractfactory.pizza.Pizza;
import com.skillone.designpattern.factory.abstractfactory.pizza.ZhCheesePizza;
import com.skillone.designpattern.factory.abstractfactory.pizza.ZhPepperPizza;

//抽象工厂
public class ZhPizzaFactory extends PizzaFactory {

    @Override
    public Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new ZhCheesePizza();
        } else if (type.equals("pepper")) {
            return new ZhPepperPizza();
        }else{
            return null;
        }
    }

}
