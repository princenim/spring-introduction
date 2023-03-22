package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hazel
 */
@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hi");
        //viewResolver가 리턴 이름이랑 같은  resource/templates를 찾아서 hello.html파일을 렌더링한다.
        return "hello";
    }


    //mvc + 템플린 엔진
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }


    //api
    @GetMapping("hello-spring")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    /*리턴이 객체라면 디폴트가 json으로 http로 반환(jsonConverter를 사용해서)*/
    public Hello helloApi(@RequestParam("name") String name){
        //객체 사용해 json으로 리턴
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    //api를 위한
    static class Hello{
        private String name;

        public String getName(){
            return name;
        }

        private void setName(String name){
            this.name= name;
        }

    }


}


