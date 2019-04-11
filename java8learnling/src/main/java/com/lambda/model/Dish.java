package com.lambda.model;

import lombok.Data;

@Data
public class Dish {

    private String name ;
    //素食
    private boolean vegetarian;
    //卡路里
    private int calories;
    private Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public enum Type{ MEAT,FISH,OTHER}
}
