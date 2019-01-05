# @RunWith

URL: 

- [Class SpringJUnit4ClassRunner](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/context/junit4/SpringJUnit4ClassRunner.html)

`SpringJUnit4ClassRunner` is a custom extension of JUnit's `BlockJUnit4ClassRunner` which provides functionality of the Spring TestContext Framework to standard JUnit tests by means of the TestContextManager and associated support classes and annotations.

To use this class, simply annotate a JUnit 4 based test class with `@RunWith(SpringJUnit4ClassRunner.class)` or `@RunWith(SpringRunner.class)`.

```java
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= AppStarter.class)// 指定spring-boot的启动类
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserMapperTest {

    @Autowired
    private UserMapper dao;

}
```

Or

```java
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserMapperTest {

    @Autowired
    private UserMapper dao;

    @BeforeClass
    public static void setUpBeforeClass() {
        // set up before class
    }

    @AfterClass
    public static void tearDownAfterClass() {
        // tear down after class
    }

    @Before
    public void setUp() {
        // do set up
    }

    @After
    public void teardown() {
        // do tear down
    }
}
```

