package com.java8.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Created by cyh on 2016-05-21.
 */
public class ListChooseMinOrMax {
    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<>();
        myList.add(6);
        myList.add(99);
        myList.add(1);
        myList.add(333);
        myList.add(11);

        System.out.println("min: " + minElement(myList));
        System.out.println("max: " + maxElement(myList));

    }

    //找出list中最小的值
    public static Integer minElement(List<Integer> list) {
        Optional<Integer> optMin = list.stream().min(Integer::compare);
        return optMin.get();
    }

    //找出list中最大的值
    public static Integer maxElement(List<Integer> list) {
        Optional<Integer> optMin = list.stream().max(Integer::compare);
        return optMin.get();
    }
}
