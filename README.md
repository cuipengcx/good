## 平台简介111

Good权限管理系统是作者学习springboot时基于springboot开发的一套后台系统，目前还有好多功能未实现，后续会陆续完成并全部开源！

访问地址: [http://localhost/admin/index](http://localhost/admin/index)
用户名: admin
密码: 123

下面是项目完成情况：
```
  1.加密规则 加盐 (先不解决)
  2.动态菜单  (解决)
  3.无权限提示页面错误  (解决)
  4.编辑角色时 角色授权树回显有BUG(解决)
  5.Shiro Ajax请求权限不满足和ajax操作时登录超时，拦截后解决方案(解决)
  6.admin不允许删除，禁用 (解决)
  7.修改角色授权时不能立即生效问题，目前只解决了当前用户角色授权时立即生效,对其他用户不能马上生效 (先不解决)
  8.后台用户修改密码 (解决)
  9.项目启动 初始化admin权限信息 (先不解决)
  10.权限管理中添加 ，删除菜单不能马上生效，需要重新登录的问题(已解决)
  11.添加完菜单资源后，超级管理员自动分配权限(先不解决)
  12.添加spring cache使用基于注解方式的缓存管理，同时可以切换任意第三方缓存
```

## 内置功能

1. 系统管理
     - 用户管理(已完成)
     - 角色管理(已完成)
     - 权限管理(已完成)
     - 组织机构(未完成)
     - 任务调度(未完成)
     - 字典管理(未完成)

2. 日志管理(未完成)
     - 操作日志
     - 登录日志

3. 系统监控(未完成)
    - 连接池监控
    
## 开发环境

* Intellij IDEA 2016.2
* jdk 1.8
* Maven 3.3.9
* Tomcat 8.5
* Mysql 5.7
* Navicat for Mysql
* Google浏览器

## 技术选型

1、后端

* 核心框架：Spring Boot 1.4.3
* 视图框架：Spring MVC 4.3.5
* 安全框架：Apache Shiro 1.3.2
* 任务调度：Spring Task 
* 持久层框架：Mybatis 3.4.2 + 通用Mapper 3.3.9
* 服务端验证：Hibernate validator 5.2.4
* 单元测试：Junit 4.12
* 网络客户端：OKHttp + OKGO
* Email客户端：Spring + JavaMail
* 模板引擎：Thymeleaf 3.0.0
* 数据库连接池：Alibaba Druid 1.0.18
* 缓存框架：Spring Cache + Ehcache 2.5.3
* 日志管理：SLF4J 1.7.22 + Logback 1.1.8
* 分页插件：PageHelper 5.0.0
* 工具类：Apache Commons、Jackson 2.2、Lombok 1.16.14、Hutool 2.16.0

1、前端

* UI框架：H-UI 3.0
* JS框架：jQuery 1.11.0
* 表单验证控件：Jquery-validation 1.14.0
* 树结构控件：zTree v3
* 分页控件：laypage 1.3
* 弹出层组件：layer 2.4
* 富文本编辑器：ueditor 1.4.3
* 上传文件控件：webuploader 0.1.5
* 日期选择控件：My97DatePicker 4.8
* 数据图表：echarts 3.4.0
* 表单美化插件：iCheck 0.7

## 特别说明

因为本人能力有限，如果系统中存在任何Bug或者缺陷，欢迎大家及时告知我，希望于大家共同学习！
QQ：228727120@qq.com
Email：nbdash@163.com

## 特别鸣谢

1.[Looly / hutool](http://git.oschina.net/loolly/hutool)   一个Java基础工具类，对文件、流、加密解密、转码、正则、线程、XML等JDK方法进行封装，组成各种Util工具类
2.[abel533 / Mapper](http://git.oschina.net/free/Mapper)   极其方便的使用Mybatis单表的增删改查
3.[abel533 / Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper)   Mybatis分页插件
3.[人人开源 / renren-security](http://git.oschina.net/babaio/renren-security)   renren-security是一个轻量级权限管理系统
4.[H-ui / admin](http://store.h-ui.net/H-ui.admin_v3.0/index.html)   H-ui.admin是用H-ui前端框架开发的轻量级网站后台模版