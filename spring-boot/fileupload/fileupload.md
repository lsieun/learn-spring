# File Upload

Config in `application.properties`

```txt
# max size of a file in request body
spring.http.multipart.maxFileSize=100MB
# max size of each request body
spring.http.multipart.maxRequestSize=100MB
```

Controller

```java
import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadFileController {

    @RequestMapping("/uploadFile")
    public void uploadFile(MultipartFile file, HttpServletResponse response) throws Exception{

        System.out.println(file.getOriginalFilename());
        file.transferTo(new File("D:\\"+file.getOriginalFilename()));

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h1>upload file successful</h1>");
    }

}
```