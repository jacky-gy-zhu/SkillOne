# MongoDB

## run with docker
    docker run -d p 2717:27017 -v ~/docker/mongodb-docker:/data/db --name mongoDB mongo:latest
    docker exec -it mongoDB bash
    mongo // run mongo
    > show dbs
    > use test
    > db.user.insert({"name":"Jacky Zhu"})
    > db.user.find()
    > exit
    
    // run mongo out of mongo container but still in the docker
    mongo localhost:2717

## 一些配置
    mongod --dbpath `配置新/data/db数据库路径` --port `新端口号port` 【端口号最大不能超过65535 】
    http://localhost:2717 ： It looks like you are trying to access MongoDB over HTTP on the native driver port.【说明mongodb已经上线】

## 启动数据库服务器
    mongod

## 进入数据库客户端
    mongo

## 概念结构
    database -> collection -> document

## 基本操作
    show dbs / show databases    [显示有多少数据库]
    use test    [进入或创建并进入数据库]
        集合和数据库都不需要手动创建，当文档所在的集合或数据库不存在会自动创建
    db  [显示当前所在数据库]   
    show collections    [查看数据库中的集合] 

### CRUD的操作
    db.<collection>.insert(doc)     [插入文档]
    db.<collection>.find()          [查询所有文档]
    db.<collection>.insert([
        {name:"xxx",age:20},
        {name:"yyy",age:30},
        {name:"zzz",age:40},
    ])
    当我们向集合中插入文档时，如果没有给文档指定_id属性，则数据库会自动添加_id作为唯一标示。如果自己定义_id，必须保证唯一性。
    ObjectId()是内置函数
    db.<collection>.insertOne()     [传对象]
    db.<collection>.insertMany()    [传数组]
    db.<collection>.find({name:"xxx"})  [条件查询]
    db.<collection>.find({"hobby.movies":"xxx"})  [条件查询]    如果嵌套查询，必须属性名加上引号
    db.<collection>.find({name:"xxx",age:21})  [条件查询，与关系]
    db.<collection>.findOne({name:"xxx"})       [用来查询集合中符合条件的第一个文档]
    db.<collection>.find().count()          [返回记录数量]
    db.<collection>.update(查询条件, 修改的新对象)    [默认情况会使用新对象替换旧的对象，并默认只会改一个]
    $set 修改操作符
    $unset 删除操作符
    $push 向数组中添加新的元素(可重复)
    $addToSet 向数组中添加新的元素(不会重复)
    db.<collection>.updateMany(查询条件)
    db.<collection>.updateOne(查询条件)
    db.<collection>.remove(查询条件)    [根据条件删除]
    db.<collection>.remove(查询条件, true)    [根据条件删除，只删除一个]
    db.<collection>.remove({})    [删除集合中所有的文档，一个一个删除]
    db.<collection>.drop()      [删除集合]
    db.dropDatabase()           [删除数据库]
    db.<collection>.deleteOne(查询条件)
    db.<collection>.deleteMany(查询条件)
    db.<collection>.limit(10)   [设置上限]
    db.<collection>.skip(10).limit(10)   [显示11～20条数据] skip((页码-1)*每页显示的条数).limit(每页显示的条数)
    MongoDB会自动条街skip和limit的位置
    
    // insert 20000 docs
    // 低效
    for(var i = 1; i < 20000; i++) {
        db.numbers.insert({num:i});
    }
    // 高效
    var arr = []
    for(var i = 1; i < 20000; i++) {
        arr.push({num:i});
    }
    db.numbers.insert(arr);

