package com.java8.lambda;

/**
 * Created by cyh on 2016-05-01.
 * 作为参数传递lambda表达式
 */
interface StringFunc{
    String func(String n);
}

public class LambdaAsArguments {
    static  String stringOp(StringFunc sf,String s){
        return sf.func(s);
    }

    public static void main(String[] args) {
        String inStr="lambda add power to Java";
        String outStr;
        System.out.println("Here is input string: "+inStr);
        outStr=stringOp(str->str.toUpperCase(),inStr);
        System.out.println("The string in uppercase: "+outStr);
        //删除字符串中的空格
        outStr=stringOp(str->{
            String result="";
            int i;
            for (i=0;i<str.length();i++){
                if(str.charAt(i)!=' '){
                    result +=str.charAt(i);
                }
            }
            return result;
        },inStr);
        System.out.println("The string with spaces removed: "+outStr);

    StringFunc reverse=str->{
      String result="";
        int i;
        for (i=str.length()-1;i>=0;i--) {
            result +=str.charAt(i);
        }
            return result;
    };
        System.out.println("The string reversed: "+stringOp(reverse,inStr));
    }
}
       /*
        Here is input string: lambda add power to Java
        The string in uppercase: LAMBDA ADD POWER TO JAVA
        The string with spaces removed: lambdaaddpowertoJava
        The string reversed: avaJ ot rewop dda adbmal
        */