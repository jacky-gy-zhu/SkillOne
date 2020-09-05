package com.skillone.designpatterns.flyweight;

import java.util.Hashtable;
import java.util.Map;

public class WebsiteFactory {

    //集合，充当池的作用
    private Map<String, ConcreteWebsite> pool = new Hashtable<>();

    //根据网站的类型，返回一个网站，如果没有就创建一个网站，并放入到池中，并返回
    public Website getWebsiteType(String type) {
        if (!pool.containsKey(type)) {
            pool.put(type, new ConcreteWebsite(type));
        }
        return pool.get(type);
    }

    //获取网站分类的总数（池中有多少个网站类型）
    public int getWebsiteCount() {
        return pool.size();
    }

}
