/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul1;

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
        MySync sync = new MySync();
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
        new Thread(() -> {
            synchronized (sync.obj) {
                sync.increment3();
            }
        }).start();
        new Thread(() -> {
            synchronized (sync.obj) {
                sync.increment3();
            }
        }).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {

        }
        System.out.println(sync.i);
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
