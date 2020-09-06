package com.skillone.designpattern.factory.factorymethod.order;

import com.skillone.designpattern.factory.factorymethod.pizza.Pizza;
import com.skillone.designpattern.factory.factorymethod.pizza.ZhCheesePizza;
import com.skillone.designpattern.factory.factorymethod.pizza.ZhPepperPizza;

public class ZhOrderPizza extends OrderPizza {

    public ZhOrderPizza(String type) {
        super(type);
    }

    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new ZhCheesePizza();
        } else if (type.equals("pepper")) {
            return new ZhPepperPizza();
        }else{
            return null;
        }
    }

}
