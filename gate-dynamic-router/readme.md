## springCloud+SpringBoot+eureka+gateway+JWT实现：注册中心，网关，token，服务之间调用

##Gateway之 Spring Cloud Gateway 动态路由
###https://blog.csdn.net/zhuyu19911016520/article/details/86562615
##实现思路如下：
 #### 1.创建一个路由信息维护的项目（dynamic-route），实现增删改查路由信息到mysql
 #### 2.提供发布功能，发布后将路由信息与版本信息保存到redis中，对外提供 rest 接口获取路由信息
 #### 3.网关（gateway-dynamic-route）开启定时任务，定时拉取 rest 接口中发布的最新版的路由信息，对比版本号，如果网关的版本号与rest接口中的不一致，则获取路由信息后更新网关路由，这样网关发布多个实例后，都会单独的去拉取维护的路由信息
 #### ————————————————
 #### 版权声明：本文为CSDN博主「闪耀的瞬间」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 #### 原文链接：https://blog.csdn.net/zhuyu19911016520/article/details/86562615