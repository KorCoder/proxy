package hello.proxy.app.v1;

public class OrderRepositoryV1Impl implements OrderRepositoryV1{
    @Override
    public void save(String itemId) {
        // 저장로직
        if(itemId.equals("ex")){
            throw new IllegalArgumentException();
        }

        getSleep(1000);
    }

    private void getSleep(int millls) {
        try {
            Thread.sleep(millls);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
