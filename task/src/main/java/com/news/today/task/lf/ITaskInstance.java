package com.news.today.task.lf;

/**
 * Created by anson on 2018/4/6.
 */
public interface ITaskInstance extends Runnable {
    int STATUS_NEW = 0;
    int STATUS_WAIT = 1;
    int STATUS_READY = 2;
    int STATUS_RUNNING = 3;
    int STATUS_OVER = 4;
    int STATUS_CANCEL = 5;

    int getStatus();

    void setStatus(int status);

    void retry();
}
