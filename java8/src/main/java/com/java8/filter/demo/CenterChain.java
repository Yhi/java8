package com.java8.filter.demo;

import com.java8.filter.FilterChain;

import java.util.Map;

public class CenterChain<T extends Map> extends FilterChain<T> {


    @Override
    protected void initFileters() {

        AFilter aFilter = new AFilter();
        BFilter bFilter = new BFilter();
        
        this.append(aFilter).append(bFilter);

    }
}
