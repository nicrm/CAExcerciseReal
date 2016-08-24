/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul1;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;
import java.io.ByteArrayOutputStream;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jens
 */
public class Threads {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        new Thread(() -> System.out.println("hi")).start();
//
//        Runnable r = new MyRunnable();
//        new Thread(r).start();
//
//        Thread t = new MyThread();
        //t.start();
        //t.start(); // Exception!
        // Process
//        new Thread(() -> {
//            while (true) {
//                System.out.println("hi");
//            }
//        }).start();
//        new Thread(() -> {
//            while (true) {
//                System.out.println("hi2");
//            }
//        }).start();
//        while(true) {
//            new Thread(() -> System.out.println("Test")).start();
//        }]
        // Race condition
//        MyAdder myAdder = new MyAdder();
//        Runnable r = () -> {
//            for (int i = 0; i < 10; i++) {
//                int oldI = myAdder.i;
//                myAdder.setI(oldI + 1);
//            }
//        };
//        new Thread(r).start();
//        new Thread(r).start();
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException ex) {
//            
//        }
//        System.out.println(myAdder.i);
        // Atomic variables
//        AtomicInteger atomI = new AtomicInteger();
//        Runnable r = () -> {
//            for (int i = 0; i < 10; i++) {
//                atomI.getAndIncrement();
//            }
//        };
//        new Thread(r).start();
//        new Thread(r).start();
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException ex) {
//        }
//        System.out.println(atomI.get());
//        MySync sync = new MySync();
        // Note to self: Don't do this:
//        new Thread(new Runnable() { 
//            @Override public void run() {
//                sync.increment();
//            }
//        });
        // Note to self: Holy grail
//        new Thread(() -> sync.increment2()).start();
//        new Thread(() -> {
//            sync.increment2();
//        }).start();
//        new Thread(() -> {
//            synchronized (sync.obj) {
//                sync.increment3();
//            }
//        }).start();
//        new Thread(() -> {
//            synchronized (sync.obj) {
//                sync.increment3();
//            }
//        }).start();
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException ex) {
//
//        }
//        System.out.println(sync.i);
        // Joining
//        MyAdder adder = new MyAdder();
//        Thread t = new Thread(() -> {
//        while (true) {}
//        });
//        t.start();
//        try {
//            t.join();
//        } catch (InterruptedException ex) {
////             Nothing happen
//        }
//        System.out.println(adder.i);
        // Interrupt
//    Thread t = new Thread(() -> {
//    while(true) {
//        try {
//            Thread.sleep(100);
//            System.out.println("Running");
//        } catch (InterruptedException e) {
//            System.out.println("Interrupt!");
//            break;
//        }
//    }});
//    t.start();
//    t.interrupt();

        // Notify
//        Object o = new Object();
//        Thread t = new Thread(() -> {
//            synchronized (o) {
//                try {
//                    o.wait();
//                } catch (InterruptedException ex) {
//                }
//            }
//        });
//        t.start();
//        try {
//            synchronized (o) {
//                o.notify();
//            }
//            t.join();
//        } catch (InterruptedException ex) {
//        }
//    
//    Semaphore s = new Semaphore(2);
//        try {
//            s.acquire();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        new Thread(() -> {
//        try {
//            s.acquire();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }).start();
//        Lock lock = new ReentrantLock();
//        try {
//            lock.lock();
//            new Thread(() -> {
//                lock.lock();
//                System.out.println("Got lock!");
//            }).start();
//
//        } finally {
//            lock.unlock();
//        }
// Priority
//    Thread t = new Thread(() -> {});
//    t.setPriority(Thread.MIN_PRIORITY);
//    t.start();
//    System.out.println(t.getPriority());
        // Executor Services
//        ExecutorService pool = Executors.newCachedThreadPool();
//        pool.execute(() -> System.out.println("Hi"));
//        pool.shutdown();

    }

    static class MySync {

        int i = 0;
        Object obj = new Object();

        // Method synchronisation
        synchronized void increment() {
            i++;
        }

        // Block synchronisation
        void increment2() {
            synchronized (this) {
                i++;
            }
        }

        // Block synchronisation (object)
        void increment3() {
            synchronized (obj) {
                i++;
            }
        }

    }

    static class MyAdder {

        volatile int i = 0;

        public void setI(int i) {
            this.i = i;
        }
    }

    static class MyThread extends Thread {

        public void run() {
            while (true) {
                System.out.println("Never gonna give you up");
            }
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Arm nuclear warheads");

        }

    }

}
