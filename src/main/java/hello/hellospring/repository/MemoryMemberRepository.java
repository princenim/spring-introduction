package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author hazel
 */

//@Repository
public class MemoryMemberRepository implements MemberRepository {
    //implements 는 인터페이스를 구현

    //저장 - > db가 현재 없어서
    private static Map<Long, Member> store = new HashMap<>();

    //id 값
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //null이 리턴될 가능성이 있을 때 감싸줌.
        return Optional.ofNullable(store.get(id));
    }



    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
}

    @Override
    public List<Member> findAll() {
        //전체 멤버를 반환
        return new ArrayList<>(store.values());
    }


    public void clearStore(){
        store.clear();
    }
}
