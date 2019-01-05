# 注解`@Mapper`、`@MapperScan`

URL: https://blog.csdn.net/nba_linshuhao/article/details/82783454 

## `@Mapper`注解

- 作用：在接口类上添加了`@Mapper`，在编译之后会生成相应的接口实现类
- 添加位置：接口类上面

```java
@Mapper
public interface UserDAO {
   //代码
}
```

如果想要每个接口都要变成实现类，那么需要在每个接口类上加上`@Mapper`注解，比较麻烦，解决这个问题用`@MapperScan`。

## @MapperScan注解

- 作用：指定要变成实现类的接口所在的包，然后包下面的所有接口在编译之后都会生成相应的实现类
- 添加位置：是在`SpringBoot`启动类上面添加

```java
@SpringBootApplication
@MapperScan("com.xxx.dao")
public class AppStarter {

    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
    }
}
```

添加`@MapperScan(“com.xxx.dao”)`注解以后，`com.xxx.dao`包下面的接口类，在编译之后都会生成相应的实现类

使用`@MapperScan`注解多个包

```java
@SpringBootApplication  
@MapperScan({"com.abc.dao","com.xyz.dao"})  
public class AppStarter {  
    public static void main(String[] args) {  
       SpringApplication.run(AppStarter.class, args);  
    }  
}
```

如果dao接口类没有在Spring Boot主程序可以扫描的包或者子包下面，可以使用如下方式进行配置：

```java
@SpringBootApplication  
@MapperScan({"com.abc.*.mapper","org.abc.*.mapper"})  
public class App {  
    public static void main(String[] args) {  
       SpringApplication.run(App.class, args);  
    }  
}  
```
