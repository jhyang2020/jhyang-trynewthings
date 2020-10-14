package com.example.jhyangnewthings.knowledge.designpattern.Singleton;

/**
 * @Author jhYang
 * @Descriprition  懒汉式
 * @Date 2020/10/14
 */
public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
       return instance;
    }

    private void outPut(){
        System.out.println("单例测试...");
    }


    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        instance.outPut();
    }
}
