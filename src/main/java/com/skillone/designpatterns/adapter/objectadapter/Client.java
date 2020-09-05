package com.skillone.designpatterns.adapter.objectadapter;

public class Client {

    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter(new Voltage220()));
    }

}
