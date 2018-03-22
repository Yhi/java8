package com.java8.pit;

import java.util.ArrayList;
import java.util.List;

public class ListRemove {

    public static void main(String[] args) {
        int l1=21;
        int l2=21;
        int l3=21;
        int l4=21;
        int l5=21;
        int l6=21;
        int l7=21;
        int l8=21;


        List list = new ArrayList();

        list.add(l1);
        list.add(l2);
        list.add(l3);
        list.add(l4);
        list.add(l5);
        list.add(l6);
        list.add(l7);
        list.add(l8);

        System.out.println("list.size()=" + list.size());
        for(int i = list.size()-1; i >=0; i--){//  这种方法ok
            //for (int i = 0; i < list.size(); i++) {  //这是有问题的
            if ((int)list.get(i)==21) {
                list.remove(i);
            }
        }
        System.out.println("after remove:list.size()=" + list.size());
    }
}
