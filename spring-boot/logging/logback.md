# Spring Boot 日志配置

URL: [Spring Boot 日志配置](https://blog.csdn.net/inke88/article/details/75007649)

## 默认日志 Logback

默认情况下，Spring Boot会用Logback来记录日志，并用`INFO`级别输出到控制台。

日志输出内容元素具体如下：

- 时间日期：精确到毫秒
- 日志级别：ERROR, WARN, INFO, DEBUG or TRACE
- 进程ID
- 分隔符：— 标识实际日志的开始
- 线程名：方括号括起来（可能会截断控制台输出）
- Logger名：通常使用源代码的类名
- 日志内容

## 添加日志依赖

在maven中添加`spring-boot-starter-logging`依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-logging</artifactId>
</dependency>
```

但是，实际开发中我们不需要直接添加该依赖。 因为你会发现`spring-boot-starter`其中包含了`spring-boot-starter-logging`，该依赖内容就是 Spring Boot 默认的日志框架 logback。工程中有用到了**Thymeleaf**，而**Thymeleaf**依赖包含了`spring-boot-starter`，最终我只要引入Thymeleaf即可。

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

## 控制台输出

日志级别从低到高分为：

```txt
TRACE < DEBUG < INFO < WARN < ERROR < FATAL。
```

如果设置为`WARN`，则低于 `WARN` 的信息都不会输出。

Spring Boot中默认配置`ERROR`、`WARN`和`INFO`级别的日志输出到控制台。

## 文件输出

默认情况下，Spring Boot将日志输出到**控制台**，不会写到日志文件。

如果在使用Spring Boot时，在`application.properties`或`application.yml`配置，这样只能配置简单的场景。但是，保存路径、日志格式等，复杂的场景（区分 info 和 error 的日志、每天产生一个日志文件等）满足不了，只能自定义配置。

## 自定义日志配置

根据不同的日志系统，你可以按如下规则组织配置文件名，就能被正确加载：

- **Logback**： logback-spring.xml, logback-spring.groovy, logback.xml, logback.groovy
- **Log4j**： log4j-spring.properties, log4j-spring.xml, log4j.properties, log4j.xml
- **Log4j2**： log4j2-spring.xml, log4j2.xml
- **JDK (Java Util Logging)**： logging.properties

Spring Boot官方推荐优先使用带有`-spring`的文件名作为你的日志配置（如使用`logback-spring.xml`，而不是`logback.xml`）。命名为`logback-spring.xml`的日志配置文件，spring boot可以为它添加一些spring boot特有的配置项（下面会提到）。

推荐使用默认的命名规则，并且放在`src/main/resources`下面即可。

## 配置文件详解

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds" debug="false">

    <contextName>logback-demo</contextName>

    <!--输出到控制台 ConsoleAppender-->
    <appender name="consoleLog1" class="ch.qos.logback.core.ConsoleAppender">
        <!--展示格式 layout-->
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%d -1 %msg%n</pattern>
        </layout>
    </appender>

    <!--输出到控制台 ConsoleAppender-->
    <appender name="consoleLog2" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d -2 %msg%n</pattern>
        </encoder>
    </appender>

    <!--指定最基础的日志输出级别-->
    <root level="INFO">
        <!--appender将会添加到这个loger-->
        <appender-ref ref="consoleLog1"/>
        <appender-ref ref="consoleLog2"/>
    </root>

</configuration>

```

### `<configuration>`

```xml
<configuration scan="true" scanPeriod="60 seconds" debug="false">
```

- `scan`:当此属性设置为true时，配置文件如果发生改变，将会被重新加载。默认值为true。
- `scanPeriod`:设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。
- `debug`:当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。

### `<root>`

```xml
<root level="debug">
  <appender-ref ref="console" />
  <appender-ref ref="file" />
</root>
```

root节点是必选节点，用来指定最基础的日志输出级别，只有一个`level`属性。

`level`:用来设置打印级别，大小写无关：`TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`, `ALL` 和 `OFF`，不能设置为INHERITED或者同义词NULL。

默认是DEBUG。

可以包含零个或多个元素，标识这个appender将会添加到这个loger。


### `<contextName>`

```xml
<contextName>logback</contextName>
```

设置上下文名称

每个logger都关联到logger上下文，默认上下文名称为“default”。但可以使用设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改,可以通过`%contextName`来打印日志上下文名称，一般来说我们不用这个属性，可有可无。

### `<property>` 设置变量

```xml
<property name="logback.logdir" value="/Users/inke/dev/log/tomcat"/>
<property name="logback.appname" value="app"/>
```

用来定义变量值的标签， 有两个属性，name和value；其中name的值是变量的名称，value的值时变量定义的值。通过定义的值会被插入到logger上下文中。定义变量后，可以使“`${}`”来使用变量。

### `<appender>`

```xml
    <!--输出到控制台 ConsoleAppender-->
    <appender name="consoleLog1" class="ch.qos.logback.core.ConsoleAppender">
        <!--展示格式 layout-->
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%d -1 %msg%n</pattern>
        </layout>
    </appender>

    <!--输出到控制台 ConsoleAppender-->
    <appender name="consoleLog2" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d -2 %msg%n</pattern>
        </encoder>
    </appender>
```

appender用来格式化日志输出节点，有俩个属性name和class，class用来指定哪种输出策略，常用就是控制台输出策略和文件输出策略。

可以看到`layout`和`encoder`，都可以将**事件**转换为**格式化后的日志记录**，但是**控制台**输出使用`layout`，**文件输出**使用`encoder`，具体原因可以看http://blog.csdn.net/cw_hello1/article/details/51969554

### 日志格式解析

```xml
<!--输出到控制台 ConsoleAppender-->
<appender name="consoleLog1" class="ch.qos.logback.core.ConsoleAppender">
    <!--展示格式 layout-->
    <layout class="ch.qos.logback.classic.PatternLayout">
        <pattern>
            <pattern>%d{HH:mm:ss.SSS} %contextName [%thread] %-5level %logger{36} - %msg%n</pattern>
        </pattern>
        <!--
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
             <level>ERROR</level>
        </filter>
         -->
    </layout>

</appender>
```

Output:

```txt
18:15:22.148 logback-demo [http-nio-9010-exec-1] INFO  c.e.demo.controller.UserContorller - 日志输出 info
18:15:22.148 logback-demo [http-nio-9010-exec-1] WARN  c.e.demo.controller.UserContorller - 日志输出 warn
18:15:22.148 logback-demo [http-nio-9010-exec-1] ERROR c.e.demo.controller.UserContorller - 日志输出 error
18:15:22.148 logback-demo [http-nio-9010-exec-1] INFO  c.e.demo.controller.UserContorller - name:inke , age:33
18:15:22.149 logback-demo [http-nio-9010-exec-1] INFO  c.e.demo.controller.UserContorller - name:inke , age:33
```

`<encoder>`表示对日志进行编码：

- `%d{HH: mm:ss.SSS}`——日志输出时间
- `%thread`——输出日志的进程名字，这在Web应用以及异步任务处理中很有用
- `%-5level`——日志级别，并且使用5个字符靠左对齐
- `%logger{36}`——日志输出者的名字
- `%msg`——日志消息
- `%n`——平台的换行符

`ThresholdFilter`为系统定义的拦截器，例如我们用ThresholdFilter来过滤掉ERROR级别以下的日志不输出到文件中。如果不用记得注释掉，不然你控制台会发现没日志~

### 输出到文件 RollingFileAppender

另一种常见的日志输出到文件，随着应用的运行时间越来越长，日志也会增长的越来越多，将他们输出到同一个文件并非一个好办法。`RollingFileAppender`用于切分文件日志：

```xml
<appender name="fileInfoLog" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!--如果只是想要 Info 级别的日志，只是过滤 info 还是会输出 Error 日志，因为 Error 的级别高，
    所以我们使用下面的策略，可以避免输出 Error 的日志-->
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
        <!--过滤 Error-->
        <level>ERROR</level>
        <!--匹配到就禁止-->
        <onMatch>DENY</onMatch>
        <!--没有匹配到就允许-->
        <onMismatch>ACCEPT</onMismatch>
    </filter>
    <!--日志名称，如果没有File 属性，那么只会使用FileNamePattern的文件路径规则
        如果同时有<File>和<FileNamePattern>，那么当天日志是<File>，明天会自动把今天
        的日志改名为今天的日期。即，<File> 的日志都是当天的。
    -->
    <File>${logback.logdir}/info.${logback.appname}.log</File>
    <!--滚动策略，按照时间滚动 TimeBasedRollingPolicy-->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!--文件路径,定义了日志的切分方式——把每一天的日志归档到一个文件中,以防止日志填满整个磁盘空间-->
        <FileNamePattern>${logback.logdir}/info.${logback.appname}.%d{yyyy-MM-dd}.log</FileNamePattern>
        <!--只保留最近90天的日志-->
        <maxHistory>90</maxHistory>
        <!--用来指定日志文件的上限大小，那么到了这个值，就会删除旧的日志-->
        <!--<totalSizeCap>1GB</totalSizeCap>-->
    </rollingPolicy>
    <!--日志输出编码格式化-->
    <encoder>
        <charset>UTF-8</charset>
        <pattern>%d [%thread] %-5level %logger{36} %line - %msg%n</pattern>
    </encoder>
</appender>


<appender name="fileErrorLog" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <!--如果只是想要 Error 级别的日志，那么需要过滤一下，默认是 info 级别的，ThresholdFilter-->
    <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
        <level>Error</level>
    </filter>
    <!--日志名称，如果没有File 属性，那么只会使用FileNamePattern的文件路径规则
        如果同时有<File>和<FileNamePattern>，那么当天日志是<File>，明天会自动把今天
        的日志改名为今天的日期。即，<File> 的日志都是当天的。
    -->
    <File>${logback.logdir}/error.${logback.appname}.log</File>
    <!--滚动策略，按照时间滚动 TimeBasedRollingPolicy-->
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!--文件路径,定义了日志的切分方式——把每一天的日志归档到一个文件中,以防止日志填满整个磁盘空间-->
        <FileNamePattern>${logback.logdir}/error.${logback.appname}.%d{yyyy-MM-dd}.log</FileNamePattern>
        <!--只保留最近90天的日志-->
        <maxHistory>90</maxHistory>
        <!--用来指定日志文件的上限大小，那么到了这个值，就会删除旧的日志-->
        <!--<totalSizeCap>1GB</totalSizeCap>-->
    </rollingPolicy>
    <!--日志输出编码格式化-->
    <encoder>
        <charset>UTF-8</charset>
        <pattern>%d [%thread] %-5level %logger{36} %line - %msg%n</pattern>
    </encoder>
</appender>
```

### `<logger>`

`<loger>`用来设置某一个包或者具体的某一个类的日志打印级别、以及指定`<appender>`。`<loger>`仅有一个name属性，一个可选的level和一个可选的addtivity属性。

- `name`:用来指定受此loger约束的某一个包或者具体的某一个类。
- `level`:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，还有一个特俗值INHERITED或者同义词NULL，代表强制执行上级的级别。如果未设置此属性，那么当前loger将会继承上级的级别。
- `addtivity`:是否向上级loger传递打印信息。默认是true。

loger在实际使用的时候有两种情况 
先来看一看代码中如何使用

```java
package com.dudu.controller;
@Controller
public class LearnController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response){
        //日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR < FATAL，如果设置为WARN，则低于WARN的信息都不会输出。
        logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
        Map<String,Object> map =new HashMap<String,Object>();
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        if(!userName.equals("") && password!=""){
            User user =new User(userName,password);
            request.getSession().setAttribute("user",user);
            map.put("result","1");
        }else{
            map.put("result","0");
        }
        return map;
    }
}
```

