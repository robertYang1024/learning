package learning.test.just.test;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bo.yang
 */
public class TestListSubList {

    public static void main(String[] args){
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11);
//        System.out.println(list.subList(0,10));
        System.out.println(list);

        System.out.println(averageAssign(list, 10));
        
        System.out.println(5/4);
    }

    public static   <T> List<List<T>> averageAssign(List<T> source, int size){
        List<List<T>> result = new ArrayList<List<T>>();
        // 商
        int quotient = source.size()/size;
        // 余数
        int remainder = source.size()%size;

        int num;
        if (remainder == 0) {
            num = quotient;
        }else {
            num = quotient + 1;
        }
        for (int i = 0; i < num; i++){
            List<T> value = null;
            if ((i+1) * size > source.size()) {
                value = source.subList(i * size, source.size());
            }else {
                value = source.subList(i * size, (i+1) * size);
            }
            result.add(value);
        }
        return result;
    }
}
