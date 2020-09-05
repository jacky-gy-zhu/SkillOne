package com.skillone.designpatterns.composite;

public abstract class OrganizationComponent {

    private String name;
    private String desc;

    public OrganizationComponent(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    protected void add(OrganizationComponent organizationComponent) {
        //默认的实现
        throw new UnsupportedOperationException();
    }

    protected void remove(OrganizationComponent organizationComponent) {
        //默认的实现
        throw new UnsupportedOperationException();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    //方法print，做成抽象的，子类都需要实现
    protected abstract void print();

}
