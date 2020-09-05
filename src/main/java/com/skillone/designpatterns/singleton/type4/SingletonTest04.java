package com.skillone.designpatterns.singleton.type4;

public class SingletonTest04 {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
        System.out.println("instance.hashCode()=" + instance.hashCode());
        System.out.println("instance2.hashCode()=" + instance2.hashCode());
    }

}

//懒汉式（线程安全，同步方法）
class Singleton {

    private static Singleton instance;

    private Singleton() {

    }

    //解决线程安全问题，但效率太低
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}