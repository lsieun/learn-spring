package spittr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages={"spittr"}, //注意这里的包名不要写错，书中写错了，写成了spitter
        excludeFilters={
                @Filter(type= FilterType.ANNOTATION, value=EnableWebMvc.class)
        })
public class RootConfig {
}

