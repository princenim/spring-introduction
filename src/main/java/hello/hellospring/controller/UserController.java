package hello.hellospring.controller;

import hello.hellospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author hazel
 */

@Controller
public class UserController {
    //2. 직접 스프링 빈을 등록하는 방법
    //config에서 직접 bean을 등록

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

}
