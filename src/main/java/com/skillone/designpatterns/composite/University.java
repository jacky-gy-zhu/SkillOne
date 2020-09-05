package com.skillone.designpatterns.composite;

import java.util.ArrayList;
import java.util.List;

public class University extends OrganizationComponent {

    //list中存放的是college
    List<OrganizationComponent> organizationComponentList = new ArrayList<>();

    public University(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
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
