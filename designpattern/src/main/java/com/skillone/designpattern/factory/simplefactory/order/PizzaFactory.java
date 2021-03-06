package com.skillone.designpattern.factory.simplefactory.order;

import com.skillone.designpattern.factory.simplefactory.pizza.CheesePizza;
import com.skillone.designpattern.factory.simplefactory.pizza.GreekPizza;
import com.skillone.designpattern.factory.simplefactory.pizza.Pizza;

//静态工厂模式（简单工厂模式）
public class PizzaFactory {

    public static Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new CheesePizza();
        } else if (type.equals("greek")) {
            return new GreekPizza();
        }else{
            return null;
        }
    }

}
