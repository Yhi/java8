package com.java8.list;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by cyh on 2016-05-15.
 * List转Map集合
 */
public class ListToMap {


    public static void main(String[] args) {
        listToMapJava7(addlist());
        listToMapJava8(addlist());

    }

    public static List addlist() {
        List list = new ArrayList<>();
        list.add("a");
        list.add(2);
        list.add(3.0);
        list.add('q');
        return list;
    }

    //list转Map
    public static void listToMapJava7(List list) {
        Map<String, Object> map = new HashMap<>();
        for (Object obj : list) {
            map.put(obj.toString(), obj);
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("java7 " + "key : " + entry.getKey() + " value : " + entry.getValue());
        }

    }

    //list转Map
    public static void listToMapJava8(List list) {
        Map map = (Map) list.stream().collect(
                Collectors.toMap(Object::toString, Function.identity()));
        map.forEach((k, v) -> {
            System.out.println("java8 " + "key : " + k + " value : " + v);
        });
    }
}
