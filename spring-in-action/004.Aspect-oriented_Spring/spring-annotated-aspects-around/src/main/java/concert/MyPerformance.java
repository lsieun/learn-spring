package concert;

import org.springframework.stereotype.Component;

@Component
public class MyPerformance implements Performance{
    public void perform() {
        System.out.println("--------------------------");
        System.out.println("Performance is going on...");
        int i = 1 / 0; // throw an exception
        System.out.println("--------------------------");
    }
}

