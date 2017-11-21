
package diningphilosophers;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jof
 */
public class Philosopher implements Runnable{
   private int id;
   private Table myTable;
   
   public Philosopher(int pid,Table tab){
       id=pid;
       myTable=tab;
   }
   
   @Override
   public void run(){
       for(int i=0;i<100;i++){
           try {
            //think
            System.out.println("Philosopher " + id + " thinks. Iteration "+ i);
            Thread.sleep((int)(Math.random()*100));
            //pick up chopsticks
               /**
                * Swaped the order in which the call is made, and output is printed
                * It was extremely confusing because it would say that the chopstick was picked up,
                * while it was waiting to be picked up.
                * */
            myTable.getLeft(id);
               System.out.println("Philosopher " + id + " pick up left");
            Thread.sleep((int)(Math.random()*10));

            myTable.getRight(id);
               System.out.println("Philosopher " + id + " pick up right");
            //eat
            System.out.println("Philosopher " + id + " eats. Iteration "+ i);
            Thread.sleep((int)(Math.random()*100));
            //release chopsticks
            System.out.println("Philosopher " + id + " drop left");
            myTable.releaseLeft(id);
            Thread.sleep((int)(Math.random()*10));
            System.out.println("Philosopher " + id + " drop right");
            myTable.releaseRight(id);
        } catch (InterruptedException ex) {
            Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
        }           
       }
   }
}
