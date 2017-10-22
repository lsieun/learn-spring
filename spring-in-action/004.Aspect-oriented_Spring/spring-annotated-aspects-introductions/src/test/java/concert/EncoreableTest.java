package concert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EncoreableConfig.class)
public class EncoreableTest {

    @Autowired
    private Performance p;

    @Test
    public void testPerformanceEncore() {
        p.perform();
        Encoreable e = (Encoreable) p;
        e.performEncore();
    }
}
