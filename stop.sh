
# 停止运行
port='8009'

# 根据端口号查询对应的pid，并删除服务进程
pid=$(netstat -nlp | grep :$port | awk '{print $7}' | awk -F"/" '{ print $1 }');
echo $pid
if [  -n  "$pid"  ];  then
    kill  -9  $pid;
fi
