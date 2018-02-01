package com.java8.filter;

import com.java8.base.ResponseResult;
import io.netty.util.concurrent.FastThreadLocal;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chenyihui
 * @version 1.0
 * @Description 定义过滤器抽象类
 * @date 2018/2/1
 */
public abstract class FilterChain<T> implements Filter<T> {

    protected List<Filter> filters = new ArrayList<>();

    protected static FastThreadLocal<Integer> fastThreadLocal = new FastThreadLocal();


    @PostConstruct
    protected abstract void initFileters();

    public FilterChain append(Filter filter) {
        filters.add(filter);
        return this;
    }


    @Override
    public ResponseResult doFilter(T context, FilterChain chain) {
        Integer index = fastThreadLocal.get();
        if (index ==null) {
            fastThreadLocal.set(0);
            index = 0;
        }

        if (index == filters.size()) {
            clean();
            return ResponseResult.success(null, context);
        }

        Filter filter = filters.get(index);

        fastThreadLocal.set(++index);


        return filter.doFilter(context, chain);
    }

    protected void clean() {
        fastThreadLocal.remove();
    }
}
