package learning.test.just.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeoutException;

/**
 * @author bo.yang
 */
@Slf4j
public class LogeTest {

    public static void main(String[] args){
        /**
         * SLF4J 1.6.0以后
         * error(String format, Object... arguments)　这个方法也会打印异常堆栈信息，
         * 只不过规定throwable对象必须为最后一个参数．
         * 如果不遵守这个规定，异常堆栈信息不会ｌｏｇ出来
         */
        Exception e = new TimeoutException("timeout");
        Exception e2 = new Exception(e);
        log.error("da打印堆栈。。。", e2);
        System.out.println("************分割线******************");
        log.error("不打印堆栈, msg:{},mm:{}",e2, "haha");
        System.out.println("************分割线******************");
        log.error("打印堆栈, msg:{},", "haha",  e2);
    }
}
