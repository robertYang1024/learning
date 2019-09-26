package thread.demo1;

import java.util.concurrent.*;

/**
 * @author bo.yang
 */
public class TestSchedulePool {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(() -> {

            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    throw new RuntimeException("test");
                }
            System.out.println(i);
            }

        }, 1, 1, TimeUnit.SECONDS);

//        new ThreadPoolExecutor()
//        Executors.newFixedThreadPool()
//        FutureTask
    }

}
