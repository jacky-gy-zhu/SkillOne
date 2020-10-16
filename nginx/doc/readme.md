# nginx

## 安装
    1. 使用远程连接工具连接linux操作系统
    2. 进入官网：http://nginx.org 
        pcre-8.37.tar.gz    [https://sourceforge.net/projects/pcre/files/pcre/8.37/pcre-8.37.tar.gz/download]
        openssl-1.0.1t.tar.gz
        zlib-1.2.8.tar.gz
        nginx-1.18.0.tar.gz 
    3. 上传pcre包 -> 解压 -> 运行./configure -> make && make install     （查看版本号:pcre-config --version）           
    4. yum -y install make zlib zlib-devel gcc-c++ libtool openssl openssl-devel    
    5. 上传nginx包 -> 解压 -> 运行./configure -> make && make install 
    6. cd /usr/local    -> 查看是否有nginx文件夹    -> 里面的~/sbin文件夹里有启动脚本
    7. 启动：cd /usr/local/nginx/sbin -> ./nginx
    8. 查看配置 ~/nginx/conf    -> 看配置文件：vim nginx.conf
    9. 查看linux防火墙：firewall-cmd --list-all   ->  firewall-cmd --add-port=80/tcp --permanent  -> firewall-cmd --reload

## 常用命令
    使用nginx操作命令前提条件，必须进入nginx目录 /usr/local/nginx/sbin
    1. 查看nginx版本号
        ./nginx -v
    2. 启动nginx
        ./nginx
    3. 关闭 nginx
        ./nginx -s stop
    4. 重新加载nginx
        ./nginx -s reload

## 配置文件
    1. nginx配置文件位置
        /usr/local/nginx/conf/nginx.conf   
    2. nginx的配置文件有3部分组成
        1）全局块
            worker_processes -> 处理并发的数量    
        2）events块
            events -> 服务器与用户网络连接
                worker_connections -> 支持最大连接数    
        3）http块     
            http全局块
            server块

## 配置实例
    1. 反向代理          
        server_name  47.90.105.193;
        location / {
            root   html;
            proxy_pass  http://127.0.0.1:8080;
            index  index.html index.htm;
        }                 