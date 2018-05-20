package com.good.base;

import lombok.Data;

/**
 * @packageName: com.jk
 * @description: JUC测试
 * @author: cuiP
 * @date: 2018/3/10 20:50
 * @version: V1.0.0
 */
public class JucTest {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();

        while (true) {
            synchronized (threadDemo) {
                if(threadDemo.isFlag()){
                    System.out.println("---------------------");
                    break;
                }
            }

        }
    }
}

@Data
class ThreadDemo implements Runnable{

    private boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag = " + flag);
    }
}
