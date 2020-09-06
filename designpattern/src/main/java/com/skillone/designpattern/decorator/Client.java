package com.skillone.designpattern.decorator;

public class Client {

    public static void main(String[] args) {
        Drink drink = new Espresso();
        System.out.println(drink.getDescription() + " = " + drink.cost());
        drink = new Milk(drink);
        System.out.println(drink.getDescription() + " = " + drink.cost());
        drink = new Chocolate(drink);
        System.out.println(drink.getDescription() + " = " + drink.cost());
    }

}
