package com.java8.Time;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by cyh on 2016-04-17.
 */
public class TimeSlot {
    static Random random=new Random();


    public void schedule() {

    }
    public  boolean isAvailable(){
        return random.nextBoolean();
    }
}

class Sample{
    public static void main(String[] args) {
        Sample.firstAvailableTimeSlotJava7();
        System.out.println("-------");
        Sample.firstAvailableTimeSlotJava8();
    }

   public static void  firstAvailableTimeSlotJava7(){
   List<TimeSlot> timeSlotList= Arrays.asList(
           new TimeSlot(),new TimeSlot(),new TimeSlot(),new TimeSlot(),
           new TimeSlot(),new TimeSlot());
     TimeSlot firstAvailableTimeSlot=null;
     for(TimeSlot timeSlot : timeSlotList){
         if(timeSlot.isAvailable()){
             timeSlot.schedule();
             firstAvailableTimeSlot=timeSlot;
             break;
         }
     }
       System.out.println("TimeSolt is "+firstAvailableTimeSlot);
   }
     public static void firstAvailableTimeSlotJava8(){
         List<TimeSlot> timeSlotList= Arrays.asList(
                 new TimeSlot(),new TimeSlot(),new TimeSlot(),new TimeSlot(),
                 new TimeSlot(),new TimeSlot());
         TimeSlot firstValue=timeSlotList.stream()
                 .filter(TimeSlot::isAvailable)
                 .findFirst()
                 .orElse(new TimeSlot());
         System.out.println("TimeSlot is "+firstValue);
     }

   /* public static  void   productJava8(){
        List<?> numbers=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        int[] factor=new int[]{ 2 };
        Stream<Integer> strm=
                numbers.stream()
                        .map(e ->e * factor[0]);
        factor[0]=44;
        strm.forEach(System.out::println);
    }*/
}
