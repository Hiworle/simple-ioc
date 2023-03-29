package site.penghao.simple_ioc.demo.dao;

import site.penghao.simple_ioc.annotation.Bean;

/**
 * @author hope
 * @date 2023/3/28 - 20:01
 */
@Bean
public class UserDaoImpl implements UserDao {
    @Override
    public void run() {
        System.out.println("dao running...");
    }
}
