package com.java8.lambda;

/**
 * Created by cyh on 2016-05-01.
 * 泛型函数式接口
 */
interface  SomeFunc<T>{
    T func(T t);
}

public class LambdaInterface {
    public static void main(String[] args) {
        SomeFunc<String> reverse=(str)-> {
            String result = "";
            int i;
            for (i = str.length() - 1; i >= 0; i--)
                result += str.charAt(i);
                return result;
        };
        System.out.println("Lambda reversed is "+reverse.func("lambda"));
        System.out.println("Expression reversed is "+reverse.func("Expression"));

        SomeFunc<Integer> factorial=n->{
            int result=1;
            for (int i=1;i<=n;i++){
                result=i*result;

            }
            return  result;
        };
        System.out.println("The factoral of 3 is "+factorial.func(3));
        System.out.println("The factoral of 5 is "+factorial.func(5));

        /*
        Lambda reversed is adbmal
        Expression reversed is noisserpxE
        The factoral of 3 is 6
        The factoral of 5 is 120
        */

    }
}
