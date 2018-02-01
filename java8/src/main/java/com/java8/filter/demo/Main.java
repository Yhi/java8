package com.java8.filter.demo;

public class Main {


    public static void main(String[] args){

        CenterChain centerChain=new CenterChain();
        centerChain.initFileters();
        centerChain.doFilter(null,centerChain);
    }
}
