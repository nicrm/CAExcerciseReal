/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jens
 */
public class Futures {
    
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        Callable<String> callable = () -> {
            System.out.println("Hej");
            return "hi";
        };
//        Future<String> future = pool.submit(callable);
//        System.out.println(future.get(10, TimeUnit.MICROSECONDS));
        pool.shutdownNow();
        
        ScheduledExecutorService scheduledPool = Executors.newSingleThreadScheduledExecutor();
        scheduledPool.scheduleAtFixedRate(() -> System.out.println("Hej"), 0, 1, TimeUnit.SECONDS);
    }
    
}
