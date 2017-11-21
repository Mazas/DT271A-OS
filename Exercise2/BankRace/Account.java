package bankrace;

import java.util.concurrent.Semaphore;

public class Account {
    int balance; //this is the resources that we need to protect
    private Semaphore semaphore;
    Account(int initBalance){
        semaphore = new Semaphore(1,true);
        balance=initBalance;
        System.out.println("An account with balance: " + balance+ " was created.");
    }
    
    public void insert(int amount) {
        try {
            semaphore.acquire();
            int oldBalance=balance;
            //first sleep
            //the purpose of the sleep is to simulate some work that 
            //might be done in a real application
            //with the sleep we also force context switching
            Thread.sleep((int)(Math.random()*1000));
            balance=oldBalance+amount;
            System.out.println("The balance is now " + balance);
            semaphore.release();
            //second sleep
            //the purpose of the sleep is to simulate some work that 
            //might be done in a real application
            //with the sleep we also force context switching
            //The reason to force context switching is to make the race condition visible
            //otherwise we might run the application several times without
            //noticing that we have a serious flaw.
            Thread.sleep((int)(Math.random()*1000));
        }
        catch (InterruptedException ex) {
            System.out.println("Thread interupted");
        }
        finally {
        }
    }
    
    public void withdraw(int amount) {
        try {
            semaphore.acquire();
            int oldBalance=balance;
            //first sleep
            //the purpose of the sleep is to simulate some work that 
            //might be done in a real application
            //with the sleep we also force context switching
            Thread.sleep((int)(Math.random()*1000));
            balance=oldBalance-amount;
            System.out.println("The balance is now " + balance);
            semaphore.release();
            //second sleep
            //the purpose of the sleep is to simulate some work that 
            //might be done in a real application
            //with the sleep we also force context switching
            //The reason to force context switching is to make the race condition visible
            //otherwise we might run the application several times without
            //noticing that we have a serious flaw.
            Thread.sleep((int)(Math.random()*1000));
        }
        catch (InterruptedException ex) {
            System.out.println("Thread interupted");
        }
        finally {
        }
}

}
