package com.skillone.designpatterns.template;

public class RedBeanSoyaMilk extends SoyaMilk {

    @Override
    void addCondiments() {
        System.out.println("step 2");
    }

    @Override
    boolean customerWantCondiments() {
        return false;
    }
}
