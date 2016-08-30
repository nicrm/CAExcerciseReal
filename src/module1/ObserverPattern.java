/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modul1;

import java.util.Observable;
import java.util.Observer;

/**
 * Example Observer Pattern
 * @author jens
 */
public class ObserverPattern {
   static class MyObservable extends Observable {
       public void createUser() {
           Object user = new Object();
           setChanged();
           notifyObservers(null);
       }
   }
    
    public static void main(String[] args) {
        MyObservable myObservable = new MyObservable();
        myObservable.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println(arg);
            }
        });
        myObservable.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println("Observer1 " + arg);
            }
        });
        myObservable.createUser();
    } 
    
}
