package com.skillone.designpattern.bridge;

public interface Brand {

    void open();

    void close();

    void call();

}

class Apple implements Brand {

    @Override
    public void open() {
        System.out.println("apple open...");
    }

    @Override
    public void close() {
        System.out.println("apple close...");
    }

    @Override
    public void call() {
        System.out.println("apple call...");
    }

}

class Huawei implements Brand {

    @Override
    public void open() {
        System.out.println("Huawei open...");
    }

    @Override
    public void close() {
        System.out.println("Huawei close...");
    }

    @Override
    public void call() {
        System.out.println("Huawei call...");
    }
}
