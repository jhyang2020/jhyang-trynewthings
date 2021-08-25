package com.example.jhyangnewthings.utils;

import java.util.concurrent.Callable;

/**
 * @Author jhYang
 * @Descriprition
 * @Date 2020/9/17
 */
public class TaskThread implements Callable<String>{
    private String data;
    public TaskThread(String data){
        this.data = data;
    }


    @Override
    public String call() throws Exception {
        return "线程"+data+"执行结果";
    }
}
