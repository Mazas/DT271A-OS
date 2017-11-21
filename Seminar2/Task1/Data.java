package readerwriter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jof
 */

//The class Data contains a value that is shared between readers and writers.
//In write the value is simply updated
//In read the value is read twice with a delay in between.
//With proper synhronization the value in read should 
//not be modified until read has finished. 

public class Data {
    private int value;
    private RWLock myLock;

    // Error count
    private int errCount = 0;
    
    public Data(){
        value=100;
        myLock=new RWLock();
    }
    
    public void read(int id){
        myLock.acquireRead();
        int val=value;
        System.out.println("Reader:" + id + " got the value: " + val);
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (value!=val){
            errCount++;
        }
        System.out.println("Reader:" + id + " thinks that the value is:" + val +", but the value is: " + value);
        myLock.releaseRead();
    }
    
     public void write(int id,int val){
        myLock.acquireWrite();
        value=val;
        System.out.println("Writer:" + id + " updated the value to :" + val);
        myLock.releaseWrite();
    }

    //for debugging purposes
    public int getErrCount(){
         return errCount;
    }
}
