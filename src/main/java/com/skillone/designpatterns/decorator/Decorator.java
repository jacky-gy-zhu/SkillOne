package com.skillone.designpatterns.decorator;

public class Decorator extends Drink {

    private Drink obj;

    public Decorator(Drink obj) {
        this.obj = obj;
    }

    @Override
    public float cost() {
        return super.getPrice() + obj.cost();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " " + super.getPrice() + " & " + obj.cost();
    }
}

class Chocolate extends Decorator {

    public Chocolate(Drink obj) {
        super(obj);
        setDescription("Chocolate");
        setPrice(3);
    }

}

class Milk extends Decorator {

    public Milk(Drink obj) {
        super(obj);
        setDescription("Milk");
        setPrice(2);
    }

}