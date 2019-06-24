package com.lambda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bo.yang
 */
public class LearnForeach {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        /**
         * lambda表达式里面return 跟continue 差不多
         */
        list.forEach(p -> {
            if ("c".equals(p)) {
                return;
            }
            System.out.println(p);
        });

        /**
         * 空的list的stream.map() 会返回空的，不会返回null
         */
        List<String> list2 = new ArrayList<>();
        List<Integer> collect = list2.stream().map(p -> p.length()).collect(Collectors.toList());
        System.out.println(collect.contains(1));
    }
}
