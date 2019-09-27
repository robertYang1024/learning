package learning.guavaDemo.retry;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author bo.yang
 */
@Slf4j
public class GuavaretryTest {

    static int num = 0;
    public static void main(String[] args) throws ExecutionException, RetryException {
         Retryer<Object> retryer = RetryerBuilder.<Object>newBuilder()
                // retryIf 重试条件
//                .retryIfException()
//                .retryIfRuntimeException()
//                .retryIfExceptionOfType(Exception.class)
//                .retryIfException(Predicates.equalTo(new Exception()))
                .retryIfResult(Predicates.equalTo(null))
                .retryIfResult(Predicates.equalTo(false))

                // 等待策略：每次请求间隔1s
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
//                .withWaitStrategy(WaitStrategies.incrementingWait(0, TimeUnit.SECONDS, 1, TimeUnit.SECONDS))
                // 停止策略 : 尝试请求6次
                .withStopStrategy(StopStrategies.stopAfterAttempt(6))

                // 时间限制 : 某次请求不得超过2s , 类似: TimeLimiter timeLimiter = new SimpleTimeLimiter();
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))
                .withRetryListener(new MyRetryListener())

                .build()
                 ;

         Callable callable = new Callable() {

             int i = 0;
             @Override
             public Object call() throws Exception {
                 System.out.println(num++);
                 throw  new Exception("test");
             }
         };

         Callable<Object> callable1 = () -> {
             Object o = null;
             try {
                System.out.println("callble " + num++);
                 throw  new RuntimeException("test");
             } catch (Exception e) {
                int t =  1/0;
                 return o;
             } finally {
                 return o;
             }

//             o = new Object();
//             return o;
         };

//         retryer.
         retryer.call(callable1);

    }


     static class MyRetryListener implements RetryListener {

         @Override
         public <V> void onRetry(Attempt<V> attempt) {
             log.info("retry " + (num-1) + "................");
         }
     }
//    public static Integer get() {
//        return i;
//    }
//
//    public static void add() {
//        GuavaretryTest.i = GuavaretryTest.i++;
//    }
}
