package com.skillone.designpattern.proxy.dynamicproxy;

public class Client {

    public static void main(String[] args) {

        ITeacherDao target = new TeacherDao();

        ITeacherDao proxyInstance = (ITeacherDao)new ProxyFactory(target).getProxyInstance();

        proxyInstance.teach();

        boolean isPos = proxyInstance.positive(20);

        System.out.println(isPos);

    }

}
