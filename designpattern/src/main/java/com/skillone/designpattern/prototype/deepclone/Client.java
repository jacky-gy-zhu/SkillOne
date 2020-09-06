package com.skillone.designpattern.prototype.deepclone;

import com.skillone.designpattern.prototype.Sheep;

public class Client {

    public static void main(String[] args) throws CloneNotSupportedException {
        Farmer farmer1 = new Farmer("Jacky", 37);
        Sheep sheep = new Sheep("Tom", 1);
        farmer1.setSheep(sheep);

        System.out.println(farmer1);

        Farmer farmer2 = (Farmer)farmer1.deepClone();

        System.out.println(farmer2);
    }

}
