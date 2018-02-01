package com.java8.filter;

import com.java8.base.ResponseResult;

public abstract class AbstractFilter<T> implements Filter<T> {

    @Override
    public ResponseResult doFilter(T context, FilterChain chain) {

        ResponseResult curResponseResult;

        //try catch 解决过滤器里抛异常之后 chain没有clean 导致下次过滤器index不正确的问题
        try {
            curResponseResult = doFilter(context);
            if (curResponseResult.isSucceed()) {
                return chain.doFilter(context, chain);
            }
        } catch (Exception e) {
            chain.clean();
            throw e;
        }

        //过滤器截止
        chain.clean();
        return curResponseResult;
    }

    protected abstract ResponseResult doFilter(T parameter);
}