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
    2. location配置
        location [ = | ~ | ~* | ^~ ] uri {
            ...
        }    
        1） =：用于不含正则表达式的uri前，要求严格匹配
        2） ~：用于表示uri包含正则表达式，并且区分大小写                       
        3） ~*：用于表示uri包含正则表达式，并且不区分大小写    
        4） ^~：用于不含正则表达式的uri前，要求nginx服务器找到标识uri和请求字符串匹配度最高的location后，立即使用此location处理。
        注意：如果uri包含正则表达式，则必须要有~或者~*标识。         

## 负载均衡
    upstream myserver {
        server  47.90.105.193:8080;
        server  47.90.105.193:8081;
    }
    server {
        listen       80;
        server_name  47.90.105.193;
        location / {
            proxy_pass  http://myserver;
        }
    }
### 负载均衡方式
    1. 轮询：默认方式，一旦某个服务器断了，会自动访问另外一台服务器
    2. 权重：权重大的访问几率大
        server  47.90.105.193:8080  weight=10;
        server  47.90.105.193:8080  weight=15;
    3. ip_hash：可以保证某一个ip地址只会访问固定一台服务器，解决session问题
            upstream myserver {
                ip_hash;   
                server  47.90.105.193:8080;
                server  47.90.105.193:8081;
            }  
    4. fair方式
        按后端服务器的响应时间来分配请求，响应时间越短的优先分配
            upstream myserver {
                server  47.90.105.193:8080;
                server  47.90.105.193:8081;
                fair;    
            } 

## 动静分离
    server {
        listen       9000;
        server_name  47.90.105.193;

        location / {
            root    /home/web/learning/react/build/;
            index   index.html;
        }

        location /css/ {
            root    /home/web/learning/react/build/;
            autoindex   on;
        }

        location /js/ {
            root    /home/web/learning/react/build/;
            autoindex   on;
        }

        location /imgs/ {
            root    /home/web/learning/react/build/;
            autoindex   on;
        }

        location /plugin/ {
            root    /home/web/learning/react/build/;
            autoindex   on;
        }

        location /media/ {
            root    /home/web/learning/react/build/;
            autoindex   on;
        }
    }

## 高可用
    安装keepalived
        yum install keepalived -y
        rpm -q -a keepalived  
    目录
        /etc/keepalived
    配置文件
        /etc/keepalived/keepalived.conf
    [修改keepalived.conf,添加一个nginx_check.sh]     
    启动修改keepalived
        systemctl start keepalived.service
        ps -ef | grep keepalived  
        ip a          
    停止keepalived
        killall keepalived    