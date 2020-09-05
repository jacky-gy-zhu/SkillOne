package com.skillone.designpattern.bridge;

public class Client {

    public static void main(String[] args) {
        Phone phone = new FoldedPhone(new Huawei());
        phone.open();
        phone.close();
        phone.call();
    }

}
