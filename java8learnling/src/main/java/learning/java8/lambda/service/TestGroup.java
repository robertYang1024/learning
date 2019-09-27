package learning.java8.lambda.service;

import learning.java8.lambda.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author bo.yang
 */
public class TestGroup {
    public static void main(String[] args){
        List<Dish> menu = Arrays.asList(
                new Dish("pork",false,800,Dish.Type.MEAT),
                new Dish("beef",false,700,Dish.Type.MEAT));
        Map<Boolean, List<Dish>> groupByVegetarian = menu.stream().collect(Collectors.groupingBy(Dish::getVegetarian));
        /**
         * 不存在true的话，map里面key没有true,groupByVegetarian.get(true)为null
         */
        System.out.println("true:"+ groupByVegetarian.get(true));
        System.out.println("false:" + groupByVegetarian.get(false));
        System.out.println("groupByVegetarian:" + groupByVegetarian);
    }
}
