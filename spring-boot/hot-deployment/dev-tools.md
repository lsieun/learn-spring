# dev-tools

## 介绍

`spring-boot-devtools`是一个为开发者服务的一个模块，其中最重要的功能就是将修改后的代码自动部署到服务器上面去。

`spring-boot-devtools`的原理是，使用了两个ClassLoader，一个Classloader加载那些不会改变的类（第三方Jar包），另一个ClassLoader加载会更改的类，称为  restart ClassLoader。当有代码更改的时候，原先的restart ClassLoader被丢弃，重新创建一个新的restart ClassLoader，由于需要加载的类相比较少，所以实现了较快的重启速度，节省了手工启动的时间，也节省了加载第三方jar包的时间。

## 使用

### 添加Maven依赖

```xml
<!-- devtools依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <!-- 子工程继承当前工程的时候，此依赖不会向下传递 -->
    <optional>true</optional>
</dependency>
```

注意：`spring-boot-devtools`的`<version>`信息是从`spring-boot-starter-parent`继承来的。

### 配置

在`application.properties`文件中，添加如下内容：

```txt
# DevTools热部署生效
spring.devtools.restart.enabled=true
# 设置文件变化需要重启服务的路径
spring.devtools.restart.additional-paths=src/main/java
# 设置文件变化，不需要重启服务的路径，默认/META-INF/maven，/META-INF/resources，/resources，/static，/templates，/public
# 路径中的内容修改不会重启服务，但是会重新加载静态内容。
# spring.devtools.restart.exclude: WEB-INF/**
```

### 使用细节

在使用IDEA的时候，如果修改的代码，去刷新浏览器，你可能发现修改没有生效。这是因为修改后的代码，还没有被编译成`.class`文件。

必须是要编译源代码才能重启，然后测试点击`mvn compile`后自动重启生效。
