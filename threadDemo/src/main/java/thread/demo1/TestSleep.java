package thread.demo1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bo.yang
 */
@Slf4j
public class TestSleep {

    public static void main(String[] args){
        System.out.println("主线程....");
         Thread thread = new Thread(() -> {
             for(int i = 0; i < 100; i++) {
                 try {
                     Thread.sleep(1000);
                     System.out.println(i);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         });
        thread.start();

    }
}
