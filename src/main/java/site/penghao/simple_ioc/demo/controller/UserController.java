package site.penghao.simple_ioc.demo.controller;

import site.penghao.simple_ioc.annotation.AutoWired;
import site.penghao.simple_ioc.annotation.Bean;
import site.penghao.simple_ioc.demo.service.UserService;

/**
 * @author hope
 * @date 2023/3/28 - 20:01
 */
@Bean
public class UserController {
    @AutoWired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserController() {
    }

    public void run() {
        System.out.println("controller running...");
        userService.run();
    }
}
