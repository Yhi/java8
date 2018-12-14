package com.java8.thread;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author chenyihui
 * @Title: Concurrentexcutor
 * @Description: 多任务并发调度器
 * @date 2018/11/30 10:42
 */
@Component
public class ConcurrentExecutor {

    private ExecutorService pool = new ThreadPoolExecutor(3, 6,
            100, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());

    public <T> List<Future<T>> execute(Callable<T>... callList) {
        List<Future<T>> rstFutureList = new ArrayList<>();
        if(callList != null) {
            for(Callable<T> call : callList) {
                rstFutureList.add(pool.submit(call));
            }
        }
        return rstFutureList;
    }

    /*public <T> Future<T> execute(Callable<T> callable) {
        Future<T> rstFuture = null;
        if(callable != null) {
            rstFuture = pool.submit(callable);
        }
        return rstFuture;
    }*/

    public void executeWithoutReturn(Runnable runnable) {
        pool.submit(runnable);
    }
}


