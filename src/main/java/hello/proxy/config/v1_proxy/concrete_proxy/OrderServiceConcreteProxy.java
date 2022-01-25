package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2 {
    private final OrderServiceV2 target;
    private final LogTrace logTrace;

    public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace logTrace) {
        // 상속 했기때문에 부모 생성자를 호출해줘야 한다.
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;

        try{
            status = logTrace.begin("OrderServiceV2.orderItem()");
            //target 호출
            target.orderItem(itemId);
            logTrace.end(status);

        } catch(Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
