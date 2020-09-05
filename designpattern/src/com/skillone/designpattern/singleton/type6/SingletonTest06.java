package com.skillone.designpattern.singleton.type6;

public class SingletonTest06 {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
        System.out.println("instance.hashCode()=" + instance.hashCode());
        System.out.println("instance2.hashCode()=" + instance2.hashCode());
    }

}

//懒汉式（线程安全，同步方法）[推荐使用]
class Singleton {

    private static volatile Singleton instance;

    private Singleton() {

    }

    //双重检查代码，解决线程安全问题，同时解决懒加载问题，同时保证了效率
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}