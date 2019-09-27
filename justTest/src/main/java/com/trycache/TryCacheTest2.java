package com.trycache;

/**
 * @author bo.yang
 */
public class TryCacheTest2 {

    /**
     * while(true)里面try catch后，也会继续执行循环
     */

    public static void main(String[] args) throws Exception {
        int i = 0;
        while(true) {
            try {

                Thread.sleep(1000);
                if (i == 5) {
                    i++;
                    throw new Exception("test....");
                }
                System.out.println(i++);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(2000);
            }
        }

    }
}
