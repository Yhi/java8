package com.java8.stream;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by cyh on 2016-05-01.
 *
 */
public class StreamInteger {
    public static void main(String[] args) {
        ArrayList<Integer> myList=new ArrayList();
        myList.add(5);
        myList.add(17);
        myList.add(100);
        myList.add(19);
        myList.add(9);
        myList.add(7);

        //获得一个list数组流
        Stream<Integer> myStream=myList.stream();

        Optional<Integer> minVal=myStream.min(Integer::compare);

        if(minVal.isPresent())
            System.out.println("MiniMum value:"+minVal.get());
        myStream=myList.stream();

        Optional<Integer> maxVal=myStream.max(Integer::compare);
        if (maxVal.isPresent())
            System.out.println("Maximum value:"+maxVal.get());

        Stream<Integer> sortedStream=myList.stream().sorted();
        System.out.println("Sorted stream:");
        //forEach调用的lambda表达式提供了accept()方法实现。forEach是终端操作。
        //因此该方法执行后，流就被消费掉了。
        sortedStream.forEach(n-> System.out.println(n+""));
        System.out.println("------------");

        //filter()方法基于一个过滤流，它返回一个只包含满足条件的新流，如下面返回只包含奇数值
        Stream<Integer> oddValues=myList.stream().sorted().filter(i ->i%2==1 );
        System.out.println("Odd values:");
        oddValues.forEach(n-> System.out.println(n+""));
        System.out.println("------------");

        oddValues=myList.stream().filter(n->n%2==1).filter(n->n>5);
        System.out.println("Odd values greater than 5: ");
        oddValues.forEach(n-> System.out.println(n+""));
        System.out.println("------------");
    }
}
