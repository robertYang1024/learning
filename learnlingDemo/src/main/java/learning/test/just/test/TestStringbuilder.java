package learning.test.just.test;

/**
 * @author bo.yang
 */
public class TestStringbuilder {
    public static void main(String[] args){
        StringBuilder stringBuilder = new StringBuilder();

//        append("123", stringBuilder);
//        append(" hello", stringBuilder);
//        System.out.println(stringBuilder);
        byte[] by = "test".getBytes();
        for (int i = 0; i <by.length ; i++) {
            System.out.println("0" + Integer.toHexString(0xFF & by[i]));
            System.out.println("0" + String.format("%02x", by[i]));
        }
    }

    public static void append(String msg, StringBuilder ss) {
        ss.append(msg);
    }
}
