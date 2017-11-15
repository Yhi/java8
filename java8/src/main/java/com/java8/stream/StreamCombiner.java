package com.java8.stream;

import java.util.ArrayList;

/**
 * Created by cyh on 2016-05-01.
 * Combiner方法和reduce方法
 */
public class StreamCombiner {
    public static void main(String[] args) {
        ArrayList<Double> myList=new ArrayList<>();
        myList.add(4.0);
        myList.add(9.0);
        myList.add(1.0);
        //累加器函数将两个元素的平方根相乘，但是合并器则将部分结果相乘。

        double productOfSqrRoots=myList.parallelStream().reduce(
                1.0,(a,b)->a*Math.sqrt(b),
                (a,b)->a*b
        );
        System.out.println("Product of square roots: "+productOfSqrRoots);

        //this won't work
        double productOfSqrRoots2=myList.parallelStream().reduce(1.0
        ,(a,b)->a*Math.sqrt(b));
        System.out.println(productOfSqrRoots2);
    }
}
