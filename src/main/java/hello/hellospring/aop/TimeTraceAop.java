package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author hazel
 */

@Aspect
@Component
//springconfig에 bean 직접 등록하기도 가능
public class TimeTraceAop {


    //전체에 적용
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        Long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try{
            //메소드 실행
            return joinPoint.proceed();
        }finally {
            Long finish  = System.currentTimeMillis();
            long timeMs= finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");


        }

    }
}
