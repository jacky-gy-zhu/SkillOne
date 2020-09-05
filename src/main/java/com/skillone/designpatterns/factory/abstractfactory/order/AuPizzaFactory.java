package com.skillone.designpatterns.factory.abstractfactory.order;

import com.skillone.designpatterns.factory.abstractfactory.pizza.AuCheesePizza;
import com.skillone.designpatterns.factory.abstractfactory.pizza.AuPepperPizza;
import com.skillone.designpatterns.factory.abstractfactory.pizza.Pizza;

//抽象工厂
public class AuPizzaFactory extends PizzaFactory {

    @Override
    public Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new AuCheesePizza();
        } else if (type.equals("pepper")) {
            return new AuPepperPizza();
        }else{
            return null;
        }
    }

}
