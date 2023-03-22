package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author hazel
 */
class MemoryMemberRepositoryTest {
    //tdd : 테스트를 먼저 개발하는 것

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //메소드가 실행이 끝날때마다 실행
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member res = repository.findById(member.getId()).get();

        //1. 둘이 같은지 확인하기 - > 결과가 녹색이면 성공
        //Assertions.assertEquals(res, res);

        //2. member가 res와 같은지 확인
        assertThat(member).isEqualTo(res);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member res1 = repository.findByName("spring1").get();
        assertThat(res1).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member3 = new Member();
        member3.setName("spring3");
        repository.save(member3);

        Member member4 = new Member();
        member4.setName("spring4");
        repository.save(member4);

        List<Member> res = repository.findAll();
        assertThat(res.size()).isEqualTo(2);
    }


}
