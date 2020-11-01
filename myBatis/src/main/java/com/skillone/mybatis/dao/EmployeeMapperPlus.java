package com.skillone.mybatis.dao;

import com.skillone.mybatis.bean.Employee;

public interface EmployeeMapperPlus {

    Employee getEmpById(Integer id);

    Employee getEmpAndDept(Integer id);

    Employee getEmpByIdStep(Integer id);

}
