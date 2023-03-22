package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author hazel
 */

//@Service
@Transactional
//jpa를 쓸때 항상 service 계층에 transactional을 붙어야햠!
public class MemberService {
    //service는 비즈니스 로직 구현을 담당, 서비스는 비즈니스 로직적으로 이름을 지어야함.
    //테스트 자동으로 만들어줌
    private final MemberRepository memberRepository;

    //멤버서비스 입장에서 new하지 않고 외부에서 memberRepository를 넣어주는게 DI
    //@Autowired
    //생성자에 @Autowired가 있으면 멤버 서비스를 생성시점에 스프링 컨테이너에서 해다 스프링 빈을 찾아 주입한다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*회원가입*/
    public Long join(Member member) {

        Long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();

        }finally {
            Long finish = System.currentTimeMillis();
            Long timeMs = finish - start;
            System.out.println("join =" + timeMs + "ms");


        }

    }

    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 중복 회원 X
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다. ");
        });
    }


    /*전체 회원조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //id로 회원조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
