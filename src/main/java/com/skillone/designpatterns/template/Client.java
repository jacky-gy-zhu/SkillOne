package com.skillone.designpatterns.template;

public class Client {

    public static void main(String[] args) {
        SoyaMilk soyaMilk = new RedBeanSoyaMilk();
        soyaMilk.make();
    }

}
