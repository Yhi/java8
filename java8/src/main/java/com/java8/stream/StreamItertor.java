package com.java8.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Stream;

/**
 * Created by cyh on 2016-05-01.
 * Jdk8 新增Spliterator
 */
public class StreamItertor {
    public static void main(String[] args) {
        ArrayList<Object> myList=new ArrayList<>();
        myList.add("apple");
        myList.add(5);
        myList.add("red");
        myList.add(4.0);
        myList.add(1L);

        Stream<Object> myStream=myList.stream();
        //获取一个迭代器
        Iterator<Object> itr=myStream.iterator();
        //从流里迭代出所有元素
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

        //使用Spliterator JDK8新增
        //获取一个Spliterator
        //如果有下一元素，tryAdvance方法会返回一个true，否则返回一个false。
        //tryAdvance方法将Iterator提供的hasNext和next方法的作用合并到一个方法中，所以提高了迭代过程的效率。
        Spliterator<Object> splitItr=myList.stream().spliterator();
        while (splitItr.tryAdvance(i-> System.out.println(i)));

    }

}
