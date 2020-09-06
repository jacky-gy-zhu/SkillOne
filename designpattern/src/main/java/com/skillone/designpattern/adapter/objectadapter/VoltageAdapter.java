package com.skillone.designpattern.adapter.objectadapter;

public class VoltageAdapter implements Voltage5 {

    public VoltageAdapter(Voltage220 voltage220) {
        this.voltage220 = voltage220;
    }

    public void setVoltage220(Voltage220 voltage220) {
        this.voltage220 = voltage220;
    }

    private Voltage220 voltage220;

    @Override
    public void output5v() {
        int voltage = voltage220.getVoltage() / 44;
        System.out.println("output >> " + voltage);
    }

}
