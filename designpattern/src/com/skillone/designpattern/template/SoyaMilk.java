package com.skillone.designpattern.template;

public abstract class SoyaMilk {

    //模版方法，make，模版方法可以做成final，不让子类去覆盖
    final void make() {
        select();
        if (customerWantCondiments()) {
            addCondiments();
        }
        soak();
    }

    void select() {
        System.out.println("step 1");
    }

    abstract void addCondiments();

    boolean customerWantCondiments() {
        return true;
    }

    void soak() {
        System.out.println("step 3");
    }

}
