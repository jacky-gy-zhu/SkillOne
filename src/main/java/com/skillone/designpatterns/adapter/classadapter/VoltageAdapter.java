package com.skillone.designpatterns.adapter.classadapter;

public class VoltageAdapter extends Voltage220 implements Voltage5 {

    @Override
    public void output5v() {
        int voltage = getVoltage() / 44;
        System.out.println("output >> " + voltage);
    }

}
