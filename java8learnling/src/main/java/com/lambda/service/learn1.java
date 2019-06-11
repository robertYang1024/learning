package com.lambda.service;

import com.lambda.model.Dish;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class learn1 {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork",false,800,Dish.Type.MEAT),
                new Dish("beef",false,700,Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

//    List<Dish> menuList = menu.stream().filter(e -> e.getCalories()>400).collect(Collectors.toList());

        List<? extends Number> numbers = Arrays.asList(1,2,3,4,5);
        List numbers2 =  numbers.stream().collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
        System.out.println(numbers2);

        /**
         * map
         */
        System.out.println("------------map--------------");
        Map<String,Dish> map = menu.stream().collect(Collectors.toMap(Dish::getName,Function.identity()));
        System.out.println(map);
        //key重复会报错 Collectors.toMap的方法不支持key重复，也不支持value为空
        List<Dish> menu2 = Arrays.asList(
                new Dish("pork",false,800,Dish.Type.MEAT),
                new Dish("beef",false,700,Dish.Type.MEAT),
                new Dish("pork", true, 400, Dish.Type.MEAT));
//        Map<String,Dish> mapTestkey = menu2.stream().collect(Collectors.toMap(Dish::getName,Function.identity()));//会报错
        //解决办法 (不过还是会有value为空会报错的问题)
        Map<String,Dish> mapTesykey2 = menu2.stream().collect(Collectors.toMap(Dish::getName,Function.identity(),(oldValue,newValue) -> newValue));
        Map<String,Dish> mapTesykey3 = menu2.stream().collect(Collectors.toMap(Dish::getName,Function.identity(),(oldValue,newValue) -> oldValue));
        System.out.println(mapTesykey3);

        //value为null报错
        List<Dish> menu3 = Arrays.asList(
                new Dish("pork",false,800,Dish.Type.MEAT),
                new Dish("beef",null,700,Dish.Type.MEAT));
//        Map<String,Boolean> mapTestValue = menu3.stream().collect(Collectors.toMap(Dish::getName,Dish::getVegetarian));//会报错
        //解决value为null会报错的问题
        Map<String,Boolean> mapTestValue2 = menu3.stream().collect(HashMap::new,(m,v) -> m.put(v.getName(),v.getVegetarian()),HashMap::putAll);
        System.out.println(mapTestValue2);


        /**
         * groupingBy
         */
        System.out.println("-----------groupingBy---------");
        Map<Boolean,List<Dish>> map2 = menu.stream().collect(Collectors.groupingBy(Dish::getVegetarian));
        System.out.println(map2);
        Map<Dish.Type,List<Dish>> map3 = menu.stream().collect(Collectors.groupingBy(Dish::getType));


    }




}
