package com.cb.io.pipe;

import java.util.concurrent.*;

public class Submit {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("This is ThreadPoolExetor#submit(Runnable runnable) method.");
//            }
//        };
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return 1;
            }
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future future = executor.submit(callable);
        System.out.println(future.get());
//        executor.shutdownNow();
        executor.shutdown();
    }
}