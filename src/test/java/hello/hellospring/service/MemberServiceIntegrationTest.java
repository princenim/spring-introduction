package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author hazel
 */

@SpringBootTest
@Transactional
//테스트 케이스에 이 어노테이션이 있으면 테스트 시작 전에 트랜잭션을 시작하고, 완료후에 항상 롤백해 db에 데이터가 남지 않아 다음 테스트에 영향X
class MemberServiceIntegrationTest {

    //스프링 컨테이너와 DB까지 연결한 통합 테스트
    @Autowired  MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

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
}