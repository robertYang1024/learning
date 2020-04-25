package learning.test.just.test2;

/**
 * @author bo.yang
 */
public class TestToHex {
    public static void main(String[] args){
        // 1111 1111  --> 0xff
        // 100000 & 0xFF ---> 只取一个字节
         System.out.println(Integer.toHexString(1000 & 0xFF));
         System.out.println(String.format("%02x",1000));

         int m  = 2;
         if (m <3) {
             System.out.println("test");
         }else if (m < 10){
             System.out.println("test2");
         }
    }
}
