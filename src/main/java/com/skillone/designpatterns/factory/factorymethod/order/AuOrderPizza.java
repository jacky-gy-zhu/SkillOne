package com.skillone.designpatterns.factory.factorymethod.order;

import com.skillone.designpatterns.factory.factorymethod.pizza.AuCheesePizza;
import com.skillone.designpatterns.factory.factorymethod.pizza.AuPepperPizza;
import com.skillone.designpatterns.factory.factorymethod.pizza.Pizza;

public class AuOrderPizza extends OrderPizza {

    public AuOrderPizza(String type) {
        super(type);
    }

    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new AuCheesePizza();
        } else if (type.equals("pepper")) {
            return new AuPepperPizza();
        }else{
            return null;
        }
    }

}
