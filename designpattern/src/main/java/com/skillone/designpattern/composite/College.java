package com.skillone.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

public class College extends OrganizationComponent {

    //list中存放的是department
    List<OrganizationComponent> organizationComponentList = new ArrayList<>();

    public College(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        //将来实际业务中，College的add和University中的add不一定完全一样
        organizationComponentList.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponentList.remove(organizationComponent);
    }

    @Override
    protected void print() {
        System.out.println("---------------"+getName()+"---------------");
        organizationComponentList.stream().forEach(OrganizationComponent::print);
    }

}
