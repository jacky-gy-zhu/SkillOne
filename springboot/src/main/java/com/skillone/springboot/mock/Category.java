package com.skillone.springboot.mock;

public class Category implements Cloneable {

    private long parentId;
    private String _id;
    private String name;
    private int __v;

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public Category withName(String name) {
        this.name = name;
        return this;
    }

    public Category clone() throws CloneNotSupportedException {
        return (Category)super.clone();
    }
}
