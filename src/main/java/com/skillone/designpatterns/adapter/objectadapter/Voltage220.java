package com.skillone.designpatterns.adapter.objectadapter;

public class Voltage220 {

    private int voltage = 220;

    public int getVoltage() {
        return voltage;
    }

    public void output220v() {
        System.out.println(voltage);
    }

}
