package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class TimeTraceAop {
//    @Around("execution(* hello.hellospring..*(..))")
    /* 컴포넌트 스캔을 하지 않고 자바 설정(SpringConfig.java)을 통해 Bean 생성을 하도록 변경 시,
       SpringConfig를 제외하지 않으면 순환 참조 문제가 발생함 */
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
