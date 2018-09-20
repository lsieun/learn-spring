# Servlet

[TOC]

## 1、Project Creation

### 1.1、maven project

使用`mvn`命令生成project，命令如下：

```bash
mvn archetype:generate -DarchetypeCatalog=internal
```

其中，`groupId`、`artifactId`、`version`和`package`信息如下：

```txt
groupId: lsieun
artifactId: my-app
version: 1.0-SNAPSHOT
package: lsieun
```

### 1.2、maven project --> eclipse project

生成eclipse的配置文件`.classpath` 和 `.project`，命令如下：

```bash
cd my-app/
mvn eclipse:eclipse 
```

打开`Vim`，将project导入到eclipse的workspace中：

```vim
:MvnRepo
:ProjectImport .
```

### 1.3、pom.xml(spring boot)

修改`pom.xml`文件，添加spring boot的配置信息。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>lsieun</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>my-app</name>
    <description>my-app project for Spring Boot</description>
    <url>http://maven.apache.org</url>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.16.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
	</parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>    
</project>
```

配置`pom.xml`完成后，做三件事情：解析maven信赖、重新生成eclipse配置信息 和 刷新eclipse项目。

```vim
:Mvn dependency:resolve
:Mvn eclipse:eclipse
:ProjectRefresh
```

## 2、Coding

### 2.1、App.java

Path: `lsieun/App.java`

```java
package lsieun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "App Start" );
        SpringApplication.run(App.class, args);
        System.out.println( "App Start Complete!" );
    }
}

```

### 2.2、HelloServlet.java

Path: `lsieun/servlet/HelloServlet.java`

```vim
:JavaNew class lsieun.servlet.HelloServlet
:JavaImpl
```

```java
package lsieun.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="hello", urlPatterns={"/hello"}, initParams={@WebInitParam(name="msg", value="I love three things in this world.")}, loadOnStartup=1)
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service: Start");

        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("<h1>Hello World - 你好</h1>");

        System.out.println("HelloServlet.service: Stop");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        String value = config.getInitParameter("msg");
        System.out.println("HelloServlet.init: " + value);
    }

    @Override
    public void destroy() {
        System.out.println("HelloServlet.destroy: 浮世万千，挚爱有三");
    }
}

```

## 3、Run

使用mvn命令生成jar包，命令如下：

```bash
mvn clean package -Dmaven.test.skip=true
```

运行生成的jar文件，命令如下：

```bash
java -jar target/my-app-1.0-SNAPSHOT.jar
```

在浏览器中查看，URL如下：

```url
http://localhost:8080/hello
```





