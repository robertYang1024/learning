package learning.classload.spi;

import java.util.ServiceLoader;

/**
 * @author bo.yang
 */
public class Test1 {
    public static void main(String[] args){
        ServiceLoader.load(Demo.class);

    }

    class Demo {

    }
}
