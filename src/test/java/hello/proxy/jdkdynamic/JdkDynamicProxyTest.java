package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    /**
     * 동적 proxy 테스트
     */
    @Test
    void dynamicA(){
        AInterface target = new AImpl();

        TimeInvocationHandler hanlder = new TimeInvocationHandler(target);

        // 동적 proxy 객체 자동 생성
        AInterface proxy = (AInterface)Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, hanlder);

        proxy.call();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }

    @Test
    void dynamicB(){
        BInterface target = new BImpl();

        TimeInvocationHandler hanlder = new TimeInvocationHandler(target);

        // 동적 proxy 객체 자동 생성 - proxy 객체를 매번 안만들어도 된다. java 가 만들어주니까
        BInterface proxy = (BInterface)Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, hanlder);

        proxy.call();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }
            
}
