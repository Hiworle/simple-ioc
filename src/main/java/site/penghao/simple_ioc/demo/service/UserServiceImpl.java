package site.penghao.simple_ioc.demo.service;

import site.penghao.simple_ioc.annotation.AutoWired;
import site.penghao.simple_ioc.annotation.Bean;
import site.penghao.simple_ioc.demo.dao.UserDao;

/**
 * @author hope
 * @date 2023/3/28 - 20:02
 */
@Bean
public class UserServiceImpl implements UserService {
    @AutoWired
    private UserDao userDao;

    public UserServiceImpl() {
    }

    @Override
    public void run() {
        System.out.println("service running...");
        userDao.run();
    }
}
