package com.skillone.designpattern.flyweight;

public class ConcreteWebsite extends Website {

    private String type;//网站发布的形式（类型）

    public ConcreteWebsite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println("网站的发布形式为：" + type + " ; 用户为：" + user.getName());
    }

}
