package com.example.jhyangnewthings.knowledge.designpattern.Singleton;

/**
 * @Author jhYang
 * @Descriprition 双重校验锁  参考博客：https://blog.csdn.net/java_1996/article/details/87472644
 * @Date 2020/10/14
 */
public class SingletonCheck {
    private static volatile SingletonCheck SingletonCheck;

    private SingletonCheck() {
    }

    public static SingletonCheck getInstance() {
        if (SingletonCheck == null) {
            synchronized (SingletonCheck.class) { //1
                if (SingletonCheck == null) { //2
                    SingletonCheck = new SingletonCheck(); //3
                }
            }
        }
        return SingletonCheck;
    }


    private void outPut(){
        System.out.println("双重校验锁...");
    }


    public static void main(String[] args) {
        SingletonCheck instance = SingletonCheck.getInstance();
        instance.outPut();
    }
}
