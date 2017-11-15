package com.java8.annotationdemo;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * Created by cyh on 2016-05-15.
 */
@Retention(RetentionPolicy.RUNTIME)
 @interface MyannotationTwo {
    String str();
    int val();
}
@Retention(RetentionPolicy.RUNTIME)
 @interface  What{
    String description();
}

@What(description = "An annotation test class")
@MyannotationTwo(str = "Mete2",val=99)
class Mete2{

    @What(description = "An annotation test method")
    @MyannotationTwo(str = "Mete2",val=100)
    public static void myMeth()  {
        try {
            Mete2 ob=new Mete2();
            Annotation annos[]=ob.getClass().getAnnotations();
            System.out.println("All annotations for Mete2");
            for (Annotation anno : annos)
                System.out.println(anno);

                System.out.println();

                Method m=ob.getClass().getMethod("myMeth");
                annos=m.getAnnotations();

                System.out.println("All annotations fot myMeth:");
            for (Annotation anno:annos)
                System.out.println(anno);





        }catch (NoSuchMethodException exc){
            System.out.println("Method Not Found");
        }

    }

    public static void main(String[] args) {
        myMeth();
    }
}
