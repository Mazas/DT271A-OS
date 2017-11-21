//Simulation of the dining philosopher problem
package diningphilosophers;

/**
 *
 * @author jof
 */
public class DiningPhilosophers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int size=5; //number of philosophers and chopsticks
        Table tab=new Table(size);
        for(int i=0;i<size;i++){
            Thread th=new Thread(new Philosopher(i,tab));
            th.start();
        }
    }
}
