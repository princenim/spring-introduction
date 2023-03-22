package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hazel
 */
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    //각 테스트를 실행하기전에 memoryrepository를 만들어서 넣어줌으로써 같은 momorepository 사용
    //개발자가 직접 주입한것 (@autowired로 스프링으로 주입 가능)
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //메소드가 끝날때마다 실행
    @AfterEach
    public void afterEach() {
        //돌때마다 메모리 클리어
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMemeber = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMemeber.getName());
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when -> 같은 이름 가입
        memberService.join(member1);
        // -> 뒤의 로직을 실행할때 , 앞의 예외가 터져야함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //에러 두개가 맞는지 확인
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. ");

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}