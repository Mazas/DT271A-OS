//description of a table in the Dining Philosophers problem
package diningphilosophers;
import java.util.concurrent.Semaphore;
import java.security.SecureRandom;
/**
 *
 * @author jof
 */
public class Table {
    
    int nbrOfChopsticks;
    private Semaphore semaphore;
    private boolean chopstick[]; //true if chopstick[i] is available
    private SecureRandom rnd = new SecureRandom();

    
    public Table(int nbrOfSticks){
        semaphore=new Semaphore(1);
        nbrOfChopsticks=nbrOfSticks;
        chopstick=new boolean[nbrOfChopsticks];
        for(int i=0;i<nbrOfChopsticks;i++){
            chopstick[i]=true;
        }
    }
    
    public void getLeft(int n){
        try {
            // Take a nap while the neighbour is eating
            while (!chopstick[n]) {
                Thread.sleep(rnd.nextInt(100));
            }
            semaphore.acquire();
            //philosopher n picks up its left chopstick
            chopstick[n] = false;
        }catch (Exception e){
            e.printStackTrace();
        }
        semaphore.release();
    }
    
    public void getRight(int n){
        try {
            int i=0; //counter
            //philosopher n picks up its right chopstick
            int pos = n + 1;
            if (pos == nbrOfChopsticks)
                pos = 0;
            // take a nap while right neighbour is eating
            while (!chopstick[pos]){
                Thread.sleep(rnd.nextInt(50));
                i++; // count how many times you took a nap
                // If you took a nap for long enough, maybe its time to let the left chopstick go,
                // so your neighbour wouldn't starve to death while you wait
                if (i>rnd.nextInt(50)&&!chopstick[n]){
                    releaseLeft(n);
                    System.err.println("Left released by "+n);
                }
            }
            // got the right chopstick? cool, get your left one back if you gave it away
            if (chopstick[n]){
                getLeft(n);
                System.err.println("Left acquired back by "+n);
            }
            semaphore.acquire();
            chopstick[pos] = false;
        }catch (Exception e){
            e.printStackTrace();
        }
        semaphore.release();
    }
    
    public void releaseLeft(int n){
        //philosopher n puts down its left chopstick
        try {
            semaphore.acquire();
            chopstick[n]=true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();
    }
    
    public void releaseRight(int n){
        //philosopher n puts down its right chopstick
        int pos=n+1;
        if(pos==nbrOfChopsticks)
            pos=0;
        try{
            semaphore.acquire();
            chopstick[pos]=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        semaphore.release();
    }
}
