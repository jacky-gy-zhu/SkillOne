# MyBatis

## sqlSession
    不是线程安全的
    sqlSession.getMapper(XXX.class) 返回的是XXX的代理对象

## 2个重要的配置文件
    mybatis的全局配置文件
    sql映射文件，保存了每一个sql语句映射信息