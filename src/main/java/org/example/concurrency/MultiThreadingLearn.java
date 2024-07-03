package org.example.concurrency;

public class MultiThreadingLearn {
    public static void main(String[] args) throws InterruptedException {
//        ThreadTest threadTest = new ThreadTest();
//        threadTest.start();
        System.out.println("Main thread is Start");
        for(int i = 0 ; i < 5 ; i++){
            ThreadTest threadTest = new ThreadTest();
            threadTest.start();

        }
        System.out.println("Main thread is end");

//        long startTime = System.nanoTime();
//
//        ExecutorService service = Executors.newFixedThreadPool(3);
//        SynchronizedMethods summation = new SynchronizedMethods();
//
//        IntStream.range(0, 1000)
//                .forEach(count -> {
//                    service.submit(summation::calculate);
//                    System.out.println("==========>"+summation.getSum());
//                });
//        long endTime   = System.nanoTime();
//        long totalTime = endTime - startTime;
//        System.out.println("Total times: " +totalTime);
    }
}
