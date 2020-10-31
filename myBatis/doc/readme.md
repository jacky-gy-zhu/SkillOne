# MyBatis

## sqlSession
    不是线程安全的
    sqlSession.getMapper(XXX.class) 返回的是XXX的代理对象

## 2个重要的配置文件
    mybatis的全局配置文件
    sql映射文件，保存了每一个sql语句映射信息

特殊select参数配置
    Employee getEmp(@Param("id") Integer id, String lastName);
    取值: id --> #{id / param1}   lastName --> #{param2}

    Employee getEmp(Integer id, Employee emp);
    取值: id --> #{param1}    lastName --> #{param2.lastName}
    
    Employee getEmp(Integer id, @Param("e") Employee emp);
    取值: id --> #{param1}    lastName --> #{param2.lastName / e.lastName}
    
    ## 特别注意：如果是Collection(List、Set)类型或者数组，也会特殊处理。也是把传入的list或者数组封装到map中。
        key：Collection(collection)，如果是List还是可以使用这个key(list)，数组(array)
    Employee getEmpById(List<Integer> ids);
    取值: 取出第一个id的值：#{list[0]}

暂未符号的区别
    #{}:是以预编译的形式，将参数设置到sql语句中；PrepareStatement；防止sql注入
    ${}:取出的值直接拼装在sql语句中；会有安全问题；
    大多数情况下，我们用参数的值都应该去使用#{}；
    但是在某些情况下，原生jdbc不支持暂未符的地方我们就可以使用${}进行取值
    比如分表、排序；按照年份分表拆分
        select * from ${year}_salary where xxx;
        select * from tbl_employee order by ${f_name} ${order}

   #{}:更丰富的用法：
        规定参数的一些规则：
        jdbcType