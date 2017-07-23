# 虚拟机说明文档
VirtualBox-5.1.22
虚拟机系统 centos7.3
账号 root
密码 123456
#### 包括软件
* jdk 1.8.0_111
* nginx 1.11.7
* mysql 5.7.17
* redis 3.2.8

##### jdk
* 路径 /usr/local/jdk1.8.0_111

##### nginx
* 路径 /usr/local/nginx
* 启动 nginx
* 重启 nginx -s reload

##### mysql
* 配置 /etc/my.conf
* 账号 root
* 密码 123456
* 端口 3306
* 启动 systemctl start mysqld
* 停止 systemctl stop mysqld

##### redis
* 路径 /usr/local/redis
* 配置 /etc/reis.conf
* 端口 6379
* 密码 123456
* 启动 systemctl start redis
* 停止 systemctl stop redis

##### tomcat
* 路径 /usr/local/tomcat
* 启动 systemctl start tomcat
* 停止 systemctl stop tomcat


