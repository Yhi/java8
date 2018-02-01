package com.java8.filter;

import com.java8.base.ResponseResult;

public interface Filter<T> {


    ResponseResult doFilter(T context, FilterChain chain);


}
