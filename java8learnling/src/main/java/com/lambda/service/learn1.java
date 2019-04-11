package com.lambda.service;

import com.lambda.model.Dish;

import java.util.Arrays;
import java.util.List;

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
        numbers.stream().forEach(System.out::println);
    }




}
