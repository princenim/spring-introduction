package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import hello.hellospring.service.UserService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Time;

/**
 * @author hazel
 */


@Configuration
public class SpringConfig {


/*    이렇게 만들면 스프링에서 UserService와 userRepository 를 가져와 스프링 빈에 등록해줌.
    그리고 스프링 빈에 등록되어있는 userRepository를 userService에 넣어줌
    이렇게 service -> repository 완성*/

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new MemoryUserRepository();
    }


    ////////////////////////////////////////////
    //jdbc 연결하기

    //데이터베이스 커넥션을 획득할때 사용하는 객체
    //스프링 부트는 데이터베이스 커넥션 정보를 바탕으로 dataSource를 생성하고 스프링 빈으로 만들기때문에 DI를 받을 수 있다.
    //private final DataSource dataSource;


//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        //this.dataSource = dataSource;
//        //this.em = em;
//    }


    //jpa용
    //private final EntityManager em;


//    @Bean
//    public MemberRepository memberRepository() {
//        //1. 순수 jdbc
//        //return new JdbcMemberRepository(dataSource);
//
//        //2.jdbcTemplate
//        //return new JdbcMemberRepository(dataSource);
//
//        //3.jpa
//        return new JpaMemberRepository(em);
//    }


    //4. spring data jpa
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


//    //aop 직접 등록
//    @Bean
//    public TimeTraceAop TimeTraceAop(){
//        return new TimeTraceAop();
//    }

}
