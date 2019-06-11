package com.lambda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LearnEmpty {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        Set<Integer> set = list.stream().map(p -> p.length()).collect(Collectors.toSet());
        System.out.println(set.size());
        System.out.println(set.contains("a"));
    }
}
