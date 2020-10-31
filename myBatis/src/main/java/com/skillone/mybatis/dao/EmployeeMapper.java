package com.skillone.mybatis.dao;

import com.skillone.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 增删改
 * 1. mybatis允许增删改直接定义以下类型返回值
 *  Integer、Long、Boolean    (Integer和Long返回影响多少行）
 * 2. 需要手动提交数据 sqlSessionFactory.openSession() ｜ 或 自动提交sqlSessionFactory.openSession(true)
 * 3. 在添加后返回一个主键值并设置到对象中<insert id="addEmp" useGeneratedKeys="true" keyProperty="id">
 */
public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    Integer addEmp(Employee employee);

    Integer updateEmp(Employee employee);

    Integer deleteEmpById(Integer id);

    Employee getEmpByIdAndLastName(Integer id, String lastName);

    Employee getEmpByIdAndLastName2(@Param("id") Integer id, @Param("lastName") String lastName);

    Employee getEmpByPojo(Employee employee);

    Employee getEmpByMap(Map<String, Object> paramMap);

}
