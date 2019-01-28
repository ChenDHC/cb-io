package com.cb.io.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.*;

/**
 * pipe 提供了线程之间通信的机制（同一个进程中的线程之间）
 * @author ChenOT
 * @date 2019-01-24
 * @see
 * @since
 */
public class PipeTest {
    private static final ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(1,1,1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10));
    private static final  ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(1,1,1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10));
    public static void main(String[] args) throws IOException {
        final PipedOutputStream pipedOutputStream = new PipedOutputStream();
        final PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
        threadPoolExecutor1.execute(() -> {
            try{
                pipedOutputStream.write("hello world, pipe!".getBytes());
            }catch (IOException e){
                e.printStackTrace();
            }
        });

        threadPoolExecutor2.execute(() -> {
            try{
                int data = pipedInputStream.read();
                if(data != -1){
                    System.out.println((char) data);
                    data = pipedInputStream.read();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        });
        threadPoolExecutor2.shutdown();
        while (true){
            if(threadPoolExecutor2.isTerminated()){
                System.out.println("thread2 end");
                threadPoolExecutor1.shutdown();
                break;
            }
            System.out.println("thread2 is shutdown");
        }
        pipedInputStream.close();
        pipedOutputStream.close();
    }
}
