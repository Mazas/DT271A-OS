
package bankrace;


public class Withdrawer implements Runnable{
    Account myAccount;
    
    Withdrawer(Account anAccount){
        myAccount=anAccount;
    }
    
    public void run(){
         //10 iterations
        //in each iteration withdraw 100
        for(int i=0;i<10;i++)
            myAccount.withdraw(100);
    }

}
