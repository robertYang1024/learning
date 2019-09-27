package learning.threadDemo.thread.demo1;

/**
 * @author bo.yang
 */
public class Test {
    public static void main(String[] args){
        String name = "KafkaListenerInfo [topic=" + "test" + ", consumerGroup=" + "group-1" + "]";
         String hello = "hello123";
         System.out.println(name.lastIndexOf("lo"));
         System.out.println(name.substring(name.lastIndexOf("consumerGroup=")+14, name.length()-1));
    }
}
