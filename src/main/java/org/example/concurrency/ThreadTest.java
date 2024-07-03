package org.example.concurrency;

public class ThreadTest extends Thread {


    public void run(){
        for (int i =0 ; i< 5; i ++) {
        System.out.println("Inside thread " + currentThread().getName());
        }
    }

}
