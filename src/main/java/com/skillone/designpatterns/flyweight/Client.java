package com.skillone.designpatterns.flyweight;

public class Client {

    public static void main(String[] args) {
        //创建一个工厂
        WebsiteFactory websiteFactory = new WebsiteFactory();

        //客户要一个以新闻形式发布的网站
        Website website1 = websiteFactory.getWebsiteType("新闻");
        website1.use(new User("Tom"));

        Website website2 = websiteFactory.getWebsiteType("博客");

        website2.use(new User("Jacky"));

        Website website3 = websiteFactory.getWebsiteType("博客");

        website3.use(new User("Ying"));

        System.out.println("网站的分类共："+websiteFactory.getWebsiteCount());
    }

}
