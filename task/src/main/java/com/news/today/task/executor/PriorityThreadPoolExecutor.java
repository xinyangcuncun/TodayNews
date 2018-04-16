package com.news.today.task.executor;

import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by anson on 2018/4/6.
 */
public final class PriorityThreadPoolExecutor extends ThreadPoolExecutor {

    private final Interceptor interceptor;

    public PriorityThreadPoolExecutor(int corePoolSize,
                                      int maximumPoolSize,
                                      long keepAliveTime,
                                      TimeUnit unit,
                                      BlockingQueue<Runnable> workQueue,
                                      int priority,
                                      @NonNull Interceptor interceptor) {

        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                new PriorityThreadFactory("priority-pool-", priority));
        this.interceptor = interceptor;
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return (RunnableFuture<T>) runnable;
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return (RunnableFuture<T>) callable;
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        interceptor.before(r);
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        interceptor.after(r, t);
        super.afterExecute(r, t);
    }

    /**
     * 拦截器
     */
    public interface Interceptor {

        void before(Runnable r);

        void after(Runnable r, Throwable t);
    }
}