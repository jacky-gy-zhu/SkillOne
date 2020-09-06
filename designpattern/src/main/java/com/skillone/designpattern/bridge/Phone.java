package com.skillone.designpattern.bridge;

public abstract class Phone {

    private Brand brand;

    public Phone(Brand brand) {
        this.brand = brand;
    }

    protected void open() {
        this.brand.open();
    }

    protected void close() {
        this.brand.close();
    }

    protected void call() {
        this.brand.call();
    }

}

class FoldedPhone extends Phone {

    public FoldedPhone(Brand brand) {
        super(brand);
    }

    @Override
    protected void open() {
        super.open();
        System.out.println("folded open...");
    }

    @Override
    protected void close() {
        super.close();
        System.out.println("folded close...");
    }

    @Override
    protected void call() {
        super.call();
        System.out.println("folded call...");
    }

}

class UprightPhone extends Phone {

    public UprightPhone(Brand brand) {
        super(brand);
    }

    @Override
    protected void open() {
        super.open();
        System.out.println("Upright open...");
    }

    @Override
    protected void close() {
        super.close();
        System.out.println("Upright close...");
    }

    @Override
    protected void call() {
        super.call();
        System.out.println("Upright call...");
    }

}
