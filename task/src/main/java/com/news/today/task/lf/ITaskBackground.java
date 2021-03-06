package com.news.today.task.lf;

/**
 * Created by anson on 2018/4/6.
 */
public interface ITaskBackground<Result> {

    /**
     * Callable.call的别名，表明该方法在后台线程中执行
     *
     * @return
     * @throws Exception
     */
    Result onBackground() throws Exception;
}
