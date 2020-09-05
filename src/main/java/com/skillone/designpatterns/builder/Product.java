package com.skillone.designpatterns.builder;

public class Product {

    private String name;
    private int width;
    private int height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}

