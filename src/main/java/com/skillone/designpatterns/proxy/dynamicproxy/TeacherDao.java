package com.skillone.designpatterns.proxy.dynamicproxy;

public class TeacherDao implements ITeacherDao {

    @Override
    public void teach() {
        System.out.println("TeacherDao teach");
    }

    @Override
    public boolean positive(int num) {
        return num > 0;
    }

}
