# 订单自动取消问题

如果不要求实时性的话，或者对实时性要求不高的话，可以使用定时任务去实现；

如果实时性要求比较高的话，这里可以提供两种方式：mq延迟队列、redis中key过期监听


## mq延迟队列：

等到收到延迟队列之后，去做对应的业务处理

注意点：
需要下载延迟队列的插件,
网址：https://www.rabbitmq.com/community-plugins.html

搜索：rabbitmq_delayed_message_exchange 下载对应的版本



## redis中key过期监听：

等到监听到key过期之后，去做对应的业务处理

注意点：
需要修改redis配置文件，开启  notify-keyspace-events "Ex"  

默认是 ：notify-keyspace-events ""



