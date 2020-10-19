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
    db.<collection>.find({name:"xxx",age:21})  [条件查询，与关系]
    db.<collection>.findOne({name:"xxx"})       [用来查询集合中符合条件的第一个文档]
    db.<collection>.find().count()          [返回记录数量]
