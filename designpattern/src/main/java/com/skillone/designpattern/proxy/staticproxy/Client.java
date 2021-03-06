package com.skillone.designpattern.proxy.staticproxy;

public class Client {

    public static void main(String[] args) {
        ITeacherDao iTeacherDao = new TeacherDao();
        ITeacherDao iTeacherDaoProxy = new TeacherDaoProxy(iTeacherDao);
        iTeacherDaoProxy.teach();
    }

}
