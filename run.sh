#!/usr/bin/env bash
# 刷新环境变量
source /etc/profile

echo ">>> git pull "
git pull

# 拉取新代码并打包
mvn clean package -U -DskipTests=true

appName='easy-poi'
port='8088'

# 删除老文件，复制新文件
rm /data/run/$appName.jar -f
rm /data/logs/$appName.log -f
mv target/app.jar /data/run/$appName.jar

# 根据端口号查询对应的pid，并删除服务进程
pid=$(netstat -nlp | grep :$port | awk '{print $7}' | awk -F"/" '{ print $1 }');
echo $pid
if [  -n  "$pid"  ];  then
    kill  -9  $pid;
fi

# 启动项目
cd /data/run
nohup java -jar $appName.jar > ../logs/$appName.log &
