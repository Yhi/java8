package com.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by cyh on 2016-04-17.
 * External vs. Internal Iterators
 * Specialized functions
 * Function composition
 * Infinite streams
 * Lazy evaluation
 */
public class StreamlingIterations {

    public static void main(String[] args) {
        StreamlingIterations.sqrtOfFirst100PrimesJava7();
        System.out.println("---------------");
        StreamlingIterations.sqrtOfFirst100PrimesJava8();
    }
    public static  boolean isPrimeJava7(int number){
        boolean divisble=false;
        for (int i=2;i<number;i++){
            if(number%i==0){
                divisble=true;
                break;
            }
        }
        return number>1&&!divisble;
    }
    public static  boolean isPrimeJava8(int number){
        return  number>1 && IntStream.range(2,number).noneMatch(i->number%i==0);
    }

    public static void sqrtOfFirst100PrimesJava7(){
        List<Double> sqrtOfFirst100Primes=new ArrayList<>();
        int index=1;
        while (sqrtOfFirst100Primes.size()<100){
            if(isPrimeJava7(index)){
                sqrtOfFirst100Primes.add(Math.sqrt(index));
            }
            index++;
        }
        System.out.println(
                String.format(
                "Computer %d values,first is %g,last is %g",sqrtOfFirst100Primes.size(),
                        sqrtOfFirst100Primes.get(0),sqrtOfFirst100Primes.get(sqrtOfFirst100Primes.size()-1)));
    }

    public static void sqrtOfFirst100PrimesJava8(){
        List<Double> sqrtOfFirst100Primes=
                Stream.iterate(1,e->e+1)
                        .filter(StreamlingIterations::isPrimeJava8)
                        .map(Math::sqrt)
                        .limit(100)
                        .collect(Collectors.toList());
        System.out.println(
                String.format(
                        "Computer %d values,first is %g,last is %g",sqrtOfFirst100Primes.size(),
                        sqrtOfFirst100Primes.get(0),sqrtOfFirst100Primes.get(sqrtOfFirst100Primes.size()-1)));
    }

}
