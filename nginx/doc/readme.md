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