package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hazel
 */
@Controller
public class HomeController {
    //mapping 된 url 부터 확인.
    @GetMapping("/")
    public String home(){
        return "home";
    }



}
