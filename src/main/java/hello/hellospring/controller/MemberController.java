package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author hazel
 */

@Controller
//이렇게 만들면 이 컨트롤러를 스프링 컨테이너가 관리
public class MemberController {


    //1. 컴포넌트 스캔과 자동 의존 관계 설정
    public final MemberService memberService;


    /*  생성자에 이 @Autowired 어노테이션이 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아 넣어준다.
    이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection)I라고 한다.  */

    /*즉, 멤버 컨트롤러가 생성이 될때 멤버 서비스에서 있는 객체를 스프링이 가져다가 넣어줌*/
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        //끝나면 홈화면으로 돌려보내기
        return "redirect:/";
    }



    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";



    }
}
