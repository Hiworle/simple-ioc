import org.junit.jupiter.api.Test;
import site.penghao.simple_ioc.demo.controller.UserController;
import site.penghao.simple_ioc.demo.dao.UserDao;
import site.penghao.simple_ioc.demo.dao.UserDaoImpl;
import site.penghao.simple_ioc.demo.service.UserService;
import site.penghao.simple_ioc.demo.service.UserServiceImpl;
import site.penghao.simple_ioc.ioc.ApplicationContext;
import site.penghao.simple_ioc.ioc.SimpleApplicationContext;

/**
 * @author hope
 * @date 2023/3/28 - 20:06
 */
public class DemoTest {
    @Test
    void demoTest() throws Exception{
        ApplicationContext context = new SimpleApplicationContext("site.penghao");
        UserController bean = (UserController) context.getBean(UserController.class);
        bean.run();
    }
}
