

package bankrace;


public class Inserter implements Runnable{
        Account myAccount;
    
    Inserter(Account anAccount){
        myAccount=anAccount;
    }
    
    public void run(){
        //10 iterations
        //in each iteration insert 100
        for(int i=0;i<10;i++)
            myAccount.insert(100);
    }

    
}
