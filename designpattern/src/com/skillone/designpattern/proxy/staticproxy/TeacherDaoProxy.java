package com.skillone.designpattern.proxy.staticproxy;

public class TeacherDaoProxy implements ITeacherDao {

    private ITeacherDao iTeacherDao;

    public TeacherDaoProxy(ITeacherDao iTeacherDao) {
        this.iTeacherDao = iTeacherDao;
    }

    @Override
    public void teach() {
        System.out.println("TeacherDaoProxy teach ready");
        iTeacherDao.teach();
        System.out.println("TeacherDaoProxy teach submit");
    }

}
