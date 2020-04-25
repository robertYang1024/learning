package thread.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args){
        ThreadPoolExecutor poolExecutor   =  new ThreadPoolExecutor(2,
                10, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        poolExecutor.submit()
    }
}
