# 项目介绍
本项目为spring cloud项目，用户的授权在ganteway上实现的，litemall-vue为商城前端，litemall-admin为商城后端（两个都为vue项目）。后面创建自己的服务仿造admin_goods_service和wx_service来创建。
# 创建子模块步骤
1.右键social_commerce->new->module之后填写模块名称
2.之后引入必要依赖（wx_service的pom文件里面有标注）
3.在resources文件夹下面新建bootstrap.yml文件，并在configuration_center中创建该服务对应的配置文件
# 项目启动顺序
configuration_center->registration_center->微服务项目->gateway


# 待办事项
-[ ] 在自己的服务中添加swagger模块


# 使用帮助
在gateway中有通过feign来调用admin_goods_service服务的demo（在web包下面的service里面），有实现类似http中post和get的操作，后面client之间的调用可以参考GoodsService中的例子

我将配置文件放到了我的git仓库https://github.com/ChunyuePeng/spring-cloud-config-repo.git
你们用的时候先把这项目克隆下来，修改配置文件之后提交到这个项目。提交之前记得update以防冲掉别人的代码
