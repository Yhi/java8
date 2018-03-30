package com.java8.list;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by cyh on 2016-04-17.
 */
public class ListSampl {
    static List<Person> people= Arrays.asList(
            new Person("Sara", 12),
            new Person("Mark", 43),
            new Person("Bob", 12),
            new Person("Jill", 64));

    public static void main(String[] args) {
        ListSampl.listSortJava7();
        System.out.println("------------");
        ListSampl.listSortJava8();
        System.out.println("------------");
       // printSorted(people,Comparator.comparing(Person::getName));
        System.out.println("------------");
       // printSorted(people,Comparator.comparing(Person::getAge));
        System.out.println("------------");
        //根据姓名和年龄排序
        printSorted(people,Comparator.comparing(Person::getName).thenComparing(Person::getAge));


    }

    public static void listSortJava7(){
        //比较器
        Comparator<Person> comparator=new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        System.out.println(people);

    }
    public static  void listSortJava8(){
        //比较器
        Comparator<Person> comparator1=(p1,p2)->
                p1.getName().compareTo(p2.getName());
        printSorted(people,comparator1);

    }
    //打印排序
    public static void printSorted(List<Person> people ,Comparator<Person> comparator){
      people.stream().sorted(comparator).
              forEach(System.out::println);

    }



}
