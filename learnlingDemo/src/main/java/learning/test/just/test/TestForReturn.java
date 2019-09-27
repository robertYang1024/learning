package learning.test.just.test;

/**
 * @author bo.yang
 */
public class TestForReturn {

    public static void main(String[] args){
        for (int i = 0; i < 10; i++) {
            if (i ==5 ){
                return;
            }
            System.out.println(i);
        }
    }
}
