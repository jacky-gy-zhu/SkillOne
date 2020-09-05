package com.skillone.designpattern.decorator;

public abstract class Drink {

    private String description;
    private float price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public abstract float cost();

}

class Coffee extends Drink {

    @Override
    public float cost() {
        return super.getPrice();
    }

}

class Espresso extends Coffee {

    public Espresso() {
        setDescription("Espresso");
        setPrice(6);
    }

}

class LongBack extends Coffee {

    public LongBack() {
        setDescription("Long");
        setPrice(8);
    }

}
