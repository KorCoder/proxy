package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0(){
        Hello target = new Hello();

        //공통 로직1 시작
        log.info(("start"));
        String result1 = target.callA(); // 호출 메서드가 다르다.
        log.info("result={}", result1);
        //공통 로직1 종료

        //공통 로직2 시작
        log.info(("start"));
        String result2 = target.callB(); // 호출 메소드가 다르다.
        log.info("result={}", result2);
        //공통 로직2 종료
    }

    @Test
    void reflection1() throws Exception {
        //클래스 정보 - 내부클래스는 $사용
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //callA 메서드 정보

        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target); // target에 있는 CallA 메서드 호출
        log.info("result1 = {}", result1);

        //callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target); // target에 있는 CallB 메서드 호출
        log.info("result2 = {}", result2);
    }

    @Test
    void reflection2() throws Exception {
        //클래스 정보 - 내부클래스는 $사용
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        //callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    /**
     * 정적인 target.callA(), target.callB() 코드를 리플렉션을 사용해서
     * Method 라는 메타정보로 추상화 했다. 이로 인해 공통 로직을 만들 수 있다.
     * 다만, 리플렉션은 런타임에 동작하기때문에 컴파일 시점에 오류를 놓칠 수 있다.
     *
     * @param method
     * @param target
     * @throws Exception
     */
    @Test
    void dynamicCall(Method method, Object target) throws Exception {
        log.info("dynamicCall start");
        Object result1 = method.invoke(target); // target에 있는 method 호출
        log.info("result = {}", result1);

    }

    @Slf4j
    static class Hello{
        public String callA(){
            log.info("callA");
            return "A";
        }

        public String callB(){
            log.info("callB");
            return "B";
        }
    }
}
