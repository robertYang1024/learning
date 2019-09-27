package learning.threadDemo.thread.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bo.yang
 */
public class TestThread  {
    private static Logger logger  =LoggerFactory.getLogger(TestThread.class);

    public static void main(String[] args) throws Exception {

        Thread thread = new Thread(() -> {
            while(true) {
                logger.info("1111");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        for (int i=0; i < 10; i++) {
            if (i == 0) {
                thread.start();
                logger.warn("线程开始了******************");
            }
            logger.info(i + "****************") ;
            Thread.sleep(1000);
        }

    }
}
