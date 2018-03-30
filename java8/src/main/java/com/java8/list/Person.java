package com.java8.list;


/**
 * Created by cyh on 2016-04-17.
 */
public  class Person { //implements Comparable<Person>

    private final String name;
    private final int age;


    public String getName(){return name;}
    public int getAge(){return age;}
    public  Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String toString() {
        return String.format("%s-%d",name,age);
    }

  /* public int compareTo(Person other){
       return ((Integer)(age)).compareTo(other.age);
   }*/


}
