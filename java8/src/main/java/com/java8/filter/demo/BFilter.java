package com.java8.filter.demo;

import com.java8.base.ResponseResult;
import com.java8.filter.AbstractFilter;

import java.util.Map;

public class BFilter<T extends Map> extends AbstractFilter<T> {


    @Override
    protected ResponseResult doFilter(T parameter) {
        System.out.println("我正在处理B的事情");
        return ResponseResult.success(null, parameter);
    }
}
