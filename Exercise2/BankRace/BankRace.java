
package bankrace;


public class BankRace {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create an account with 1000 in balance
        Account myAccount=new Account(1000);
        //create two threads
        //the first thread will insert 100 ten times
        //and the second will withdraw 100 ten times
        Thread thread1=new Thread(new Inserter(myAccount));
        Thread thread2=new Thread(new Withdrawer(myAccount));
        
        thread1.start();
        thread2.start();

    }
    
}
