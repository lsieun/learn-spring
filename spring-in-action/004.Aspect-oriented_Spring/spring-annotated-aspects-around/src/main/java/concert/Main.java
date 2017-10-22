package concert;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConcertConfig.class);
        Performance p = ctx.getBean(Performance.class);
        p.perform();
    }
}
