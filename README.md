## 平台简介

>Good权限管理系统是作者学习springBoot时基于springBoot开发的一套轻量级的权限系统，其目的是形成一套属于自己的通用的开发框架
>以后来项目的时候就可以直接基于此平台进行开发，减少重复工作量.
>目前还有好多功能未实现，后续会陆续完成并全部开源！

- 访问地址: 
    > 链接: [http://localhost:8081/admin/index](http://localhost:8081/admin/index)
    >
    > 用户名: admin
    >
    > 密码: 123
    
- 演示地址
      - http://139.224.112.51:8082/admin/index
      - 账号:test  密码:test

- 待完成:
    > 1. 加密规则 加盐 (先不解决)
    > 2. 项目启动 初始化admin权限信息 (先不解决)
    > 3. 添加完菜单资源后，超级管理员自动分配权限(先不解决)
    > 4. 添加spring cache使用基于注解方式的缓存管理，同时可以切换任意第三方缓存

## 主要功能介绍

1. 权限: 使用Shiro进行权限控制，灵活的可控制到页面或按钮，满足绝大部分的权限需求.
2. 数据库: 使用Druid数据库连接池，可以监控数据库访问性能，SQL执行效率进行SQL优化，同时使用多环境配置方式可以动态切换到开发环境、测试环境、生产环境. 
3. 日志：使用Logback进行日志输出，使用多环境配置方式可以动态切换到开发环境、测试环境、生产环境,同时基于时间和文件大小分割日志文件并进行归档.
4. 任务调度: 使用Spring+Quartz，基于数据库的分布式定时任务，既可以通过Http的方式调用其它项目的RESTFull接口，也可以执行自己本地的调度任务，支持动态增加、删除、修改任务、暂停|恢复、立即执行一次、查看执行记录，无需重启.
5. 公共异常处理和数据校验

## 内置功能

1. 系统管理
     - 用户管理(已完成)
     - 角色管理(已完成)
     - 权限管理(已完成)
     - 组织机构(未完成)
     - 字典管理(未完成)
     - SQL监控(已完成)
     - 在线用户管理
2. 调度管理(完成)
3. 内容管理(未完成)
4. 日志管理(已完成)
    
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

* 核心框架：Spring Boot 1.5.4
* 视图框架：Spring MVC 4.3.9
* 安全框架：Apache Shiro 1.3.2
* 任务调度：Spring+Quartz 2.x 
* 持久层框架：Mybatis 3.4.4 + 通用Mapper 3.4.2
* 服务端验证：Hibernate validator 5.3.5
* API生成工具: Swagger2
* 单元测试：Junit 4.12
* 网络客户端：Spring RestTemplate(OKGO) + OKHttp
* Email客户端：Spring + JavaMail
* 模板引擎：Thymeleaf 3.0.0
* 数据库连接池：Alibaba Druid 1.1.0
* 缓存框架：Spring Cache + Ehcache 2.5.3
* 日志管理：SLF4J 1.7.22 + Logback 1.1.8
* 分页插件：PageHelper 5.0.3
* 工具类：Apache Commons、Jackson 2.2、Lombok 1.16.14、Hutool 2.16.0、feilong-core 1.10.5

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

## 功能展示
![输入图片说明](https://git.oschina.net/uploads/images/2017/0424/112506_6f6953e8_798427.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0424/112608_ad3834e5_798427.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0424/112626_13d68392_798427.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0424/112633_3a416d99_798427.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0424/112642_bf3f3e2a_798427.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0424/112651_b3408011_798427.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0509/092341_07ee27ba_798427.jpeg "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0509/092401_a151ffcf_798427.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0521/210630_7ef7a3a8_798427.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0521/210653_bfbd06e2_798427.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0521/210707_508cad83_798427.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0521/210720_3ac69a9d_798427.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0521/210733_c4f35d0e_798427.png "在这里输入图片标题")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0726/174534_da8e44f7_798427.jpeg "F))1%[4%K)(4AV26JRZ(KQF.jpg")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0801/010933_e3a2c8a1_798427.png "]6A~)`7ZYGFV_ZN4)MOEL1V.png")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0801/011035_7bfa014a_798427.png "9U2H[$G@DHZEG7P}(ZD6PLR.png")

## 特别说明

因为本人能力有限，如果系统中存在任何Bug或者缺陷，欢迎大家及时告知我，希望于大家共同学习！

## QQ交流群[641213116](http://shang.qq.com/wpa/qunwpa?idkey=b047cee4542e383ad5a476bbf021c898fdad18311939d4dc365fa6c8349901ee "Good通用后台交流(不定时发放福利)")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0516/091125_f9478acc_798427.png "在这里输入图片标题")
> **注:不定期发放福利干货!!**

## 特别鸣谢
- [Looly / hutool](http://git.oschina.net/loolly/hutool)   一个Java基础工具类，对文件、流、加密解密、转码、正则、线程、XML等JDK方法进行封装，组成各种Util工具类
- [feilong / feilong-core](https://github.com/venusdrogon/feilong-core)   让Java开发更简便的工具包，让你从大量重复的底层代码中脱身,提高工作效率
- [abel533 / Mapper](http://git.oschina.net/free/Mapper)   极其方便的使用Mybatis单表的增删改查
- [abel533 / Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper)   Mybatis分页插件
- [人人开源 / renren-security](http://git.oschina.net/babaio/renren-security)   renren-security是一个轻量级权限管理系统
- [H-ui / admin](http://store.h-ui.net/H-ui.admin_v3.0/index.html)   H-ui.admin是用H-ui前端框架开发的轻量级网站后台模版

## 更新日志

### 计划更新

1. 会话管理
    - 对在线用户session管理和基本信息查看;可以手动踢出.
2. 字典管理

### 2017年9月24日

1. 解决quartz 触发器是CronTrigger模式时 调用scheduler.rescheduleJob方法后会立即执行任务的问题
2. 解决修改定时任务cron表达式后执行日志没有更新的问题

### 2017年9月7日

1. 解决定时任务里无法通过注解自动注入spring容器中的bean的问题

### 2017年8月20日

1. 权限列表使用treetable展示,增加用户体验

### 2017年8月14日

1. 解决授权时无法只选择列表的BUG
    - zTree取消全部子节点时，父节点不会取消

### 2017年8月12日

1. 重新设计登录页设计，开启炫酷模式
2. 解决字符串自动去除前后空格的BUG

### 2017年8月11日

1.  校验上传文件是否合法
     - 校验文件后缀扩展名是否合法
     - 校验上传文件的头信息，防止非法用户通过更改文件后缀名绕过第一步校验

### 2017年8月9日

1. 解决退出登录时无法清除认证缓存的问题.
    - 由于认证成功存入缓存的是整个对象,shiro提供的退出方法无法清除,还有之前所有的操作都是针对该对象进行的,所以重写了退出过滤器，自己手动去清除认证缓存,这样改动是最小的.

### 2017年8月5日

1. 定义welcome-file-list页面
2. 部署外网演示地址
    - http://139.224.112.51:8082/admin/index 
    - 账号:test  密码:test
3. 配置Nginx访问图片(配置文件见群共享)
4. 修改当前登录密码后，退出登录的逻辑走的是shiro的提供的默认方法，无法清除当前账号登录的次数的相关信息，所以再次登录明明是执行过了退出，确还提醒已经在别的地方登录了
    > 解决办法
    > 1. 执行退出走自定义退出过滤器
    > 2. 后台对shiro的退出做二次封装(未实现)

### 2017年8月1日

1. 重写规划包的目录结构，按模块进行划分
2. 判断如果已经登录了，还访问login登录地址，做重定向到原来的地址

### 2017年7月31日

1. 引入feilong-core 1.10.5包
2. 并发登录人数控制，限制一个账号只能一处登录，踢出前者(自定义KickoutSessionControlFilter和LoginFilter)
    - Ajax请求,前端给出选择框，选择重新登录后执行退出并跳转到登录页面
    - 传统请求，被踢出后跳转到登录页面，弹窗提示
    - 添加登录过滤器，判断登录账号是否已经在其他地方登录，并进行踢出询问，并展示已经登录用户的信息包括IP和登录时间   
3. 自定义退出过滤器，实现清除缓存

### 2017年7月20日

1. 修复BUG
2. 重构角色授权功能和添加角色分离
3. 富文本编辑器更换为UEditor(待完成),去除h-ui中多余的依赖
4. 升级springBoot版本到1.5.4
5. 升级Mybatis到3.4.4,mapper-spring-boot-starter到1.3.0,pagehelper-spring-boot-starter到1.1.2
6. 升级druid-spring-boot-starter到1.1.2 注:V1.1.1不支持connectionPropreties配置，所以无法配置ConfigFilter进行密码加密访问.
7. 重新配置和实现文件上传

### 2017年7月18日

1. 解决修改角色授权时不能立即生效问题，目前只解决了当前用户角色授权时立即生效,对其他用户不能马上生效(之前遗留问题)
2. 去除浏览器地址栏中url中JSESSIONID参数

### 2017年7月14日

1. 解决通用mapper用resultType返回时 无法绑定到对象的问题
2. 排除对二进制文件的过滤,解决字体图标无法显示

### 2017年6月28日

1. 添加SpringBoot jar方式打包时,下载jar包内的文件和响应jar包内文本文件内容的例子

### 2017年6月27日

1. 添加druid-spring-boot-starter
2. 数据库密码加密
3. druid配置细化

### 2017年5月27日

1. 对异常进行细分
    - Service层抛出指定类型的RuntimeException异常
    - Controller层抛出带状态码RumtimeException异常
    - 对异常进行统一转化,并响应到客户端
2. 重构表单重复提交校验(包括:重复提交后的提示和数据校验未通过token的刷新),全部向外抛出异常,进行统一处理
3. 将后端数据校验结果进行客户端页面展示(待处理)

### 2017年5月26日

1. 添加统一异常处理,并进行了细分
2. 添加后端数据校验,支持分组、排序,前后端双层校验,保证数据安全性

### 2017年5月25日

1. 封装layer提示插件,ajax添加遮罩层,增强客户体验度

### 2017年5月24日

1. 后台对Ajax操作的,无权限、登陆超时、表单重复提交等情况响应头信息进行统一配置,前台提示的逻辑重新改造

### 2017年5月23日

1. 添加防止表单重复提交校验前端js+后端token的方式
    - 对Ajax请求,提交时由于验证机制未通过,比如:用户名已存在,返回页面修改后再次提交出现token无法验证通过做处理
    > 解决方案: 利用ajax的全局方法 提交时自动加上token值 判断如果有返回的X-Refresh-Token-Form就更新页面所有的tokenForm值
    - 由于session是存储在服务器内存中的,在集群环境下不能保证生成token的服务器和验证token的服务器就是同一台,所以需要一种机制可以解决多服务器节点的数据共享. 
    > 解决方案: 使用spring session实现session共享或者将token放入redis中
    - 对于多开窗口操作同一个功能 比如: 添加用户，进行限制
    > 采用最后打开的窗口有效的方式,并进行页面过期弹窗友好提示
    - 安全考虑,可以给formToken设置一个有效期
    > 比如后期放入redis,可以方便借助它的生命周期控制;或者在formToken中加入时间戳进行比对.
    - 前端Ajax请求前全局禁用页面submit按钮既可以一定程度防止表单的重复提交，又能大大减轻服务器端访问压力,响应后重新启用按钮.

### 2017年5月22日

1. 新增调度任务查看详情
2. 新增调度日志查看
3. 正在执行，执行完成(待实现)

###  2017年5月4日

1. 动态任务调度
2. 改造权限管理模块
3. 日志UI界面展示

### 2017年4月24日

1. spring boot yml文件多环境配置
2. thymeleaf 布局调整

