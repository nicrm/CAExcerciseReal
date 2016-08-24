/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul1;

import java.util.Optional;

/**
 * THIS IS NOT THREAD SAFE!
 * See https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html
 * For a complete example
 * @author jens
 */
public class ProducerConsumer {

    private Optional<String> queue = Optional.empty();

    public synchronized void put(String element) throws InterruptedException {
        if (queue.isPresent()) {
            synchronized (queue) {
                queue.wait();
            }
        }
        synchronized (queue) {
            queue.notify();
        }
        queue = Optional.of(element);
    }

    public synchronized String take() throws InterruptedException {
        if (!queue.isPresent()) {
            synchronized (queue) {
                queue.wait();
            }
        }
        String newElement = queue.get();
        queue = Optional.empty();
        synchronized (queue) {
            queue.notify();
        }
        return newElement;
    }

}
