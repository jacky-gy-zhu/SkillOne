package com.skillone.mybatis;

import com.skillone.mybatis.bean.Employee;
import com.skillone.mybatis.dao.EmployeeMapper;
import com.skillone.mybatis.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 根据xml配置文件，创建一个sqlSessionFactory
 * 2. sql映射文件，配置了每一个sql，以及sql的封装规则等
 * 3. 将sql映射文件注册在全局配置文件中
 * 4. 写代码
 *      1）根据全局配置文件得到SqlSessionFactory
 *      2）使用sqlSession工厂，获得到sqlSession对象使用他来执行增删改查
 *          一个sqlSession就是代表和数据库的一次会话，用完关闭
 *      3）使用sql的唯一标识来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中
 */
public class Application {

    public static void main(String[] args) throws IOException {
//        basicOperation();
//        interfaceOperation();
//        interfaceOperationAnnotation();
//        crud();
//        multiSelect();
//        selectPojo();
        selectByMap();
    }

    private static void basicOperation() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //2. 获取SqlSession实例
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Employee employee = session.selectOne(
                    "com.skillone.mybatis.dao.EmployeeMapper.getEmpById", 1);
            System.out.println(employee);
        }
    }

    private static void interfaceOperation() throws IOException {
        //1. get sqlSessionFactory object
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //2. get sqlSession obj
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            //3. get object (actually is a object proxy)
            // 会为接口自动创建一个代理对象，代理对象会去执行增删改查
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            System.out.println(employeeMapper);

            Employee employee = employeeMapper.getEmpById(1);

            System.out.println(employee);
        }
    }

    private static void interfaceOperationAnnotation() throws IOException {
        //1. get sqlSessionFactory object
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //2. get sqlSession obj
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            //3. get object (actually is a object proxy)
            // 会为接口自动创建一个代理对象，代理对象会去执行增删改查
            EmployeeMapperAnnotation employeeMapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            System.out.println(employeeMapper);

            Employee employee = employeeMapper.getEmpById(1);

            System.out.println(employee);
        }
    }

    private static void crud() throws IOException {
        //1. get sqlSessionFactory object
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //2. get sqlSession obj
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            //3. get object (actually is a object proxy)
            // 会为接口自动创建一个代理对象，代理对象会去执行增删改查
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee emp = new Employee(null, "Jacky", "jacky@gmail.com", true);
            Integer id = employeeMapper.addEmp(emp);
            System.out.println("增加了一行：" + emp);

            emp.setEmail("Ying@gmail.com");
            id = employeeMapper.updateEmp(emp);
            System.out.println("修改了一行：" + emp);

            employeeMapper.deleteEmpById(emp.getId());
            System.out.println("删除了一行：" + id);

            sqlSession.commit();
        }
    }

    private static void multiSelect() throws IOException {
        //1. get sqlSessionFactory object
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //2. get sqlSession obj
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            //3. get object (actually is a object proxy)
            // 会为接口自动创建一个代理对象，代理对象会去执行增删改查
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee employee = employeeMapper.getEmpByIdAndLastName(1, "Zhu");

            Employee employee2 = employeeMapper.getEmpByIdAndLastName2(1, "Zhu");

            System.out.println(employee);
            System.out.println(employee2);
        }
    }

    private static void selectPojo() throws IOException {
        //1. get sqlSessionFactory object
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //2. get sqlSession obj
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            //3. get object (actually is a object proxy)
            // 会为接口自动创建一个代理对象，代理对象会去执行增删改查
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee employee = employeeMapper.getEmpByPojo(new Employee(1, "Zhu", null, null));

            System.out.println(employee);
        }
    }

    private static void selectByMap() throws IOException {
        //1. get sqlSessionFactory object
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //2. get sqlSession obj
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            //3. get object (actually is a object proxy)
            // 会为接口自动创建一个代理对象，代理对象会去执行增删改查
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("id", 1);
            paramMap.put("lastName", "Zhu");
            Employee employee = employeeMapper.getEmpByMap(paramMap);

            System.out.println(employee);
        }
    }

    private static SqlSessionFactory getSqlSessionFactory() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

}
